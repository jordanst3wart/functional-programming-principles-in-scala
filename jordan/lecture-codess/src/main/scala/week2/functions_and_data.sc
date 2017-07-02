
// 2.5, 2.6
// 2.5 is data, and objects
// 2.6 is data abstraction
// clients observe the same thing, regardless of software changes.

// x/y

// numerator x
// denominator y

// require is used to enforce a precondition on the caller of a function
// assert is used as to check th code of the functino itself

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
  def add(that: Rational): Rational = {
    new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
  }

  def substract(that: Rational): Rational = {
    add(that.neg)
  }

  def less(that: Rational): Boolean = this.numer * that.denom < that.numer * this.denom

  def max(that: Rational): Rational = if(this.less(that)) that else this

  def neg = new Rational(-numer, denom)

  override def toString: String = numer + "/" + denom
}

// type named Rational
// a constructor Rational


object rationals {
  val foo = new Rational(1,2)
  foo.numer
  foo.denom

  println(foo.toString)
  println(foo.neg)

}

val foo2 = new Rational(2,4)
println(foo2.toString)


