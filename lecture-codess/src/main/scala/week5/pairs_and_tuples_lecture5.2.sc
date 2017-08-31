

// merge sort, more efficient than insertion sort
/*

  pair, and tuples
  - decompose with patten matching
  val (label, value) = pair

    scala.Tuplen[T1,...Tn]
     it is a case class
     it overrides toString
  Tuple pattern:
    scala.Tuplen(p1,..p2)


 */


/*
  Zero, or one elements the list is already sorted.
  Otherwise:
    - separate the list into two lists
    - sort the two sub-lists
    - Merge the two sorted sub-lists into a single sorted list
 */

def msort(xs: List[Int]): List[Int] = {
  val n = xs.length/2
  if (n == 0 ) xs // 0 or 1, rounds down
  else {
    val (fst, snd) = xs.splitAt(n)
    mergePairPattenMatch(msort(fst), msort(snd))
  }
}

def merge(xs: List[Int], ys: List[Int]): List[Int] = {
  // left list pattern match
  xs match {
    case Nil => ys
    case x :: xs1 =>
      // right list pattern match
      ys match {
        case Nil => xs
        case y :: ys1 =>
          if (x < y ) x :: merge(xs1,ys)
          else y :: merge(ys1,xs)
      }
  }
}

def mergePairPattenMatch(xs: List[Int], ys: List[Int]): List[Int] = {
  (xs,ys) match {
    case (Nil, ysp) => ysp
    case (xsp, Nil) => xsp
    case (x::xs1,y::ys1) =>
      if (x < y) x :: mergePairPattenMatch(xs1,ys)
      else y :: mergePairPattenMatch(ys1,xs)
  }
}


val nums = List(4,1,19,3)
msort(nums)
