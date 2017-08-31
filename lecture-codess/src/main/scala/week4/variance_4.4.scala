/**
  * Created by jordan on 15/07/17.
  */

import java.util.NoSuchElementException
/*

Variance
How subtyping relates to generics
Cons list

list convariant
is immuteable

arrays not covariant
muteable


Where C[T] is a parameterized type and A, B are types such that A <: B.
A <: B (A is a subtype of B) <: subtype >: supertype

C[A] <: C[B] C is covariant
C[A] >: C[B] C is contravariant
neither C[A] nor C[B] is a subtype of the other C is nonvariant

class C[+A]{...}    covariant
class C[-A]{...}    contravariant
class C[A]{...}     nonvariant

 */

// type A = IntSet => NonEmpty
// type B = NonEmpty => IntSet
// IntSet >: NonEmpty
// A <: B, A can take all the inputs of B

// Barbara Liskov (Liskov Substitution Principle)
/*

If A <: B (A subtype of B), then everything one can to do with a value of
type B one should also be able to do with a value of type A.

*/

/*
Subtyping between functions
If A2 <: A1, and B1 <: B2, then

A1 => B1 <: A2 => B2

So functions are contravariant in their argument types and covariant in their result type.
This looks like:

trait Function1[-T,+U] {
  def apply(x: T): U
}
*/






/*
Variance Checks:

This leds to problems:
class Array[+T]{
  def update(x: T) ...
}

 */
/*
The scala compiler roughly allows:

- covariant type parameters can only appear in method results.
- contravariant type parameters can only appear in method parameters.
- invariant type parameters can appear anywhere.

 */



/**
  * Created by jordan on 9/07/17.
  */

trait List[+T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
  def prepend [U >: T](elem: U): List[U] = new Cons(elem, this)
}


// copied from object polymorphism

// val head: Int, defines a field and value in the class
class Cons[T](val head: T, val tail: List[T]) extends List[T]{
  def isEmpty: Boolean = false
}

// Nothing is the bottom type
object Nil extends List[Nothing] {
  def isEmpty: Boolean = true
  def head: Nothing = throw java.util.NoSuchElementException("Nil.head")
  def tail: Nothing = throw java.util.NoSuchElementException("Nil.tail") // type Nothing
  // Nothing is a subclass of every other type
}


object test {
  val x: List[String] = Nil
  def f(xs: List[NonEmpty], x: Empty) = xs prepend x
}