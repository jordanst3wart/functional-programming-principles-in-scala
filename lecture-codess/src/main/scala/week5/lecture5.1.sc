import scala.runtime.Nothing$

/*

xs.length number of elements of xs
xs.last the last element
xs.init a list containing all elements but the last one
xs take n take  list consisting of the first n lements of cs
xs drop n drops n number of elements in the list
xs(n) n is an index

xs ++ ys cats to two lists
xs.reverse reverses the list
xs.updated(n,x) changes one element in a list.

xs indexOf x  -1 if not in list, n if
xs contains x if x is in the element at all



 */

/*

// n complexity
def last[T](xs: List[T]): T = xs match {
  case List() => throw new Error("empty list)
  case List(x) => x
  case y :: ys => last(ys)
}


def init[T](xs: List[T]): List[T] = xs match {
  case List() => throw new Error("init of empty list")
  case List(x) => List()
  case y :: ys => y :: init(ys)
}


// complexity n or | xs |
def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
  case List() => ys
  case z :: zs => z :: concat(zs, ys)
}

// n complexity
// n^2 complexity, should be linear, or n log n
def reverse[T](xs: List[T]): List[T] = xs match {
  case List() => List()
  case y:: ys => reverse(ys) ++ List(y)
}

def removeAt[T](xs: List[T], n: Int): List[T] = xs match {
  case List() => throw new Error("No such index")
  case List(y) => if(n==0) List()
  case y::ys =>
   if ( n == 0 ) ys
   else y::removeAt(ys,n-1)
}
// def removeAt[T](xs: List[T], n: Int): List[T] = (xs take n) ::: (xs drop n + 1)

 */


def flatten(xs: List[Any]): List[Any] = {

  def Iter(elem: Any, done: List[Any], queued: List[Any]): List[Any] = {
    val fst = elem match {
      //case List() => List()
      case y :: ys => List(y)
      case y => List(y)
    }

  }

  if (xs == List()) List()
  else Iter(xs.head, List(), xs.tail)
  // wrap with a list at the end???
}

flatten(List())
flatten(List(1))
flatten(List(1,2))
flatten(List(List(1)))
flatten(List(List(1, 1), 2, List(3, List(5, 8))))