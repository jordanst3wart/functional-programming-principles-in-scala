package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {
      /*if(c<0 || r<0) {
        // throw exception of some type if negative input, or do something with negative input
      }*/

      if(c == 0 || c == r) 1
      else pascal(c-1, r) + pascal(c,r-1)
    }
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
      def bracIter(chars:List[Char],nbrac:Int): Boolean = {
        if (chars.isEmpty) nbrac==0
        else if (chars.head == ')' && nbrac<=0) false
        else {
          // for debugging
          //println(chars + " number of brackets: " + nbrac.toString)
          if (chars.head == ')') bracIter(chars.tail, nbrac - 1 )
          else if (chars.head == '(') bracIter(chars.tail,nbrac + 1)
          else bracIter(chars.tail, nbrac)
        }
      }
      bracIter(chars,0)
    }
  
  /**
   * Exercise 3
   *
   */
    def countChange(money: Int, coins: List[Int]): Int = {
      // money is 0 or greater
      def countIter(money: Int, coins: List[Int], sum: Int): Int = {
        if (money == 0) 1
        else if (money < 0 || coins.isEmpty) 0
        else {
            // iterates through the head, and creates a branch to iterate through the tail
            countIter(money - coins.head, coins, sum) + countIter(money, coins.tail, sum)
        }
      }

      // checks for preconditions of countIter
      val sum : Int = 0
      if(money <= 0) sum
      else countIter(money, coins, sum)
    }
  }
