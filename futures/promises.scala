object promises {
  import scala.concurrent.Future
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.util.{ Try, Success, Failure }
  import scala.async.Async._
  import scala.concurrent.Promise

  //filter with promise
  def filter[T](self: => Future[T])(p: T => Boolean): Future[T] = {
    val promise = Promise[T]()
    self onComplete {
      case Failure(e) => promise.failure(e)
      case Success(r) => if (p(r)) promise.failure(new NoSuchElementException)
      else promise.success(r)

    }
    promise.future
  }
  //firstOf
  def firstOf[T](f1: Future[T], f2: Future[T]): Future[T] = {
    val p = Promise[T]()
    f1 onComplete { p.tryComplete }
    f2 onComplete { p.tryComplete }
    p.future
  }
  //Zip with promise
  def zipWith[T, S, R](f1: Future[T], f2: Future[S])(f: (T, S) => R): Future[R] = {
    val p = Promise[R]()
    f1 onComplete {
      case Failure(t) => p.failure(t)
      case Success(s1) => f2 onComplete {
        case Failure(t) => p.failure(t)
        case Success(s2) => p.success(f(s1, s2))
      }
    }
    p.future
  }
  //Zip with for
  def zipWith2[T, S, R](f1: Future[T], f2: Future[S])(f: (T, S) => R): Future[R] = {
    for {
      s1 <- f1
      s2 <- f2
      r = f(s1, s2)
    } yield r
  }
  //Zip with async
  def zipWith2[T, S, R](f1: Future[T], f2: Future[S])(f: (T, S) => R): Future[R] = async {
    f(await(f1), await(f2))
  }
  //Sequence
  def sequence[T](l: List[Future[T]]): Future[List[T]] = {
    val p = Promise[List[T]]()
    p.success(Nil)
    l.foldRight(p.future) { (e, acc) =>
      for {
        xs <- acc
        x <- e
      } yield x :: xs
    }
  }

}

