/*

Higher order functions:
fold, or reduce combinators
insert, and do an operation

Combine with a given operator.

 */


/*

foldLeft, and reduceLeft produce trees that lean to the left.

 */


object withReduceLeft {
  def sum(xs: List[Int]): Int = ( 0 :: xs) reduceLeft(_ + _)

  def product(xs:List[Int]): Int = (1 :: xs) reduceLeft(_ * _)

  // _ represents a function parameter
  // ie. ( x=> x) = _
  //def product(xs:)
}

// foldLeft has an initial operator

def sum(xs: List[Int]) = xs.foldLeft(0)(_ + _)
def product(xs: List[Int]) = xs.foldLeft(1)(_ * _)


/*

  associative, and communicative, foldRight, and foldLeft, give the same result.

*/


/*
  Map and length implemented with foldRight
  def mapFun[T, U](xs: List[T], f: T => U): List[U] = xs.foldRight(List[U]())(???)
  def lengthFun[T](xs: List[T]): Int = xs.foldRight(0)(_ + 1)
*/

