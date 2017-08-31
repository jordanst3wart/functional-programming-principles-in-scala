
package listfun


object listfun{
  val nums = List(2, -4, 5, 7, 1)
  val fruits = List("apple","pineapple","orange","banana")


  // -4 filtered out
  nums filter( x => x > 0)

  nums filterNot( x => x > 0)

  // gets two lists, true, and false
  // combined filter and filterNot
  nums partition( x => x > 0)

  // takes while true
  nums takeWhile(x => x > 0)

  nums dropWhile(x => x > 0)

  // combines takeWhile and dropWhile
  nums span(x => x > 0)



}