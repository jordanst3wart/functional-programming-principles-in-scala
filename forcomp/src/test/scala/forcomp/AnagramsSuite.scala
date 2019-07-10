package forcomp

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import Anagrams._

@RunWith(classOf[JUnitRunner])
class AnagramsSuite extends FunSuite  {

  test("wordOccurrences: abcd") {
    assert(wordOccurrences("abcd") === List(('a', 1), ('b', 1), ('c', 1), ('d', 1)))
  }

  test("wordOccurrences: Robert") {
    assert(wordOccurrences("Robert") === List(('b', 1), ('e', 1), ('o', 1), ('r', 2), ('t', 1)))
  }


  test("sentenceOccurrences: abcd e") {
    assert(sentenceOccurrences(List("abcd", "e")) === List(('a', 1), ('b', 1), ('c', 1), ('d', 1), ('e', 1)))
    assert(sentenceOccurrences(List("abcccd", "e")) === List(('a', 1), ('b', 1), ('c', 3), ('d', 1), ('e', 1)))
  }


  test("dictionaryByOccurrences.get: eat") {
    assert(dictionaryByOccurrences.get(List(('a', 1), ('e', 1), ('t', 1))).map(_.toSet) === Some(Set("ate", "eat", "tea")))
  }


  test("word anagrams: married") {
    assert(wordAnagrams("married").toSet === Set("married", "admirer"))
  }

  test("word anagrams: player") {
    assert(wordAnagrams("player").toSet === Set("parley", "pearly", "player", "replay"))
  }

  test("combinations: []") {
    assert(combinations(Nil) === List(Nil))
  }

  test("combinations: aabb") {
    val abba = List(('a', 2), ('b', 2))
    val abbacomb = List(
      List(),
      List(('a', 1)),
      List(('a', 2)),
      List(('b', 1)),
      List(('a', 1), ('b', 1)),
      List(('a', 2), ('b', 1)),
      List(('b', 2)),
      List(('a', 1), ('b', 2)),
      List(('a', 2), ('b', 2))
    )
    assert(combinations(abba).toSet === abbacomb.toSet)
  }


  test("combinations: aaabb") {
    val abba = List(('a', 3), ('b', 2))
    val abbacomb = List(
      List(),
      List(('a', 1)),
      List(('a', 2)),
      List(('a', 3)),
      List(('b', 1)),
      List(('a', 1), ('b', 1)),
      List(('a', 2), ('b', 1)),
      List(('a', 3), ('b', 1)),
      List(('b', 2)),
      List(('a', 1), ('b', 2)),
      List(('a', 2), ('b', 2)),
      List(('a', 3), ('b', 2))
    )
    assert(combinations(abba).toSet === abbacomb.toSet)
  }

  test("combinations: abc") {
    val abba = List(('a', 1), ('b', 1), ('c',1))
    val abbacomb = List(
      List(),
      List(('a', 1)),
      List(('a', 1), ('b', 1)),
      List(('a', 1), ('c', 1)),
      List(('a', 1),('b', 1), ('c', 1)),
      List(('b', 1)),
      List(('b', 1), ('c', 1)),
      List(('c', 1))
    )
    assert(combinations(abba).toSet === abbacomb.toSet)
  }

  test("subtract1: lard - r") {
    val lard = List(('a', 1), ('d', 1), ('l', 1), ('r', 1))
    val r = List(('r', 1))
    val lad = List(('a', 1), ('d', 1), ('l', 1))
    assert(subtract(lard, r) === lad)
  }

  // I think that is right
  test("subtract2: lard - lard") {
    val lard = List(('a', 1), ('d', 1), ('l', 1), ('r', 1))
    val lard2 = List(('a', 1), ('d', 1), ('l', 1), ('r', 1))
    val nothing = List()
    assert(subtract(lard, lard2) === nothing)
  }

  test("subtract3: larrrrd - arrrr") {
    val larrrrd = List(('a', 1), ('d', 1), ('l', 1), ('r', 4))
    val arrrr = List(('a', 1), ('r', 4))
    val ld = List(('d',1),('l', 1))

    assert(subtract(larrrrd, arrrr) === ld)
  }

  test("subtract4: laarrrd - laarrr") {
    val laarrrd = List(('a', 2), ('d', 1), ('l', 1), ('r', 3))
    val laarrr = List(('a', 2), ('l', 1), ('r', 3))
    val d = List(('d', 1))
    assert(subtract(laarrrd, laarrr) === d)
  }

  test("subtract5: larrrrd - arrr") {
    val larrrrd = List(('a', 1), ('d', 1), ('l', 1), ('r', 4))
    val arrr = List(('a', 1), ('r', 3))
    val lrd = List(('d',1),('l', 1),('r', 1))

    assert(subtract(larrrrd, arrr) === lrd)
  }

  test("sentence anagrams: []") {
    val sentence = List()
    assert(sentenceAnagrams(sentence) === List(Nil))
  }

  // 1 word
  test("sentence anagrams: in") {
    val sentence = List("in")
    val anas = List(
      List("in")
    )
    assert(sentenceAnagrams(sentence).toSet === anas.toSet)
  }

  // 2 words
  test("sentence anagrams: i in") {
    val sentence = List("inside")
    val anas = List(
      List("i","in")
    )
    assert(sentenceAnagrams(sentence).toSet === anas.toSet)
  }

  // 2 words from 1 word
  test("sentence anagrams: in it") {
    val sentence = List("init")
    val anas = List(
      List("it","in")
    )
    assert(sentenceAnagrams(sentence).toSet === anas.toSet)
  }

  test("sentence anagrams: Linux rulez") {
    val sentence = List("Linux", "rulez")
    val anas = List(
      List("Rex", "Lin", "Zulu"),
      List("nil", "Zulu", "Rex"),
      List("Rex", "nil", "Zulu"),
      List("Zulu", "Rex", "Lin"),
      List("null", "Uzi", "Rex"),
      List("Rex", "Zulu", "Lin"),
      List("Uzi", "null", "Rex"),
      List("Rex", "null", "Uzi"),
      List("null", "Rex", "Uzi"),
      List("Lin", "Rex", "Zulu"),
      List("nil", "Rex", "Zulu"),
      List("Rex", "Uzi", "null"),
      List("Rex", "Zulu", "nil"),
      List("Zulu", "Rex", "nil"),
      List("Zulu", "Lin", "Rex"),
      List("Lin", "Zulu", "Rex"),
      List("Uzi", "Rex", "null"),
      List("Zulu", "nil", "Rex"),
      List("rulez", "Linux"),
      List("Linux", "rulez")
    )
    assert(sentenceAnagrams(sentence).toSet === anas.toSet)
  }

}
