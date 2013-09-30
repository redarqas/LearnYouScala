trait Philosophical {
    def philosophize() {
        println("I consume memory, therefore i'am")
    }

    def whichSuper {
        println("Real implem of super is " + super.toString)
    }
}

trait HasLegs
class Animal

class Frog extends Animal with Philosophical with HasLegs {
    override def toString = "green Frog"
    override def philosophize() {
        println("It is not easy to " + toString)
    }
}

val frog: Philosophical = new Frog()
println(frog.philosophize())

//Dynamic super call in traits
println(frog.whichSuper)

class Point(val x:Int, val y:Int)

trait Rectangular {
    def tl : Point
    def br : Point
    def l = tl.x
    def r = br.x
    def w = r - l
}


class Rectangle(val tl: Point, val br: Point) extends Rectangular

abstract class Component extends Rectangular


val rect = new Rectangle(new Point(1,2), new Point(5,6)) 

println(rect.l)




