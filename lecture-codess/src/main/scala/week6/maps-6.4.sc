/*

Maps

Associative maps, key, value.

Has function Type Key => Value

iterables, and functions


 */

val romanNumerals = Map('I' -> 1, 'V' -> 5)

// function call
romanNumerals('I')

// option value
romanNumerals get 'I'



/*


Options
trait Option[+A]
case class Som[+A](value: A) extends Option[A]
object None extends Option[Nothing]

Support map, and flatMap


 */

val fruit = List("apple","pear","orange", "pineapple")

fruit.sorted

fruit groupBy (_.head) // maps to head char

/*


polynomial can be seen as a map from exponents to coefficients

x^3 - 2x + 5

 */

object polynomials {

  class Poly(terms0: Map[Int, Double]){
    def this(bindings: (Int, Double)*) = this(bindings.toMap)  // * means repeated, the bindings are a sequence internally, this is a construct
    private val terms = terms0 withDefaultValue 0.0 // default values
    //def + (other: Poly): Poly = new Poly(terms ++ other.terms map adjust)
    override def toString: String =
      (for ((exp, coeff) <- terms.toList.sorted.reverse) yield coeff + "x^" + exp) mkString " + "
    /*def adjust(term: (Int, Double)): (Int, Double) = {
      val (exp, coeff) = term
      exp -> (coeff + terms(exp))
    }*/

    // if other.terms is empty return terms
    // avoids intermittent data structure, so is probably more efficient than other +
    def + (other: Poly) =
      new Poly((other.terms foldLeft terms)(addTerm))
    private def addTerm(terms: Map[Int, Double], term: (Int, Double)): Map[Int,Double] = {
      val (exp, coeff) = term
      terms + (exp -> (terms(exp) + coeff))
    }



  }


  val p1 = new Poly(1->2.0, 3 -> 4.0, 5 -> 6.2)
  val p2 = new Poly(0 -> 3.0, 3 -> 7.0)
  println(p1 + p2)


}

polynomials.p1 + polynomials.p2