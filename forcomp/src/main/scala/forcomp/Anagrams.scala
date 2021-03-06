package forcomp

import java.util.Dictionary

import scala.annotation.tailrec


object Anagrams {

  /** A word is simply a `String`. */
  type Word = String

  /** A sentence is a `List` of words. */
  type Sentence = List[Word]

  /** `Occurrences` is a `List` of pairs of characters and positive integers saying
   *  how often the character appears.
   *  This list is sorted alphabetically w.r.t. to the character in each pair.
   *  All characters in the occurrence list are lowercase.
   *
   *  Any list of pairs of lowercase characters and their frequency which is not sorted
   *  is **not** an occurrence list.
   *
   *  Note: If the frequency of some character is zero, then that character should not be
   *  in the list.
   */
  type Occurrences = List[(Char, Int)]

  /** The dictionary is simply a sequence of words.
   *  It is predefined and obtained as a sequence using the utility method `loadDictionary`.
   */
  lazy val dictionary: List[Word] = loadDictionary

  /** Converts the word into its character occurrence list.
   *
   *  Note: the uppercase and lowercase version of the character are treated as the
   *  same character, and are represented as a lowercase character in the occurrence list.
   *
   *  Note: you must use `groupBy` to implement this method!
   */
  def wordOccurrences(w: Word): Occurrences = {
    // might need to remove non-Letter characters
    val list = w.toLowerCase().toList

    list.groupBy((element: Char) => element).
      map((x) => (x._1, x._2.length)).toList.sortWith(_._1 < _._1)
  }

  /** Converts a sentence into its character occurrence list. */
  def sentenceOccurrences(s: Sentence): Occurrences = {

    // concat sentences into a word
    // use wordOccurrences on the sentence
    // might need to remove special characters
    if (s.isEmpty) List()
    else if (s.tail.isEmpty) wordOccurrences(s.head)
    else wordOccurrences(s.reduce((x,y) => x + y))
  }

  /** The `dictionaryByOccurrences` is a `Map` from different occurrences to a sequence of all
   *  the words that have that occurrence count.
   *  This map serves as an easy way to obtain all the anagrams of a word given its occurrence list.
   *
   *  For example, the word "eat" has the following character occurrence list:
   *
   *     `List(('a', 1), ('e', 1), ('t', 1))`
   *
   *  Incidentally, so do the words "ate" and "tea".
   *
   *  This means that the `dictionaryByOccurrences` map will contain an entry:
   *
   *    List(('a', 1), ('e', 1), ('t', 1)) -> Seq("ate", "eat", "tea")
   *
   */
    // Occurrences need to be sorted
  lazy val dictionaryByOccurrences: Map[Occurrences, List[Word]] = {
    dictionary.groupBy(x => wordOccurrences(x)) // missed the reverse index ('m',1),('y',1) -> my however ('y',1),('m',1) -> my doesn't
    /*

    @tailrec
    def iter(mapOccurrencesList: Map[Occurrences, List[Word]], dictionary: List[Word]): Map[Occurrences, List[Word]] = {
      if (dictionary.isEmpty) mapOccurrencesList
      else {
        val word = dictionary.head
        val occur = wordOccurrences(word)
        val newMap = mapOccurrencesList + (occur -> (word :: mapOccurrencesList(occur)))
        iter(newMap,dictionary.tail)
      }
    }

    // set default of empty list
    val map: Map[Occurrences, List[Word]] = Map().withDefaultValue(List())
    iter(map,dictionary)*/
  }

  /** Returns all the anagrams of a given word. */
  def wordAnagrams(word: Word): List[Word] = {
    val occurrences = wordOccurrences(word)
    dictionaryByOccurrences(occurrences)
  }

  /** Returns the list of all subsets of the occurrence list.
   *  This includes the occurrence itself, i.e. `List(('k', 1), ('o', 1))`
   *  is a subset of `List(('k', 1), ('o', 1))`.
   *  It also include the empty subset `List()`.
   *
   *  Example: the subsets of the occurrence list `List(('a', 2), ('b', 2))` are:
   *
   *    List(
   *      List(),          List(('a', 1)),             List(('a', 2)),
   *      List(('b', 1)),  List(('a', 1), ('b', 1)),   List(('a', 2), ('b', 1)),
   *      List(('b', 2)),  List(('a', 1), ('b', 2)),   List(('a', 2), ('b', 2))
   *    )
   *
   *  Note that the order of the occurrence list subsets does not matter -- the subsets
   *  in the example above could have been displayed in some other order.
   */

  def combinations(occurrences: Occurrences): List[Occurrences] = {
    def combinations_other(x: (Char, Int)): Occurrences = {
      val pairs = for ( i <- x._2 to 0 by -1 ) yield {
        (x._1,i)
      }
      pairs.toList
    }

    // accumulate pairs onto occurrences lists
    def accumulate(list: Occurrences, accum: List[Occurrences]): List[Occurrences] ={
      val something = for {
        item <- list
        item2 <- accum
      } yield {
        removeZeroElements(item2 :+ item) //removeZeroElements(item :: item2)
      }
      something
    }

    @tailrec
    def iter( list: Occurrences, accum: List[Occurrences] ): List[Occurrences] = {
      if (list.isEmpty) accum
      else {
        val combos = combinations_other(list.head)
        val newAccum = accumulate(combos, accum)
        iter(list.tail, newAccum)
      }
    }

    iter(occurrences, List(List()))
  }

  private def removeZeroElements(list: Occurrences): Occurrences = {
    list.filter(item => item._2 > 0)
  }
  /** Subtracts occurrence list `y` from occurrence list `x`.
   *
   *  The precondition is that the occurrence list `y` is a subset of
   *  the occurrence list `x` -- any character appearing in `y` must
   *  appear in `x`, and its frequency in `y` must be smaller or equal
   *  than its frequency in `x`.
   *
   *  Note: the resulting value is an occurrence - meaning it is sorted
   *  and has no zero-entries.
   */
  def subtract(x: Occurrences, y: Occurrences): Occurrences = {
    val xMap = x.toMap
    val yMap = y.toMap.withDefaultValue(0)
    val subtractedMap = xMap.map( elem => (elem._1, xMap(elem._1) - yMap(elem._1))).toList
    removeZeroElements(subtractedMap)
  }
  // Hint: you can use `foldLeft`, and `-`, `apply` and `updated` operations on `Map`.

  /** Returns a list of all anagram sentences of the given sentence.
   *
   *  An anagram of a sentence is formed by taking the occurrences of all the characters of
   *  all the words in the sentence, and producing all possible combinations of words with those characters,
   *  such that the words have to be from the dictionary.
   *
   *  The number of words in the sentence and its anagrams does not have to correspond.
   *  For example, the sentence `List("I", "love", "you")` is an anagram of the sentence `List("You", "olive")`.
   *
   *  Also, two sentences with the same words but in a different order are considered two different anagrams.
   *  For example, sentences `List("You", "olive")` and `List("olive", "you")` are different anagrams of
   *  `List("I", "love", "you")`.
   *
   *  Here is a full example of a sentence `List("Yes", "man")` and its anagrams for our dictionary:
   *
   *    List(
   *      List(en, as, my),
   *      List(en, my, as),
   *      List(man, yes),
   *      List(men, say),
   *      List(as, en, my),
   *      List(as, my, en),
   *      List(sane, my),
   *      List(Sean, my),
   *      List(my, en, as),
   *      List(my, as, en),
   *      List(my, sane),
   *      List(my, Sean),
   *      List(say, men),
   *      List(yes, man)
   *    )
   *
   *  The different sentences do not have to be output in the order shown above - any order is fine as long as
   *  all the anagrams are there. Every returned word has to exist in the dictionary.
   *
   *  Note: in case that the words of the sentence are in the dictionary, then the sentence is the anagram of itself,
   *  so it has to be returned in this list.
   *
   *  Note: There is only one anagram of an empty sentence.
   */
  /*
  def sentenceAnagrams(sentence: Sentence): List[Sentence] = {
    if (sentence.isEmpty) List(Nil)
    else {
      // break into occurrences
      val occurrences = sentenceOccurrences(sentence)
      // get all combos
      val combos = combinations(occurrences)
      // get all words from occurrences
      // probably want to be a map
      val wordsByOccurrences = combos.map( elem =>
        (elem, dictionaryByOccurrences(elem))
      )//.toMap

      //dictionary.foldLeft(dictionary.head)(subtract(occurrences,dictionary.head)

      // get list of words for each grouping of occurrences
      // could repeat I think

      def iter(temp: Occurrences, reducedWordsByOccurrences: List[(Occurrences,List[Word])], accum:  List[Word]): List[Word] = {
        if (temp.isEmpty || reducedWordsByOccurrences.isEmpty ) accum
        else {
          val map = reducedWordsByOccurrences.toMap
          val newList = map(temp) ::: accum
          val newTemp = subtract(temp, reducedWordsByOccurrences.head._1)
          iter(newTemp,reducedWordsByOccurrences,newList)
        }
      }



      //listOfWords

      //subtract(combos)


      print(anagramSentence)
      //def iter()
      // iterate through each combinations substracting letters when you find a word

    }
  }*/

  def sentenceAnagrams(sentence: Sentence): List[Sentence] = {



    def cal(ys: Occurrences): List[Sentence] = {
      if(ys.isEmpty) List(List()) // need to be List(Nil)
      else {
        println(ys)
        //val OccurrencesInDictionaryFromSentence = combinations(ys).filter( x => dictionaryByOccurrences.contains(x))
        //val wordsFromSentence = OccurrencesInDictionaryFromSentence.map( occ => dictionaryByOccurrences(occ)  )
        //val foo = combinations(ys).filter( x => dictionaryByOccurrences.contains(x));

        // need to sort occurrences for dictionary... :|
        // TODO should clean up sorting
        for {
          occurrence <- combinations(ys).filter( x => dictionaryByOccurrences.contains(x.sortWith(_._1 < _._1)));
          word <- dictionaryByOccurrences(occurrence.sortWith(_._1 < _._1));
          sentence <- cal( subtract(ys, occurrence) )
        } yield {
          word :: sentence
        }
      }
    }

    cal(sentenceOccurrences(sentence))
  }
}
