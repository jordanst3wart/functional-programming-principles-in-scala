// subtype List

// polymorphism
// subtyping: Instance of a subtype when required
// generics: Parameterise type with other types

/*
Two main areas:

- bounds
- variance

 */

// def assertAllPos(s: IntSet): IntSet
// def assertAllPos(Empty) = Empty
// def assertAllPos(NonEmpty())= NonEmpty or Exception
// not reflected in in type

// def assertAllPos[S <: IntSet](r: S): S = ...
// S is any type that conforms to IntSet

// S <: T means S is a subtype of T (upper bound)
// S >: T means S is a supertype of T


// [S >: NonEmpty] Supertypes of NonEmpty
// S could be NonEmpty, IntSet, AnyRef, or Any
// low bound

// [S >: NonEmpty <: IntSet] types between NonEmpty and IntSet

// NonEmpty <: Intset

// List[NonEmpty] <: List[IntSet] -> Covariant


// Array <: Array (arrays are covariant in Java)
// runtime exception ArrayStoreExeception
// java contains a type tag
// sort(Obj[]a) - needs to be covariant for this
// ^ before genetics in java 4


// Barbara Liskov (Liskov Substitution Principle)
/*

If A <: B (A subtype of B), then everything one can to do with a value of
type B one should also be able to do with a value of type A.

Arrays are not covariant in Scala.
*/


