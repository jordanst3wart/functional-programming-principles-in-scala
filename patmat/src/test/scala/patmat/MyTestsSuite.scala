package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class MyTestsSuite extends FunSuite {
  trait TestMyFuncs{
    val charList1 = List('a','b','c')
    val charList2 = List('a','b','c','a')
    val charList3 = List('a','b','c','a','c')

    val pairList1 = List(('a',1),('b',1),('c',1))
    val pairList2 = List(('a',2),('b',1),('c',1))
    val pairList3 = List(('a',2),('b',1),('c',2))


    val codeTree1 = List(Leaf('a', 1) ,Leaf('b', 1) ,Leaf('c', 1))
    val codeTree2 = List(Leaf('b', 1) ,Leaf('c', 1) ,Leaf('a', 2))

    val fork1 = Fork(Leaf('b', 1), Leaf('c', 1), List('b','c'), 2)
  }


  test("test times") {
    new TestMyFuncs {
      assert(times(charList1) === pairList1)
      assert(times(charList2) === pairList2)
      assert(times(charList3) === pairList3)
    }
  }

  test("test makeOrderedLeafList") {
    new TestMyFuncs {
      assert( makeOrderedLeafList(pairList1) === codeTree1 )
      assert( makeOrderedLeafList(pairList2) === codeTree2 )
      //assert( ma)
    }
  }

  test("test combine"){
    new TestMyFuncs {
      assert( combine(codeTree2) ===  List(fork1 ,Leaf('a', 2)))
    }
  }

  test("test makecodetree"){
    new TestMyFuncs {
      assert( combine(codeTree2) ===  List(fork1 ,Leaf('a', 2)))
    }
  }

}
