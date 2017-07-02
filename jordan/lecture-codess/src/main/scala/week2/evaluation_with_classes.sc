

/*
  Infix operations
 r add s r.add(s)
 r less s r.less(s)

  alphanumeric identifier
  _ is identifier (alphanumeric)
  symbolic identifer ie. ? *


 */

class Rational (x: Int, y: Int) {
  require( y != 0, "denominator must be non-zero")
  // a require is a test performed when the class is created

  // if this is used as a function it is a constructor
  def this(x: Int) = this(x,1) // this(x,1) is the implicit primary constructor

  // there is also assert
  private def gcd(a: Int,b: Int): Int = if(b == 0) a else gcd(b, a % b)
  private val g = gcd(x, y)
  val numer: Int = x / g
  val denom: Int = y / g

  // add
  def + (that: Rational): Rational = {
    new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
  }

  def - (that: Rational): Rational = {
    add(that.neg)
  }

  def < (that: Rational): Boolean = this.numer * that.denom < that.numer * this.denom

  def max(that: Rational): Rational = if(this.less(that)) that else this

  def unary_- = new Rational(-numer, denom) // prefix operator ie. -Rational

  override def toString: String = numer + "/" + denom
}


// prevedence Rules help implement order of evaluation
// like 1 * 1
// prevedence is evaluated universally
