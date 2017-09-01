

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
Or it splits the list at p.

xs takeWhile p
Takes the longest prefix


xs dropWhile p
Drops the longest prefix

xs span p
Combines takeWhile and dropWhile

 */

object lectureHigherOrder {
  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    case x :: xs1 => {
      val list = xs.span(y => y == x)
      list._1 :: pack(list._2)
    }
  }

  def encode[T](xxs: List[T]): List[(T, Int)] = {
    pack(xxs).
    map(x => (x.head, x.length))
  }
  //println(pack(List("a","a","a","b","c","c","a")))
}

println(lectureHigherOrder.pack(List("a","a","a","b","c","c","a")))
println(lectureHigherOrder.encode(List("a","a","a","b","c","c","a")))






/*


pack(List("a","a","a","b","c","c","a"))
should give:
List(List("a","a","a"), List("b"), List("c","c"), List("a"))


encode(List("a", "a", "a", "b", "c", "c", "a"))
List(("a", 3), ("b", 1), ("c", 2), ("a", 1))

 */


