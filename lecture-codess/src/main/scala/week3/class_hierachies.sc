package week3

object  intsets {

  val t1 = new NonEmpty(3, Empty, Empty)
  val t2 = t1.incl(4)


}



abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
}

// singleton object
// singleton object is a value
// invariant: tree are sorted
object Empty extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
  override def toString = "."
  def union(other: IntSet): IntSet = other
}


class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  def contains(x: Int): Boolean = {
    if (x < elem ) left.contains(x)
    else if (x > elem) right.contains(x)
    else true
  }

  def incl(x: Int): IntSet = {
    if (x < elem) new NonEmpty(elem, left.incl(x), right)
    else if (x > elem) new NonEmpty(elem, left, right.incl(x))
    else this
  }

  override def toString: String = "{" + left + elem + right + "}"

  def union(other: IntSet): IntSet = {
    left.union(right).union(other).incl(elem) // contains left, right, elem and other
    // how do we know that it terminates, the sub calls are smaller
    // dynamic method dispatch model
  }
}


// IntSet is the superclass
// Empty, and NonEmpty subclasses
// Object is the root class of stuff


// subclasses implement base class abstract functions

abstract class Base {
  def foo = 1
  def bar: Int

}


class Sub extends Base {
  override def foo: Int = 2
  def bar = 3
}



// dynamic method dispatch
// Empty.contains(1)
// x this false

// similar to higher order functions
// as in it isn't known statically but is determined statisically
// objects in terms of higher-order functions?
// higher-order function in terms of objects?
