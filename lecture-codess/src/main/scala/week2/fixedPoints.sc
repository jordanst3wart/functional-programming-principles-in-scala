

import math.abs

// shows using higher order functions


// a fixed point of a function of f is:
// f(x) = x

object exercise {
  val tolerance = 0.0001
  def isCloseEnough(x: Double, y: Double) = {
    abs((x - y) / x) / x < tolerance
  }
  def fixedPoint(f: Double => Double)(firstGuess: Double) = {
    def iterate(guess: Double): Double = {
      val next = f(guess)
      if(isCloseEnough(guess, next)) next
      else iterate(next)
    }
    iterate(firstGuess)
  }
  fixedPoint(x => 1 + x/2)(1)

  // sqrt algorithm
  def sqrt(x: Double)= fixedPoint(y => ((y + x / y)/2))(1)

  def NotSqrt(x: Double)= fixedPoint(y => ((y + x / y)/2))(1)

  // playing
  NotSqrt(3)
  // z is not defined
  // def BrokeSqrt(x: Double)= fixedPoint(y => ((y + z / y)/2))(1)

  // nothing really, you can have input variables that do nothing
  def NothingReally(x: Double)= { 1 }
  NothingReally(1)




  def averageDamp(f: Double => Double)(x: Double)= (x + f(x))/2

  // probably the most concise defintion of sqrt
  def sqrt2(x: Double) = {
    // a function that takes a function to another function
    fixedPoint(averageDamp(y => x/y))(1)
  }
  sqrt(2)
  sqrt2(2)

}





