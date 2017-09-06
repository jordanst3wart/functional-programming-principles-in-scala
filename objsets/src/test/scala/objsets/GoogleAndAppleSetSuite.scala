package objsets

/**
  * Created by jordan on 15/07/17.
  */

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.concurrent.TimeLimitedTests
import org.scalatest.junit.JUnitRunner
import org.scalatest.time.Millis
import org.scalatest.time.Span

@RunWith(classOf[JUnitRunner])
class GoogleAndAppleSetSuite extends FunSuite with TimeLimitedTests {
  val timeLimit = Span(800, Millis)

  test("all TweetSet"){
    GoogleVsApple.allTweets
  }

  test("apple TweetSet"){
    GoogleVsApple.appleTweets foreach println
  }

  test("google TweetSet"){
    GoogleVsApple.googleTweets foreach println
  }

  //test("apple & google") {
  //  GoogleVsApple.trending
  //}

}
