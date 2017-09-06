/*

Handling Nested Sequences

 */

/*


for ( s ) yield e

s can be a generator p <- e, e collection, p is a varaible
filter is if f, f is a boolean
can be () or {}

 */


object pairs {
  def isPrime(n: Int): Boolean = {
    (2 until n).
      forall(x => (n % x) != 0)
  }

  val n = 7

  // flatMap is map followed by flatten
  ((1 until n) flatMap (i =>
    (1 until i) map (j => (i,j)))).
    filter( pair => isPrime( pair._1 + pair._2))


  // easier to understand
  for {
    i <- 1 until n
    j <- 1 until i
    if isPrime(i + j)
  } yield (i, j)

}

/*


for ( s ) yield e

s can be a generator p <- e, e collection, p is a varaible
filter is if f, f is a boolean
can be () or {}

 */
/*
def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double = {
  //(xs zip ys).map(xy => xy._1 * xy._2 ).sum
  (xs zip ys).map{ case(x,y) => x * y }.sum
}
*/
// for implementations

def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double = {
  (xs zip ys).map{ case(x,y) => x * y }.sum

  (for ((x, y) <- xs zip ys) yield x * y).sum

}












