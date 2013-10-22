sealed abstract class Expr
case class Var(name:String) extends Expr
case class Num(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr
class Test(val s:String) extends Expr
object Test {
    def apply(s:String) = new Test(s)
    def unapply(t:Test) = Some(t.s) //Unapply is necessary for constructor pattenrs

}

def simplifyTop(expr: Expr) : Expr = (expr: @unchecked)  match {
    case UnOp("-", UnOp("-", e)) => simplifyTop(e)
    case BinOp("+", e, Num(0)) => simplifyTop(e)
    case BinOp("*", e, Num(1)) => simplifyTop(e)
    //variable binding
    case UnOp("abs", e @ UnOp("abs", _)) => e
    case Test(_) => Var("v") 
    case _ => expr //order is important, catch-all in last position
}


def seqMatchers[T](l: List[T]) = l match {
    case List(_) => println("Singleton list")
    case Nil => println("Empty list")
    case _ => println("Long list")
}

def othMatchers[T](l: List[T]) = l match {
    case List(_,y) => println("Second element" + y)
    case (x::rs) => println("head " + x + " tail "+ rs)
    case _ => println("Long list")
}

def tupleDemo(t: (Int, String)) = t match {
    case (1, s) => println(1 + " : " + s)
    case _ => println("otherwise") 
}

//Non disponible en haskell
def generalSize(x: Any) = x match {
    case s:String => s.length
    case m:List[_] => m.length
    case _ => -1 
}

//Type erasure 

def isIntIntMap(x: Any) = x match {
    //case m:Map[Int,Int] => true : equivalent to, type are unchecked at runtime
    case m:Map[_,_] => 
    case _ => false 
}

//Type erasure exception : Array
def isStringArray (x: Any) = x match {
    case a:Array[String] => true
    case _ => false
}

//Pattern guards
def simplifyAdd(e:Expr) = e match {
    case BinOp("+", x, y) if x == y => BinOp("*", x, Num(2))
    case _ => e 
}

val e:Option[Int] = Some(3)

def showOption(x: Option[Int]) = x match {
    case Some(y) => println("Just "+y)
    case None => println("Nothing")
}

//Patterns everywhere
val (i,s) = (4,"s")
val List(x,y,z) = List(1,2,3)
val (x::y::rs) = 1::3::Nil

//Case sequences as partial functions

val somme : ((Any,Any),Any) => Int = {
    case ((x:Int,"msg"), y:Int) => 1
    case _ => 0
}

val withDefault: Option[Int] => Int = {
    case Some(x) => x
    case None => 0
}

//Patial function : function applied to somme cases
//val second : List[Int] => Int = {
//    case (_::y::_) => y 
//}

val second : PartialFunction[(List[Int], String),Int] = {
    case (_::y::_, _) => y 
}

//Patterns in for expressions
val m: Map[Int, String] = Map(1 -> "un", 2 -> "deux")
val ss = for( (i,s) <- m) yield s 

val jn = for( Some(x) <- List(Some(1), None, Some(2))) yield x





