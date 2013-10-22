trait Queue[T] {
    def head[T] : T
    def tail[T] : Queue[T]
    def enqueue[T] (e:T) : Queue[T] 
}


class Q[T](val l: List[T]) {
    
    def head = l match {
        case Nil => new Exception("Empty Queue")
        case (x::rs) => x 
    }

    def tail= l match {
        case Nil => new Exception("Empty Queue")
        case (x::rs) => new Q(rs) 
    }

    def enqueue(e:T) = l match {
        case Nil => new Q(List(e))
        case xs => new Q(xs ::: List(e)) 
    }
      
}