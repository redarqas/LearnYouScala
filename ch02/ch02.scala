1 + 1
res0 * 2
val msg = "Hello world"
println(msg)
var greeting = "Hello world"
greeting = "Leave alone world"

val multiline = 
  "This is the next line"

def max(x: Int, y: Int) = if (x > y) x else y 

def greeting() = println ("Hello, world ")

def prependToAll[T](sep: T, xs: List[T]) : List[T] = xs match {
    case Nil => Nil
    case (y::ys) => sep::y::prependToAll(sep, ys)
}


 