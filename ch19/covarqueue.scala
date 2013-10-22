class Queue[+T] protected (protected val in: List[T], protected val out: List[T]) {
    protected def this() = this(Nil,Nil)
    private def mirror: Queue[T] = if (!in.isEmpty) this else new Queue[T](out.reverse, Nil) 
    def head : T = mirror.head
    def tail : Queue[T] = new Queue[T](mirror.in.tail, mirror.out)
    def enqueue[U >: T](x: U) = new Queue[U](in, x::out)
}

object Queue {
    def apply[T](elems: T*) : Queue[T] = new Queue[T](elems.toList, Nil)
}

trait Fruit
class Apple extends Fruit
class Orange extends Fruit


