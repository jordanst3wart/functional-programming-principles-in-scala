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
      val fork2 = Fork(Leaf('c',1),Fork(Leaf('a',1),Leaf('b',1),List('a', 'b'),2),List('c', 'a', 'b'),3)
      val fork3 = Fork(Fork(Leaf('b',1),Leaf('c',1),List('b', 'c'),2),Leaf('a',2),List('b', 'c', 'a'),4)
      assert( createCodeTree(charList1) === fork2)
      assert( createCodeTree(charList2) === fork3)
    }
  }

  test("test decode"){
    new TestMyFuncs {
      //decode(tree: CodeTree, bits: List[Bit]): List[Char]
      assert( decode(fork1,List(1,1,1)) === List('c','c','c'))
      assert( decode(fork1,List(0,0,0)) === List('b','b','b'))
      assert( decode(fork1,List(1,0,1)) === List('c','b','c'))
    }
  }

  test("test decode secret"){
    new TestMyFuncs {
      //decode(tree: CodeTree, bits: List[Bit]): List[Char]
      assert( decodedSecret === List('h', 'u', 'f', 'f', 'm', 'a', 'n', 'e', 's', 't', 'c', 'o', 'o', 'l'))
    }
  }

  test("test encode"){
    new TestMyFuncs {
      assert( encode(fork1)(List('c','c','c') ) === List(1,1,1))
      assert( encode(fork1)(List('b','b','b') ) === List(0,0,0) )
      assert( encode(fork1)(List('c','b','c') ) === List(1,0,1) )
    }
  }

  // testing what happens when a single Leaf is input as the codeTree
  // it just returns List()
  test("break encode"){
    new TestMyFuncs {
      assert( encode(Leaf('b', 1))(List('b') ) === List())
    }
  }

  test("code table"){
    val table1 = List(('a', List(0,0,0)),('b', List(1,0,1)))
    assert(codeBits(table1)('b') === List(1,0,1))
  }

  test("quick encode"){
    new TestMyFuncs {
      assert(quickEncode(fork1)(List('c', 'c', 'c')) === List(1, 1, 1))
      assert(quickEncode(fork1)(List('b', 'b', 'b')) === List(0, 0, 0))
      assert(quickEncode(fork1)(List('c', 'b', 'c')) === List(1, 0, 1))
    }
  }

}
