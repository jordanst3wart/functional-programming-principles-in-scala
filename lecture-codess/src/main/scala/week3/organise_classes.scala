
package week3

import week3.Rational
// import week3.{Rational, Hello}  // named import
// import week3._  // wildcard


/**
  * Created by jordan on 28/06/17.
  */


// classes are generally put into packages ie. package progfun.examples
// fully qualitied name is something like progfun.example.hello

object scratch {
  new Rational(1,2)

  def error(msg: String) = threw new Error(msg)


  val x = null
  val y: String = x

  //val z: Int = null type mismatch

}



/*

 Automatic imports
  all members of packag scala
  all members of package java.lang
  all members of the singleton object scala.Predef

Int         scala.Int
Boolean     scala.Boolean
Object      java.lang.Object


full info: www.scala-lang.org/docs???


Scala is a single inheritance language in java as well as in Scala. This can be achieved with a trait

Classes can multiple traits, traits are like interfaces in java. traits can have fields, and concrete methods.
In java interfaces can only contain abstract methods.
traits cannnot have value parameters in scala 2....

classes can have value parameters, where traits cannot atm.

class Square extends Shape with Planar with Moveable ...

Nothing (scala.Nothing)
- can be used for abnormal termination.
- as an element type of empty collections ie. a set[Nothing]

throw Exc has type Nothing



scala.Null
Every class has a null value.

The type of null is Null.

Null is a subtype of every class that inherits from Object
it is incompatible with subtypes of AnyVal



AnyVal is the superclass of value classes ie. Int, Float, Boolean

AnyRef is the superclass of reference objects.

 */


