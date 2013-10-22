class Queue[+T] protected (
    protected[this] var leading: List[T], //private[this] reassignable fields do not break variance rules
    protected[this] var trailing: List[T]
  ) {
    private def mirror() =
      if (leading.isEmpty) {
        while (!trailing.isEmpty) {
          leading = trailing.head :: leading
          trailing = trailing.tail
     }  
    }
    def head: T = {
      mirror()
      leading.head
    }
    def tail: Queue[T] = {
      mirror()
      new Queue(leading.tail, trailing) 
    }
    def enqueue[U >: T](x: U) =
      new Queue[U](leading, x :: trailing)
}

object Queue {
    def apply[T] (elems: T*) = new Queue[T] (elems.toList, Nil)
}


class Vehicle

class Car extends Vehicle
class Renault extends Car

class Truck extends Vehicle
class Mercedes extends Truck


object QueueTest {
    def main(args: Array[String]): Unit = {
       val q1 : Queue[Renault] = Queue(new Renault)
       val q2 = q1.enqueue(new Mercedes) //will result Queue[Vehicle]
       val q3 = q2.enqueue(1) // will result Queue[Any]

    }
    
}