package objsets

import TweetReader._

import scala.annotation.tailrec
import math.Ordering

/**
 * A class to represent tweets.
 */
class Tweet(val user: String, val text: String, val retweets: Int) {
  override def toString: String =
    "User: " + user + "\n" +
    "Text: " + text + " [" + retweets + "]"
}

/**
 * This represents a set of objects of type `Tweet` in the form of a binary search
 * tree. Every branch in the tree has two children (two `TweetSet`s). There is an
 * invariant which always holds: for every branch `b`, all elements in the left
 * subtree are smaller than the tweet at `b`. The elements in the right subtree are
 * larger.
 *
 * Note that the above structure requires us to be able to compare two tweets (we
 * need to be able to say which of two tweets is larger, or if they are equal). In
 * this implementation, the equality / order of tweets is based on the tweet's text
 * (see `def incl`). Hence, a `TweetSet` could not contain two tweets with the same
 * text from different users.
 *
 *
 * The advantage of representing sets as binary search trees is that the elements
 * of the set can be found quickly. If you want to learn more you can take a look
 * at the Wikipedia page [1], but this is not necessary in order to solve this
 * assignment.
 *
 * [1] http://en.wikipedia.org/wiki/Binary_search_tree
 */
abstract class TweetSet {

  /**
   * This method takes a predicate and returns a subset of all the elements
   * in the original set for which the predicate is true.
   *
   * Question: Can we implment this method here, or should it remain abstract
   * and be implemented in the subclasses?
   */
    def filter(p: Tweet => Boolean): TweetSet = filterAcc(p, new Empty())
  
  /**
   * This is a helper method for `filter` that propagetes the accumulated tweets.
   */
    def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet

  /**
   * Returns a new `TweetSet` that is the union of `TweetSet`s `this` and `that`.
   *
   * Question: Should we implment this method here, or should it remain abstract
   * and be implemented in the subclasses?
   */
    def union(that: TweetSet): TweetSet
  
  /**
   * Returns the tweet from this set which has the greatest retweet count.
   *
   * Calling `mostRetweeted` on an empty set should throw an exception of
   * type `java.util.NoSuchElementException`.
   *
   * Question: Should we implment this method here, or should it remain abstract
   * and be implemented in the subclasses?
   */
    def lestRetweeted: Tweet


    //def searchTreeHighestInt( acc: Tweet): Tweet

    //def searchTreeStr( target: Tweet ): Boolean
  
  /**
   * Returns a list containing all tweets of this set, sorted by retweet count
   * in descending order. In other words, the head of the resulting list should
   * have the highest retweet count.
   *
   * Hint: the method `remove` on TweetSet will be very useful.
   * Question: Should we implment this method here, or should it remain abstract
   * and be implemented in the subclasses?
   */

  // I think this would be better done with an orderedList, or orderedTree


    def descendingByRetweet: TweetList /*
      new Cons( mostRetweeted,remove(mostRetweeted).
        descendingByRetweet
      ) */
    /*def descendingByRetweet: TweetList = {
    // add to tail

    // next step is to order the tree / list based on retweets

      @tailrec
      def Iter(tweetSet: TweetSet, tweetList: TweetList): TweetList = {
        if(tweetSet.isEmpty) tweetList
        else {
          val tweet = tweetSet.lessRetweeted
          Iter(tweetSet.remove(tweet),
            new Cons(tweet, tweetList)
          )
        }
      }

      Iter(this, Nil) //new Cons(mostRetweeted, Nil)
    }*/

    val isEmpty: Boolean
  
  /**
   * The following methods are already implemented
   */

  /**
   * Returns a new `TweetSet` which contains all elements of this set, and the
   * the new element `tweet` in case it does not already exist in this set.
   *
   * If `this.contains(tweet)`, the current set is returned.
   */
  def incl(tweet: Tweet): TweetSet

  // def nothing(): TweetSet

  /**
   * Returns a new `TweetSet` which excludes `tweet`.
   */
  def remove(tweet: Tweet): TweetSet

  /**
   * Tests if `tweet` exists in this `TweetSet`.
   */
  //def contains(tweet: Tweet): Boolean

  /**
   * This method takes a function and applies it to every element in the set.
   */
  def foreach(f: Tweet => Unit): Unit
}

class Empty extends TweetSet {

  val isEmpty: Boolean = true

  //def nothing(): TweetSet = new Nil

  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet = acc

  def union(that: TweetSet): TweetSet = that

  def lestRetweeted: Tweet = throw new java.util.NoSuchElementException

  def searchTreeHighestInt( acc: Tweet): Tweet = throw new java.util.NoSuchElementException

  //def searchTreeStr( target: Tweet ): Boolean = acc

  def descendingByRetweet: TweetList = Nil

  /**
   * The following methods are already implemented
   */

  //def contains(tweet: Tweet): Boolean = false

  def incl(tweet: Tweet): TweetSet = new NonEmpty(tweet, new Empty, new Empty)

  def remove(tweet: Tweet): TweetSet = this

  def foreach(f: Tweet => Unit): Unit = ()
}

class NonEmpty(elem: Tweet, left: TweetSet, right: TweetSet) extends TweetSet {

  val isEmpty: Boolean = false

  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet = {
    def filterElem(): TweetSet = {
      if(p(elem)) acc.incl(elem)
      else acc
    }

    right.filterAcc(p,
      left.filterAcc(p,
        filterElem()
      )
    )
  }

  // maybe this could be implemented as an non-abstract method with a higher order function, but idk, maybe with foreach ???
  //def union(that: TweetSet): TweetSet = left.union(right).union(that).incl(elem)
  // https://www.geeksforgeeks.org/merge-two-balanced-binary-search-trees/
  /*def union(that: TweetSet): TweetSet = {
    // convert to lists and merge them
    val list1 = that.descendingByRetweet
    @tailrec
    def iter(reduc: TweetList, accum: TweetSet): TweetSet ={
      if(reduc.isEmpty) accum
      else iter(reduc.tail, accum.incl(reduc.head))
    }
    iter(list1,this)
  }*/

  /*def union(that: TweetSet): TweetSet = {
    that.foreach( x => this.incl(x) )
    this
  }*/

  /*
  def union(that: TweetSet): TweetSet = {
    that.foreach( elem => this.incl(elem) )
    this.accum2( x => this.incl(x) )
    this
    this.accum2(x => this.incl(x))
  }

  def accum2(f: Tweet => Tweetset): Tweetset= {
    f(elem)
  }*/

  def union(that: TweetSet): TweetSet = {
    var accum: TweetSet = this
    that.foreach(x => accum = accum.incl(x))
    accum
  }

  // left node is the highest
  /*def searchTreeHighestInt( x: Tweet): Tweet = {
    if (left.isEmpty) elem
    else left.searchTreeHighestInt()
  }*/

  /*def searchTreeStr( target: Tweet ): Boolean = {
    if (elem.text == target.text) true
    else {
      left.searchTreeStr(target)
      right.searchTreeStr(target)
      false
    }
  }*/


  // left node is the highest
  def lestRetweeted: Tweet = {
    if (left.isEmpty) elem
    else left.lestRetweeted
  }

  // I don't know if insertion is slower when the data structure is created
  // probably really slow as well
  def descendingByRetweet: TweetList = {
    /*new Cons( lestRetweeted, remove(lestRetweeted).
      descendingByRetweet) */
    @tailrec
    def iter(accum: TweetList, remainder: TweetSet): TweetList = {
      if (remainder.isEmpty) accum
      else {
        val lest = remainder.lestRetweeted
        iter(new Cons(lest, accum), remainder.remove(lest))
      }
    }
    val remainder = this
    iter(Nil, remainder)
  }

  /**
   * The following methods are already implemented
   */

  def incl(x: Tweet): TweetSet = {
    if (x.retweets < elem.retweets) new NonEmpty(elem, left.incl(x), right)
    else if (elem.retweets < x.retweets) new NonEmpty(elem, left, right.incl(x))
    else {
      if (x.text < elem.text) new NonEmpty(elem, left.incl(x), right)
      else if (elem.text < x.text) new NonEmpty(elem, left, right.incl(x))
      else this
    }
  }

  def remove(x: Tweet): TweetSet = {
    if (x.retweets < elem.retweets) new NonEmpty(elem, left.remove(x), right)
    else if (elem.retweets < x.retweets) new NonEmpty(elem, left, right.remove(x))
    else {
      if (x.text < elem.text) new NonEmpty(elem, left.remove(x), right)
      else if (elem.text < x.text) new NonEmpty(elem, left, right.remove(x))
      else left.union(right)
    }
  }

  def foreach(f: Tweet => Unit): Unit = {
    f(elem)
    left.foreach(f)
    right.foreach(f)
  }

  def toTweetList: TweetList = {
    //val list1 : TweetList = this.left.toTweetList
    //val list2 : TweetList = this.right.toTweetList

    //list1
    new Cons(this.elem, Nil)
  }

}


trait TweetList {
  def head: Tweet
  def tail: TweetList
  def isEmpty: Boolean
  def foreach(f: Tweet => Unit): Unit =
    if (!isEmpty) {
      f(head)
      tail.foreach(f)
    }
}

object Nil extends TweetList {
  def head = throw new java.util.NoSuchElementException("head of EmptyList")
  def tail = throw new java.util.NoSuchElementException("tail of EmptyList")
  def isEmpty = true
}

class Cons(val head: Tweet, val tail: TweetList) extends TweetList {
  def isEmpty = false
}


object GoogleVsApple {
  val google = List("android", "Android", "galaxy", "Galaxy", "nexus", "Nexus")
  val apple = List("ios", "iOS", "iphone", "iPhone", "ipad", "iPad")

  /*private def isIn(list: List[String], tweet: Tweet): Boolean = {
    list.exists(str => str.contains(tweet.text))
  }*/

  // descendingByRetweet

  //lazy val allTweets: TweetSet = TweetReader.allTweets
  val allTweets: TweetSet = TweetReader.allTweets

  //lazy val quarterTweets: TweetSet = TweetReader.allTweets

  // contains a word in google list
  //lazy val googleTweets: TweetSet = allTweets.filter( tweet => isIn(google,tweet))
  lazy val googleTweets: TweetSet = allTweets.filter( tweet => google.exists(str => str.contains(tweet.text)))

  // contains a word in the apple list
  //lazy val appleTweets: TweetSet = allTweets.filter( tweet => isIn(apple,tweet))
  lazy val appleTweets: TweetSet = allTweets.filter( tweet => apple.exists(str => str.contains(tweet.text)))
  // exists method of List, and contains method of java.lang.String

  //appleTweets.concat(googleTweets)

  /**
   * A list of all tweets mentioning a keyword from either apple or google,
   * sorted by the number of retweets.
   */
  //new Cons(new Tweet("","",0), Nil)//TweetReader.allTweets //
  lazy val trending: TweetList = googleTweets.union(appleTweets).descendingByRetweet

  // build a tree based on retweets rather text
}

object Main extends App {
  // Print the trending tweets
  GoogleVsApple.trending foreach println
}
