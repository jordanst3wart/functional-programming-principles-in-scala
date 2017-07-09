package scalaFake


import java.util.NoSuchElementException


// function as an object
// function types
trait Function1[A, B] {
  def apply(x: A): B
}


(x: Int) => x * x
// expanded too:
{


class AnonFun extends Function1[Int, Int] {
    def apply(x: Int) = x * x
  }
  new AnonFun
}


/*

f(a,b)
f.apply(a,b)
val f = (x: Int) => x * x
f(7)

val f = new Function1[Int, Int] {
  def apply(x: Int) = x * x
}

// def f(x: Int): Boolean = ...

// convert to

// This is called eta-expansion in lambda calculus

 */

