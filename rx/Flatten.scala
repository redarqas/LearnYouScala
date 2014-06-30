package common

import scala.language.postfixOps
import rx.lang.scala.Observable
import scala.concurrent.duration._

object Flatten {

  val xs : Observable[Int] = Observable[Int](1,2,3)
  /*val xss : Observable[Observable[String]] = xs.map{ x =>
   Observable.interval(x seconds).map(_ => "Show : " + x.toString).take(2)
  }
  val zs: Observable[String] = xss.flatten*/
  val zs : Observable[String] = xs.flatMap{ x =>
    Observable.interval(x seconds).map(_ => "Show : " + x.toString).take(2)
  }

  def main(args: Array[String]): Unit = {
   zs.subscribe(i => println(i))

  }

}
