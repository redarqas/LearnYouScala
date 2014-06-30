package common

import scala.language.postfixOps
import rx.lang.scala._
import scala.concurrent.duration._
import scala.util.{Try, Random, Success, Failure}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits._

import rx.lang.scala.subscriptions.Subscription
import rx.lang.scala.subscriptions.Subscription
import scala.util.Failure
import scala.util.Success
import rx.lang.scala.subjects.PublishSubject
import rx.lang.scala.subjects.AsyncSubject
import rx.concurrency.NewThreadScheduler
import scala.util.Failure
import scala.util.Success
import scala.util.Failure
import scala.util.Success


/**
 * Created by redarqas on 08/12/2013.
 */
object earth {

  val rand = new Random()
  case class GeoCoordinate(longitude : Long, latitude: Long)
  case class EarthQuake(magnitude: Double, location: GeoCoordinate)
  def usgs() : Observable[EarthQuake] = {

    Observable.interval(3 seconds).map{i =>
      EarthQuake(rand.nextInt(11), GeoCoordinate(i,i+360))
    }
  }

  object Magnitude extends Enumeration {
    def apply(magnitude: Double) : Magnitude = magnitude match {
      case m if (m <= 1)  => Micro
      case m if (m <= 2) => Minor
      case m if (m <= 3) => Light
      case m if (m <= 5) => Moderate
      case m if (m <= 6) => Strong
      case m if (m <= 8) => Major
      case _ => Great
    }
    type Magnitude = Value
    val Micro, Minor, Light, Moderate, Strong, Major, Great = Value
  }

  case class Country(name: String)
  val countries = List("Japan", "Pakistan", "India", "Iran", "Colombia")
  def reverseGeo(coord: GeoCoordinate) : Future[Country] = Future {
     Country(countries(rand.nextInt(5)))
  }

  val withCountry: Observable[Observable[(EarthQuake, Country)]] = usgs() map { quake =>
   val country = reverseGeo(quake.location)
   //Just to follow the course
   Observable(country.map(country => (quake, country)).value.get.get)
  }

  val merged:Observable[(EarthQuake, Country)] = withCountry.concat
  val byCountry : Observable[(Country, Observable[(EarthQuake, Country)])] = merged.groupBy{case (q,c) => c}
  def runningAverage(s: Observable[Double]): Observable[Double] = ???
  def runningAveragePerCountry: Observable[(Country, Observable[Double])] =byCountry.map {case (country, quakes) =>
    (country, runningAverage(quakes.map(_._1.magnitude)))
  }
  //Some observables
  /*def never(): Observable[Nothing] = Observable[Nothing](observer =>
    Subscription {}
  )

  def error[T](e: Throwable) : Observable[T] = Observable[T] (observer => {
    observer.onError(e)
    Subscription {}
  })

  def startWith[T](self: Observable[T])(xs: T*): Observable[T] = Observable[T](observer => {
    for (x <- xs) observer.onNext(x)
    self.subscribe(observer)
  })

  def filter[T](self: Observable[T])(p: T => Boolean) : Observable[T] = Observable[T](observer => {
    self.subscribe(
       (t:T) => if (p(t)) observer.onNext(t),
       (e: Throwable) => observer.onError(e),
       () => observer.onCompleted()
    )
  })
  def map[T,S](self: Observable[T])(f: T => S) : Observable[S] = Observable[S](observer => {
    self.subscribe(
      (t:T) => observer.onNext(f(t)),
      (e: Throwable) => observer.onError(e),
      () => observer.onCompleted()
    )
  })*/

  def formFuture[T](f:Future[T]): Observable[T] = {
    val channel = AsyncSubject[T]()
    f onComplete {
      case Success(v) => channel.onNext(v)
      case Failure(t) => channel.onError(t)
    }
    channel
  }

  //Subject
  val channel = PublishSubject[Long](1)
  val a = channel.subscribe(x => println("a : "+ x))
  val b = channel.subscribe(x => println("b : "+ x))
  channel.onNext(23)
  channel.onNext(2223)
  channel.onCompleted()
  //Scheduler
  /*def from[T](seq: Iterable[T])(implicit scheduler: Scheduler) : Observable[T] = Observable[T] ( observer => {
    val iter = seq.iterator
    scheduler.schedule (self => {
      if(iter.hasNext) {observer.onNext(iter.next); self()}
      else {observer.onCompleted()}
    })
   }
  )*/


  def main(args: Array[String]): Unit = {
    val quacks = usgs()
    val major = quacks map {q =>
      (q.location, Magnitude(q.magnitude))
    } filter { case (l, mag) =>
        mag >= Magnitude.Major
    }

    major.subscribe(_ match {
      case (l, mag) => println(s"Magnitude : ${l} quke at ${mag}")
    })
  }

}
