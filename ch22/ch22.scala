sealed abstract class L[+T] {
    def head : T
    def isEmpty : Boolean = true
    def tail : L[T]
    def length : Int = if (isEmpty) 0 else 1 + tail.length
    def drop(n: Int): L[T] = if (isEmpty) N 
                             else if (n <= 0) this
                             else tail.drop(n -1)
    def map[U](f: T => U) : L[U] = if (isEmpty) N
                                   else C(f(head), tail.map(f))
    def ::[U >: T](x: U): L[U] = new C(x, this)
    def :::[U >: T](l: L[U]): L[U] = if (l.isEmpty) this else l.head::(l.tail:::this)
}

case object N extends L[Nothing] {
    override def isEmpty = true
    def head = throw new NoSuchElementException("head of empty list")
    def tail = throw new NoSuchElementException("tail of empty list")
}


final case class C[T](head: T, tail: L[T]) extends L[T] {
    override def isEmpty = false 
}

//List buffer class 


