package funsets


/**
 * 2. Purely Functional Sets.
 */
object FunSets {
  /**
   * We represent a set by its characteristic function, i.e.
   * its `contains` predicate.
   */
  type Set = Int => Boolean // type alias

  /**
   * Indicates whether a set contains a given element.
   */
  def contains(s: Set, elem: Int): Boolean = s(elem)

  /**
   * Returns the set of the one given element.
   */
    def singletonSet(elem: Int): Set = Set(elem) // { x => x == elem } ???

  /**
   * Returns the union of the two given sets,
   * the sets of all elements that are in either `s` or `t`.
   */
    def union(s: Set, t: Set): Set = x =>  contains(s,x) || contains(t,x) // s or t // both sets of s and t
  
  /**
   * Returns the intersection of the two given sets,
   * the set of all elements that are both in `s` and `t`.
   */
    def intersect(s: Set, t: Set): Set = x => contains(s, x) && contains(t, x) // s and t
  
  /**
   * Returns the difference of the two given sets,
   * the set of all elements of `s` that are not in `t`.
   */
    def diff(s: Set, t: Set): Set = x => contains(s, x) && !contains(t, x) //  s && !t
  
  /**
   * Returns the subset of `s` for which `p` holds.
   */
    def filter(s: Set, p: Int => Boolean): Set = x => contains(s, x) && p(x) // s && p // or intersection
  

  /**
   * The bounds for `forall` and `exists` are +/- 1000.
   */
  val bound = 1000

  /**
   * Returns whether all bounded integers within `s` satisfy `p`.
   */
  def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if ( a > bound ) true
      else if ( !(s(a) && p(a)) ) false  // !intersects(s,p) // inverse of (if a is in s, p(a) is true)
      else iter(a + 1)
    }
    iter(-bound) // start at -bound go to + bound
  }
  
  /**
   * Returns whether there exists a bounded integer within `s`
   * that satisfies `p`.
   */
    def exists(s: Set, p: Int => Boolean): Boolean = {
       def t: Set = x => !contains(s,x)
      !forall(t, p) // !forall(t, x => !p(x))
    } // test that all of s is outside of p, and true the inverse

  /**
   * Returns a set transformed by applying `f` to each element of `s`.
   */
    def map(s: Set, f: Int => Int): Set = x => exists(s, y => f(y) == x )
    /*{
      def iter(a: Int, x: Set): Set = {
        if (a > bound ) x
        else {
          if( s(a) ) x( f(a) )
          iter(a + 1, x)
        }
      }
      iter( -bound, Set())
    }*/
  
  /**
   * Displays the contents of a set
   */
  def toString(s: Set): String = {
    val xs = for (i <- -bound to bound if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  }

  /**
   * Prints the contents of a set on the console.
   */
  def printSet(s: Set) {
    println(toString(s))
  }
}
