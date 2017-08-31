/*

Recurring Patterns for Computations on Lists such as:
- transforming each element in a list in a certain way
- retrieving a list of all elements satisfying a criterion
- combining the elements of a list using an operator

Functional languages allow programmers to write generic functions that implement patterns such
as these using higher-order functions.

 */
/*
def scaleList(xs: List[Double], factor: Double): List[Double] = xs match {
  case Nil => xs
  case y :: ys => y * factor :: scaleList(ys,factor)

}
*/

// ^ generalised to map

/*
abstract class List[T]{
  // map is a bit different in scala
  def map[U](f: T => U): List[U] = this match {
    case Nil => this
    case x :: xs => f(x):: xs.map(f)
  }

}

// general case of scaleList
def scaleList(xs: List[Double], factor: Double) = xs map(x => x *factor)
*/

/*
def squareList(xs: List[Int]): List[Int] = xs match {
  case Nil => xs
  case y :: ys => (y * y) :: squareList(ys)
}

def squareList(xs: List[Int]): List[Int] = xs map xs map (x => x * x)

def posElems(xs: List[Int]): List[Int] = xs match {
  case Nil => xs
  case y :: ys => if (y > 0) y :: posElems(ys) else posElems(ys)
}

def posElems(xs: List[Int]): List[Int] = xs filter (x => x > 0)
*/
// generalised to filter

/*



xs filterNot p
Same as xs filter (x => !p(x))

xs partition p
Same as xs filter p, xs filterNot p

xs takeWhile p


xs dropWhile p

xs span p


 */


def pack[T](xs: List[T]): List[List[T]] = xs match {
  case Nil => Nil
  case x :: xs1 =>
    xs1.partition(x1 => x1 == x)
}








