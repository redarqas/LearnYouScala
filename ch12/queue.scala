import scala.collection.mutable.ArrayBuffer

abstract class IntQueue {
    def get(): Int
    def put(x:Int)
}

class BasicIntQueue extends IntQueue {
    private val buf = new ArrayBuffer[Int]
    def get() = buf.remove(0)
    def put(x:Int) {buf += x}
}

trait Doubling extends IntQueue {
    abstract override def put(x: Int) {println("Doubling "+ x); super.put(x*2)} 
}

trait Incrementing extends IntQueue {
    abstract override def put(x: Int) {println("Increminting "+ x);super.put(x+1)}     
}

class MyQueue extends BasicIntQueue with Doubling with Incrementing {
    override def put(x:Int) {println("MyQueue " + x ); super.put(x-1)}
}

//super liearization : Cat -> FourLegged -> HasLegs -> Furry -> Animal -> AnyRef -> Any
class Animal
trait Furry extends Animal
trait HasLegs extends Animal
trait FourLegged extends HasLegs
class Cat extends Animal with Furry with FourLegged

