import scala.annotation.tailrec

def sum(f: Int => Int, a: Int, b: Int): Int = {
  if (a > b) 0
  else f(a) + sum(f, a+1, b)
}


def id(x: Int): Int = x
def cube(x: Int): Int = x * x * x
def fact(x: Int): Int = if (x == 0) 1 else fact(x-1)

def sumInts(a: Int, b: Int): Int = sum(id,a,b)
def sumCubes(a: Int, b: Int): Int = sum(cube, a, b)
def sumFact(a: Int, b: Int): Int = sum(fact,a,b)

//function type A=>B

// def str = "abc"; println(str)

// literals ^
// anonmyous functions

(x: Int) => x * x * x
(x: Int, y: Int) => x * y

// equivalent, not using syntactic sugar, but doesn't work for some reason
//{def f(x: Int) = x * 1; f}

// using anonymous functions
def sumInts2(a: Int, b: Int) = sum(x => x, a,b)
def sumCubes2(a: Int, b: Int) = sum(x => x * x * x,a,b)

object exercise2{
  // tailrec version of sum
  def sum2(f: Int => Int)(a: Int, b: Int): Int = {
    @tailrec
    def loop(a: Int, acc: Int): Int = {
      if (a>b) acc
      else loop(a+1,f(a)+acc)

    }
    loop(a,0)
  }

  println(sum2(id)(1,0))



  //def sumInts3(a: Int, b: Int) = sum(x => x)
  //sumInts3(1,2)
}











