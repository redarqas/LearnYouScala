class SlowAppendQueue[T] (l: List[T]) {
    def head = l.head
    def tail = new SlowAppendQueue(l.tail)
    def enqueue(x:T) = new SlowAppendQueue(l ++ List[T](x))
}


class Queue[T] private (private val leading: List[T], private val trailing: List[T]) {
    def this() = this(Nil, Nil)
    //def this(elems: T*) = this(elems.toList, Nil)

    private val mirror = if (leading.isEmpty) new Queue(trailing.reverse, Nil) else this 
    def head = mirror.leading.head
    def tail = new Queue(mirror.leading.tail, mirror.trailing)
    def enqueue(e:T) = new Queue(leading, e::trailing)
}

//Hide initialization
object Queue {
    def apply[T](elems: T*) = new Queue[T](elems.toList, Nil)
}





