/*

Pattern matching is a good fix for decomposition.


Expr
- Number
- Sum
- Prod
- Var

Methods
- eval
- show
- simplify

Other methods:
- Classification and access methods: quadratic explosion
- Type tests and casts: unsafe, low-level
- Object-oriented decomposition: does not always work, need to touch all classes to add a new method.
It didn't work for methods that where non-local.

*/

//package week4

trait Expr // you can put the match in the Expr Trait
case class Number(n: Int) extends Expr
case class Sum(e1 : Expr, e2 : Expr) extends Expr
case class Prod(e1: Expr, e2: Expr) extends Expr
case class Var(str: String) extends Expr

// creates factory objects automatically that allows something like: Number(1) to create a number

// generalization of switch but to class hierarchies


def eval(e: Expr): Int = e match {
  case Number(n) => n
  case Sum(e1,e2) => eval(e1) + eval(e2)
  case Prod(e1,e2) => eval(e1) * eval(e2)
  //case Var(str) => str
}
def show(e: Expr): String = {
  def checkChild(sub: Expr): String = sub match {
    case Sum(e1,e2) => "(" + show(Sum(e1,e2)) + ")"
    case _ => show(sub)
  }

  e match {
    case Number(n) => n.toString
    case Sum(e1, e2) => show(e1) + " + " + show(e2)
    case Prod(e1, e2) => checkChild(e1) + " * " + checkChild(e2)
    case Var(str) => str
  }

}

/*
def checkChild(sub: Expr): String = sub match {
  case Sum(e1,e2) => "(" + show(Sum(e1,e2)) + ")"
  case _ => show(sub)
}
*/



show(Sum(Number(1),Number(2)))
show(Prod(Sum(Number(2),Number(1)), Var("y")))
show(Sum(Prod(Number(2),Var("x")), Var("y")))

/*

The expression problem is the name of this problem.


 */



