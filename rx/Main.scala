package common

import scala.language.postfixOps
import rx.lang.scala.Observable
import scala.concurrent.duration._

/**
 * Created by redarqas on 08/12/2013.
 */
object Main {

  //define some observables
  val ticks: Observable[Long] = Observable.interval(1 second)

  val evens: Observable[Long] = ticks.filter(_ % 2 == 0)

  val bufs : Observable[Seq[Long]] = ticks.buffer(2, 1)


  def main(args: Array[String]) : Unit = {
    val subscription = bufs.subscribe(b => println(b))

    readLine()

    subscription.unsubscribe()

  }


}
