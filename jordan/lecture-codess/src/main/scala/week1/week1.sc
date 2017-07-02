import scala.annotation.tailrec
//package week1

// other stuff
def square(x: Int) = x * x


def sumOfSquares(x: Int,y: Int): Int ={
  square(x) + square(y)
}


square(2)

def first(x: Int, y:Int) = x


// loop stuff
//def loop: Boolean = loop

//def x = loop

object NewtonEstimate {

  def sqrt(x: Double) = {
    def Iter( estim: Double, error: Double): Double = {
      val isGoodEnough = math.abs(x - estim * estim)/x < error
      lazy val improve = (estim + x / estim)/2
      if ( isGoodEnough ) estim
      else Iter(improve, 0.0001)
    }

    Iter(1,0.0001)
  }

  val xy = sqrt(100)
}



// blocks and lexical scope
object lexicalScope {

  val result = {
    val x = 1
    x + 1
  }

  val hello: Int = 1
  def foo = { hello } // takes hello from scope of the projects scope

  println(foo)
}









object factorial {

  def fact(n: Int): Int = {
    if(n == 0) 1
    else n * fact(n-1)
  }


  def factTail(n: Int): Int ={
    @tailrec
    def factIter(n:Int, sum: Int): Int = {
      if (n == 0) sum * 1
      else factIter(n - 1, sum * n)
    }
    factIter(n,1)
  }


}




