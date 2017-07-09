package week4



import java.util.NoSuchElementException

/**
  * Created by jordan on 9/07/17.
  */

trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
}


// copied from object polymorphism

// val head: Int, defines a field and value in the class
class Cons[T](val head: T, val tail: List[T]) extends List[T]{
  def isEmpty: Boolean = false
}

class Nil[T] extends List[T] {
  def isEmpty: Boolean = true
  def head: Nothing = throw java.util.NoSuchElementException("Nil.head")
  def tail: Nothing = throw java.util.NoSuchElementException("Nil.tail") // type Nothing
  // Nothing is a subclass of every other type
}

def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])
singleton[Int](1) // could be singleton(1)
singleton[Boolean](true) // could be singleton(true)

// List() = new Nil
// List(arg1) = new Cons(arg1, List())
// List(arg1, arg2) = new Cons(arg1, List(arg2))


object List {
  def apply[T](): List = new Nil // I'm not to sure if this works without a type
  def apply[T](arg1:  T ): List[T] = new Cons[T](arg1, new Nil)
  def apply[T](arg1: T, arg2: T): List[T] = new Cons(arg1, new Cons(arg2, new Nil))
}