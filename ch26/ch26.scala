object EMail extends ((String, String) => String) {
    def apply(user: String, domain: String) = user + "@" + domain
    def unapply(str: String) : Option[(String, String)] = {
        val parts = str split "@"
        if(parts.length == 2) Some(parts(0), parts(1)) else None
    } 
}

def getEmail(s: String) = s match {
    case EMail(u,_) => u
    case _ => "Not found" 
}

//Patterns with zero one variable
object Twice {
   def apply(s: String) = s * 2
   def unapply(s: String) = {
        val l = s.length / 2
        val half = s.substring(0,l)
        if(half == s.substring(l)) Some(half) else None
    }

}

object UpperCase {
  def unapply(s: String) = s.toUpperCase == s
}

def useTwiceUpper(s: String) = s match {
    case EMail(Twice(x @ UpperCase()), domain) => "match " + x + " in domain " + domain 
    case _ => "No match"
}

useTwiceUpper("HAHA@gmail.com")

//Variable argument extractor
object Domain {
    def apply(parts: String*) = parts.reverse.mkString(".")
    def unapplySeq(s: String) : Option[Seq[String]] = Some(s.split("\\.").reverse)
}


def isTomInDotCom(s: String) = s match {
   case EMail("tom", Domain("com", _*)) => true
   case _ => false
}


object ExpandedEmail {
    def unapplySeq(s: String) : Option[(String, Seq[String])] = {
        val parts = s split "@"
        if (parts.length == 2)
          Some(parts(0), parts(1).split("\\.").reverse)
        else 
          None
    }
}

val ExpandedEmail(name, topDom, secondDom, subDoms @ _*) = "jamal@gmail.com.net"

//Regex

import scala.util.matching.Regex

//val Decimal = new Regex("""(-)?(\d+)(\.\d*)?""")
val decimal = """(-)?(\d+)(\.\d*)?""".r

val f = decimal findFirstIn "Price : 1.99"
val a = decimal findAllIn "Prices : 2.34 , 3.66"
a.foreach(println)
val p = decimal findPrefixOf "Price : 1.33"

//Extractor with regex

val decimal(sign, i, d) = "-1122.00"

def showIntegerpart(s: String) = for (decimal(_, i, _) <- decimal findAllIn s) println(i)


