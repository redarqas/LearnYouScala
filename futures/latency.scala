
object latency {
  import scala.concurrent.Future
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.util.{ Try, Success, Failure }


  trait Socket {
    def readMemory(): Future[Array[Byte]]
    def sendToEurope(packet: Array[Byte]): Future[Array[Byte]]
  }
  class FakeSocket extends Socket {
    def readMemory(): Future[Array[Byte]] = Future("Message".toArray.map(_.toByte))
    def sendToEurope(packet: Array[Byte]): Future[Array[Byte]] = Future("Message".toArray.map(_.toByte))
  }

  //usage
  val socket = new FakeSocket()
  socket.readMemory().onComplete {
    case Success(p) => socket.sendToEurope(p)
    case Failure(t) => Future.failed(t)
  }
  //Exract confiramtion
  val confirmation: Future[Array[Byte]] = for {
    msg <- socket.readMemory()
    conf <- socket.sendToEurope(msg)
  } yield conf

  //with flatmap
  val confirmation2: Future[Array[Byte]] = socket.readMemory().flatMap(msg => socket.sendToEurope(msg))

  //retry with recu
  def retry[T](times: Int)(block: => Future[T]): Future[T] = {
    if (times == 0) Future.failed(new Exception("Oops"))
    else block fallbackTo {
      retry(times - 1)(block)
    }
  }
  //retry with fold
  def ntry[T](n: Int)(block: => Future[T]): Future[T] = {
    val range = (1 to n)
    val init: Future[T] = Future.failed(new Exception("Oops"))
    range.foldLeft(init)((acc, f) => acc recoverWith { case _ => block })
  }

  

}

