trait Abstract {
    type T 
    def g(x: T) : T
    def f : T
    val i:T
    var j:T 
}

class Concrete extends Abstract {
    type T = String
    def g(x: String) = x
    val f = "We can implement a def by a val "
    val i = "val should be implemented by a val"
    var j = "var is implemented by a var to generate getter and setter"
}

//initialise Abstract vals

trait RationalTrait {
  val numerArg: Int
  val denomArg: Int
  require(denomArg != 0)
  private val g = gcd(numerArg, denomArg)
  val numer = numerArg / g
  val denom = denomArg / g
  private def gcd(a: Int, b: Int): Int =
  if (b == 0) a else gcd(b, a % b) 
  override def toString = numer +"/"+ denom
}

// Exception : RationalTrait is initialized before anonymous class
// because of numerArg and denomArg aret not available for RationalTrait initialization
//require will throw an exception 

//val r = new RationalTrait {
//    val numerArg = 1
//    val denomArg = 2
//}


//First solution : Pre-initialized fields
val r = new {
    val numerArg = 1
    val denomArg = 2
} with RationalTrait //Work only with "with" keyword, even if the base class is abstract and not trait

object oneHalf extends {
    val numerArg = 1
    val denomArg = 2
} with RationalTrait

class RationalClass(x: Int, y:Int) extends {
    val numerArg = x
    val denomArg = y
} with RationalTrait


trait LazyRationalTrait {
  val numerArg: Int
  val denomArg: Int
  
  lazy val numer = numerArg / g
  lazy val denom = denomArg / g
  
  private lazy val g = {
    require(denomArg != 0)
    gcd(numerArg, denomArg)
  }
  private def gcd(a: Int, b: Int): Int =
  if (b == 0) a else gcd(b, a % b) 
  override def toString = numer +"/"+ denom
}

val lz = new {
    val numerArg = 1
    val denomArg = 2
} with LazyRationalTrait





