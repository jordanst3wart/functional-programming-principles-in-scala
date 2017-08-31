// package week5

import math.Ordering

object mergesort {

  //
  // (lt: (T,T) => Boolean)

  def msort[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
    val n = xs.length / 2
    if (n == 0) xs // 0 or 1, rounds down
    else {

      def merge(xs: List[T], ys: List[T]): List[T] = {
        (xs, ys) match {
          case (Nil, ysp) => ysp
          case (xsp, Nil) => xsp
          case (x :: xs1, y :: ys1) =>
            if (ord.lt(x,y)) x :: merge(xs1, ys)
            else y :: merge(ys1, xs)
        }
      }

      val (fst, snd) = xs.splitAt(n)
      merge(msort(fst), msort(snd))
    }
  }

  val nums = List(1,4,3,2)

}

/*

- is marked implicit
- has a type compatible with T
- is visible at the point of the function call, or is defined in a companion object associated with T

Otherwise error





 */