//User tierce library : example Swing
import javax.swing._
import java.awt.event._


implicit def function2ActionListener(f: ActionEvent => Unit) = {
    new ActionListener {
        def actionPerformed(e: ActionEvent) = {
            f(e)
        }
    }
}

val button = new JButton

//Java solution : 
button.addActionListener(
  new ActionListener {
    def actionPerformed(event:  ActionEvent) {
        println("pressed")
    }
  }     
)

//Btter solution with function litterals
button.addActionListener((e : ActionEvent) => println("pressed")) //Will not compile

//*****************
// Rules
//*****************

//Only definitions marked implicit are available

//An inserted implicit must be in scope as single identifier
//Or be associated with the source or the traget of conversion

//One at time rule : only one implicit is tried

//Explicit first



//*****************************************
// Implicit conversion to an expected type
//*****************************************

//Just for example : not recomended
//Inmplicit coversion should enrich the type
implicit def doubleToInt(x: Double) = x.toInt

val i:Int = doubleToInt(3.5)
val j:Int = 3.5

//*****************************************
// Convert the receiver
//*****************************************

//Interoperating with new types

//implicit def intToRational(x: Int) = new Rational(x) : cf ch06

//Simulating new syntaxe
// 1 is coverted to ArrowAssoc, which has a -> method taht produces a tuple
val m = Map(1 -> "one", 2 -> "two")

//*****************************************
// Implicit parameters
//*****************************************

class PreferredPrompt(val preference: String)
class AdjVerb(val adj: String)

object Greeter {
    def greet(name: String)(implicit prompt: PreferredPrompt, adj: AdjVerb)= {
        println(prompt.preference + " " + name + " is " + adj.adj)
    }
}


object JamalPrefs {
    implicit val prompt = new PreferredPrompt("$> ")
    implicit val adj = new AdjVerb("cool")
}


//Make the implicit available
import JamalPrefs._

//implicit identifiers are import for compiler to match them to parameters
Greeter.greet("Jamal")

//def maxListUpBound[T <: Ordered[T]](xs: List[T]) : T = xs match {
//    case Nil => throw new IllegalArgumentException("empty list")
//    case List(x) => x
//    case (x::rs) => 
//      val maxRs = maxListUpBound(rs)
//      if (x > maxRs) x else maxRs   
//}

//orderer is custom named type
//Avoid using generic types: T => Boolean can match a lot of things

def maxListUpBound[T](xs: List[T])(implicit orderer: T => Ordered[T]) : T = xs match {
    case Nil => throw new IllegalArgumentException("empty list")
    case List(x) => x
    case (x::rs) => 
      val maxRs = maxListUpBound(rs) //orderer is implicit, then suplied by the compiler
      if (x > maxRs) x else maxRs   
}

//View bounds, type classes in haskell
//A implicit parameter is added
def maxList[T <% Ordered[T]](xs: List[T]) : T = xs match {
    case Nil => throw new IllegalArgumentException("empty list")
    case List(x) => x
    case (x::rs) => 
      val maxRs = maxListUpBound(rs) //orderer is implicit, then suplied by the compiler
      if (x > maxRs) x else maxRs   
}
























