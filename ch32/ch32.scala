import scala.actors.Actor._

object SillyActor extends Actor {
    def act() {
        for( i <- 0 to 10) {
            println("I'm acting! "+ i)
            Thread.sleep(100)
        }
    }
}

val SeriousActor  = actor {
        for( i <- 0 to 10) {
            println("To be or not to be!" +  i)
            Thread.sleep(100)
        }
    }

SillyActor.start()
//SeriousActor.start()

val echoActor = actor {
 while (true) {
   receive {
     case msg => println("received message: "+ msg)
    }
 } 
}

echoActor ! "Hello world"

val intActor = actor {
   receive {
      case x: Int => println("Got an Int: "+ x)
   } 
}

intActor ! 34

object NameResolver extends Actor {
   import java.net.{InetAddress, UnknownHostException}
   
   def act() {
          loop {
            react {
              case (name: String, actor: Actor) =>
                actor ! getIp(name)
              case msg =>
                println("Unhandled message: " + msg)
            }
          } 
    }

   def getIp(name: String): Option[InetAddress] = {
            try {
              Some(InetAddress.getByName(name))
            } catch {
              case _:UnknownHostException => None
            }
   }    
}


NameResolver.start()

NameResolver ! ("www.scala-lang.org", self)
