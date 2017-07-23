/*

Decomposing a




 */

/*
Expr
- Number
- Sum

 */

trait Expr {
  def isNumber: Boolean
  def isSum: Boolean
  def numValue: Int
  def leftOp: Expr
  def rightOp: Expr
}

class Number(n: Int) extends Expr {
  def isNumber: Boolean = true
  def isSum: Boolean = false






}

class Sum(e1: Expr, e2: Expr) extends Expr {




}

def eval(e: Expr): Int = {
  if (e.isNumber) e.numValue
  else if (e.isSum) eval(e.leftOp) + eval(e.rightOp)
  else throw new Error("Unknown expression " + e)
}

/*

Non-Solution:
Any class
def isInstanceOf[T]: Boolean
def asInstanceOf[T]: T


trait Expr {
  def eval: Int
  def show: String
}

class Number(n: Int) extends Expr {
  def eval: Int = n
}

class Sum(e1: Expr, e2: Expr) extends Expr {
  def eval: Int = e1.eval + e2.eval
}



// Simplify the expressions.
// OO Decomposition is good for some solutions.

 */







