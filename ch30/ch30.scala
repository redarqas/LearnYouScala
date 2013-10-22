class Point(val x: Int, val y: Int) {
    override def equals(other: Any) = other match {
      case that: Point =>
        (that canEqual this) &&
        (this.x == that.x) && (this.y == that.y)
      case _ => false
    }
    override def hashCode = 41 * (41 + x) + y
    def canEqual(other: Any) = other.isInstanceOf[Point]
}

val p1, p2 = new Point(1, 2)

import scala.collection.mutable._
val coll = HashSet(p1)
coll contains p2


object Color extends Enumeration {
          val Red, Orange, Yellow, Green, Blue, Indigo, Violet = Value
}

class ColoredPoint(x: Int, y: Int, val color: Color.Value) extends Point(x, y) {
    override def hashCode = 41 * super.hashCode + color.hashCode
    override def equals(other: Any) = other match {
      case that: ColoredPoint =>
            (that canEqual this) &&
            super.equals(that) && this.color == that.color
      case _ => false
    }
    override def canEqual(other: Any) = other.isInstanceOf[ColoredPoint]
}

val cp = new ColoredPoint(1, 2, Color.Red)
p1 equals cp
cp equals p1
val pAnon = new Point(1, 1) { override val y = 2 }

HashSet[Point](p1) contains cp
HashSet[Point](cp) contains p1

val coll = List(p1)
coll contains p1
coll contains pAnon


trait Tree[+T] {
  def elem: T
  def left: Tree[T]
  def right: Tree[T]
}
object EmptyTree extends Tree[Nothing] {
 def elem = throw new NoSuchElementException("EmptyTree.elem")
 def left = throw new NoSuchElementException("EmptyTree.left")
 def right = throw new NoSuchElementException("EmptyTree.right")
}
class Branch[+T](
 val elem: T,
 val left: Tree[T],
 val right: Tree[T]
) extends Tree[T] {
    override def equals(other: Any) = other match {
     //not produce an unchecked warning because of  '_'
     case that: Branch[_] => this.elem == that.elem && this.left == that.left && this.right == that.right
     case _ => false
  }

  override def hashCode: Int =
          41 * (
            41 * (
              41 + elem.hashCode
            ) + left.hashCode
          ) + right.hashCode
}


val b1 = new Branch[List[String]](Nil,
                   EmptyTree, EmptyTree)
val b2 = new Branch[List[Int]](Nil,
                   EmptyTree, EmptyTree)

b1 == b2



