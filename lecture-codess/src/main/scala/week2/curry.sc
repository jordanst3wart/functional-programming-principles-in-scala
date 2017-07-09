import exercise5.fact

def fact(x: Int): Int = if (x == 0) 1 else x * fact(x-1)

// returns function
object exercise3{
  def sum(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int = {
      if (a > b) 0
      else f(a) + sumF(a+1, b)

    }
    sumF
  }

  def sumInts: (Int, Int) => Int = sum(x => x)
  def sumCubes: (Int, Int) => Int = sum(x => x * x * x)
  def sumFact: (Int, Int) => Int = sum(fact)
}


// curried
object exercise4 {
  def sum(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 0
    else f(a)+sum(f)(a + 1,b)
  }

  def sumInts: (Int, Int) => Int = sum(x => x)
  def sumCubes: (Int, Int) => Int = sum(x => x * x * x)
  def sumFact: (Int, Int) => Int = sum(fact)
}


// product
object exercise5 {
  def product(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 1
    else f(a)*product(f)(a + 1,b)
  }

  // factorial in terms of product
  def fact(n: Int): Int = product(x => x)(1, n) // could actually be 0

  // did it!
  def sigma(f: Int => Int, op: (Int,Int) => Int)( a: Int, b: Int, unit: Int): Int = {
    if (a > b) unit
    else {
      val x: Int = f(a)
      val y: Int = sigma(f, op)(a + 1,b,unit)
      op(x,y)
    }
  }

  def mapReduce(f: Int => Int, combine: (Int, Int) => Int,zero: Int)(a: Int, b: Int): Int = {
    if (a > b) zero
    else combine(f(a), mapReduce(f,combine,zero)(a+1,b))
  }

}


println(fact(3))
def sum(x: Int,y: Int): Int = exercise5.sigma( x => x, (x,y) => x + y )(x,y,0)
sum(1,3)





