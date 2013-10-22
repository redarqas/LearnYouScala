//Covariant type
trait Queue[T] {
    def head: T 
    def tail: Queue[T]
    def enqueue(e:T) : Queue[T] 
}

object Queue {
    def apply[T](elems: T*): Queue[T] = new QueueImp[T](elems.toList, Nil)

    class QueueImp[T] (private val leading: List[T], private val trailing: List[T]) extends Queue[T]{
     
     private val mirror = if (leading.isEmpty) new QueueImp(trailing.reverse, Nil) else this 
     def head = mirror.leading.head
     def tail = new QueueImp(mirror.leading.tail, mirror.trailing)
     def enqueue(e:T) = new QueueImp(leading, e::trailing)
  }
}




