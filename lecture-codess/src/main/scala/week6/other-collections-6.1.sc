/*

Lists
Good for accessing the head, and the tail (constant time).

Lists are linear. Access to the first element is much faster than access to the middle or end. Constant time to access head, and tail.

*/

List(1,2,3)

/*

Vectors
Good for map, and fold operations, due to caching, and faster access.

Vector are very shallow trees. Up to 32. 2*10 (or 32 * 32). Is pointers to pointers. Good with 32, 1 access. log32(N) N is size of vector. Vectors have a good random access profile. Good for maps, and folds, as it can be done with groups of 32, which is normally a cache line.

Can apply same operations except

  x +: xs leading element x
  x :+ xs  ending element x

 */


Vector(1,2,3)


/*

Collection Hierarchy
Iterable
  Seq
    List
    Vector
    String (from java)
    Array (from java)
    Range
    IndexedSeq
  Set
  Map


Array, and strings are
 */




/*

Ranges

Stores lower, upper, and step value.

 */

val r: Range = 1 until 5
val s: Range = 1 to 5 // 1, 2,3, 4,5
val t: Range = 1 to 10 by 3

/*

xs exists p true if one elems
xs forall p true if all elems

xs zip ys     combines two lists zippers (or zips them together)
xs unzip      opposite of zip
xs flatMap f  applies collection-valued function f to all elements of xs and concatenates the results.
 flatMap is the same as applying map and then flatten
xs sum
xs max



 */

List(1,2,3).flatMap( x => List(x*2) )

// returns combinations
def combinations(m: Int,n: Int): IndexedSeq[(Int, Int)] = {
  (1 to m) flatMap (x => (1 to n) map (y => (x, y)))
}


/*

Scalar Product of two vectors.

 */
def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double = {
  //(xs zip ys).map(xy => xy._1 * xy._2 ).sum
  (xs zip ys).map{ case(x,y) => x * y }.sum
}

/*
Short hand for pattern matching:
{ case p1 => e1 ... case pn => en }
is equivalent to:
x => x match { case p1 => e1 ... case pn => en }
*/

// maybe forall
def isPrime(n: Int): Boolean = {
  (2 until n).
    forall(x => (n % x) != 0)
}

isPrime(3)