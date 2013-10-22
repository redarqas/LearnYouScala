//Travesable : foreach
val l = List(1,2,3)
l.foreach(println)
//Iterable : defines iterator
val it = l.iterator
println(it.next())
val group = l.grouped(2)
println(group.next)
println(group.next)
if(group.hasNext) println(group.next)
val slid = l sliding 2
println(slid.next())



//Sets
var s = Set(1,2,3)
s += 4
s -= 1
println(s)
//
val sm = scala.collection.mutable.Set(1,2,3)
sm += 4
sm -= 1
println(sm)
//Sorted set
val myOrdering = Ordering.fromLessThan[String] (_ < _)
val numbers = scala.collection.immutable.TreeSet.empty(myOrdering) + ("one", "two", "three", "four") 
val range = numbers range ("four", "two")
val from = numbers from ("three")

def f(x: String) = {
    println("I'm working"); Thread.sleep(10)
    x.reverse
}

val cache = collection.mutable.Map[String, String]()
def cachedF(s: String) = cache.getOrElseUpdate(s, f(s))

//Synchronized Maps
import scala.collection.mutable.{Map, SynchronizedMap, HashMap}

object mapMaker {
    def makeMap: Map[String, String] = 
      new HashMap[String, String] with SynchronizedMap[String, String] {
        override def default(key: String) = "Error no such element"
      }
}

//List list : Strean
import scala.collection.immutable.Stream
val s: Stream[Int] = 1 #:: 2 #:: Stream.empty
//fibonacci
def fibonacci(a: Int, b:Int) : Stream[Int] = a #:: fibonacci(b, a + b) 
//Easy element access
import scala.collection.immutable.Vector
val v = "One" +: "Two"+: Vector.empty
val upv = v.updated(1, "Three")
//immutable stacks
import scala.collection.immutable.Stack

val stack : Stack[Int] = Stack.empty.push(1).push(2)
for( e <- stack) println(e)
println(stack.top)
println(stack.pop.top)
//Immutable Queue
import scala.collection.immutable.Queue
val queue = Queue.empty.enqueue(List(1,2,3))
//Immutable Range
val range = 3 to 21 by 3
//List maps : use Maps instead
val lm = scala.collection.immutable.ListMap(1 -> "one", 2 -> "two")


//*********************
// Mutables 
//********************























