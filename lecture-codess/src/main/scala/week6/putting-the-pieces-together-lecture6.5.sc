/*

Telephone number to sentences

I've taken it from a paper by Lutz Prechelt. Paper's called An Imperial Comparison of Seven Programming Languages. It appeared at IEEE Computer at 33 in (2000)

 */

import scala.io.Source

object x {

  private val in = Source.fromURL("http://lamp.epfl.ch/files/content/sites/lamp/files/teaching/progfun/linuxowrds")

  private val words = in.getLines.toList filter (word => word forall (chr => chr.isLetter))

  val mnem = Map(
    '2' -> "ABC", '3' -> "DEF", '4' -> "GHI", '5' -> "JKL",
    '6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ")

  // Invert the mnem map to give a map from chars 'A' .. 'Z' to '2' ... '9'
  val charCode: Map[Char, Char] =
    for ((digit, str) <- mnem; ltr <- str) yield ltr -> digit


  // Maps a word to the digit string it can represent, eg. "Java" -> "5282"
  def wordCode(word: String): String = word.toUpperCase map charCode


  /**
    A map from digit strings to the words that represent them,
    eg. "5282" -> List("Java","Kata","Lava", ...)
    Note: A missing number should map to the empty set, e.g. "1111" -> List()
   */
  val wordsForNum: Map[String, Seq[String]] =
    words groupBy wordCode withDefaultValue Seq()


  /** Return all ways to encode a number as a list of words */
  def encode(number: String): Set[List[String]] =
    if (number.isEmpty) Set(List())
    else {
      for {
        split <- 1 to number.length
        word <- wordsForNum(number take split)
        rest <- encode(number drop split)
      } yield word :: rest
    }.toSet

  /** */
  def translate(number: String): Set[String] =
    encode(number) map (_ mkString " ")

  translate("7225427386")

}

println(x.translate("7225427386"))


/*
Scala's immutable collections are:
- easy to use
- concise
- safe
- fast
- universal



 */