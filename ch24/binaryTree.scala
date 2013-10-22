//Tree : Why Traversable and Iterable, sometimes it's more easy and efficient to 
//implement foreach  
sealed abstract class Tree[+A] extends scala.collection.Traversable[A] {
    def singleton[U >: A](elem: U) = new Node(elem, EmptyTree, EmptyTree)
    def insert[U >: A <% Ordered[U]](elem: U) : Tree[U] = this match {
        case EmptyTree => singleton(elem)
        case o@Node(x, l, r) => if (x == elem) o
                                else if (x > elem)  new Node(x, l, r.insert(elem))
                                else new Node(x, l.insert(elem), r)
    }
    def foreach[B](f: A => B) = this match {
        case EmptyTree => ()
        case Node(x, l, r) => f(x); l.foreach(f); r.foreach(f)
    } 
}

case class Node[A](elem: A, l: Tree[A], r: Tree[A]) extends Tree[A]
case object EmptyTree extends Tree[Nothing]

