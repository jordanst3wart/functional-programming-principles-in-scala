package week3

import java.util.NoSuchElementException

/*
// Cons-List
// Nil the empty list
// Cons
// list(1, 2, 3)



*/

// type parameters are written in square brackets
trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]

}




// val head: Int, defines a field and value in the class
class Cons[T](val head: T, val tail: List[T]) extends List[T]{
  def isEmpty: Boolean = false
}

class Nil[T] extends List[T] {
  def isEmpty: Boolean = true
  def head: Nothing = throw NoSuchElementException("Nil.head")
  def tail: Nothing = throw NoSuchElementException("Nil.tail") // type Nothing
  // Nothing is a subclass of every other type
}

def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])
singleton[Int](1) // could be singleton(1)
singleton[Boolean](true) // could be singleton(true)


// Type parameters do not affect evaluation in Scala

// Types are only important for the compiler, but are not used at runtime

// type erasure, remove types, java, Scala, Haskell
// keep types, C++, F#


/*
Polymorphism mean that a function type comes "in many forms".

  subtyping: instances of a subclass can be passed to a base class
  generics: instances of a function or class are created by type parameterization


 */

def nth(n: int, list: List[T]): T = {
  if (list.isEmpty) throw new IndexOutOfBoundsException
  else if (n == 0) list.head
  else nth(n -1 , list.tail)
}