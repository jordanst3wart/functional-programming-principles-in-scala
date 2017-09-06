/*


N-Queens problem


 */


/*

Collection Hierarchy
Iterable
  Seq
    List
    Vector
    String (from java)
    Array (from java)
    Range
    IndexedSeq
  Set
  Map


Array, and strings are
 */

/*
Set
3 differences between sets, and sequences.
Sets are unordered.
Sets do not have duplicate elements.
The fundamental operation on sets is contains.

*/

//Set("apple","banana","pear")


/*

Place N queens on a chessboard





 */

//List(0,3,1)
//List(2,0,3,1)


object nqueens {
  def queens(n: Int): Set[List[Int]] = {
    def placeQueens(k: Int): Set[List[Int]] = {
      if (k == 0 ) Set(List())
      else
        for {
          queens <- placeQueens(k - 1)
          col <- 0 until n
          if isSafe(col, queens)
        } yield col :: queens
    }

    def isSafe2(col: Int, queens: List[Int]): Boolean = {
      // check for dialog
      val range = queens.length
      val queensSet = queens.toSet

      val newQueensSet = queensSet.++(iter(queens, Set()))

      // check for same row
      if (newQueensSet contains col ) false
      else true

      def iter(reduc: List[Int], acc: Set[Int]): Set[Int] = {
        if (reduc.isEmpty) acc
        else {
          val head = reduc.head
          val dist = reduc.tail.length // cals cols based on dist
          if (head + dist < range) acc.+(head + dist, head - dist)
          if ( head - dist > 0 ) acc.+(head - dist)
          iter(reduc.tail, acc)
        }
      }

    }

    def isSafe(col: Int, queens: List[Int]): Boolean = {
      val row = queens.length
      val queensWithRow = (row - 1 to 0 by -1) zip queens
      queensWithRow forall {
        case(r,c) => col != c && math.abs(col - c) != row - r
      }
    }

    placeQueens(n)
  }

  /*
  def show(queens: List[Int]) = {
    val lines =
      for (col <- queens.reverse)
        yield Vector.fill(queens.length)("* ").updated(col, "X ").mkString
      "\n" + (lines mkString "\n")

  }*/

  println(queens(2))
}