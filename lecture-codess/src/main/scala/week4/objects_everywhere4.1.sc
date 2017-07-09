/* objects everywhere
 A pure object oriented language is one which
 every value is an object.

Primitive Types can be treated like classes
scala.Int 32-bit integers
scala.Boolean by Java Boolean

*/


package idealized.scala

abstract class Boolean {
  def ifThenElse[T](t: => T, e: => T): T

  def && (x: Boolean ): Boolean = ifThenElse(x, false)
  def || (x: => Boolean ): Boolean = ifThenElse(true, x)
  def unary_! : Boolean = ifThenElse(false, true)

  def == (x: Boolean ): Boolean = ifThenElse(x,x.unary_!)
  def != (x: Boolean ): Boolean = ifThenElse(x.unary_!, x)

  def < (x: Boolean ): Boolean = ifThenElse( false , x )
  def > (x: Boolean ): Boolean = ifThenElse( x , false )

}

object true extends Boolean {
  def ifThenElse[T](t: => T, e: => T ) = t
}

object false extends Boolean {
  def ifThenElse[T](t: => T, e: => T ) = e
}

/*

  This is can be mimiced for Int.


*/


/*
  All postive numbers and zero
  Throw if negative with substraction
  Zero and Succ

  // Peano numbers
*/

abstract class Nat {
  def isZero: Boolean
  def predecessor: Nat
  def successor: Nat = new Succ(this)
  def + (that: Nat ): Nat = {
    if (that.isZero) this
    else successor(this) + predecessor(that)
  }
  def - (that: Nat ): Nat = {
    if (that.isZero ) this
    else predecessor(this) - predecessor(that)
  }
}


object Zero extends Nat {
  def isZero: Boolean = True
  def predecessor: Nat = throw new Error("0.predecessor")
}


class Succ (n: Nat) extends {
  def isZero: Boolean = false
  def predecessor: Nat = this - new Succ(Zero)
}

