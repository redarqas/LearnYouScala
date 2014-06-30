import scala.collection.JavaConversions._

object Solution {
    def solution(s: String): Int = {
        (1 to s.length).foldLeft(0){ (acc, i) => val sub = s.take(i) 
                                                   val count = countPrefix(s, sub, 0) * sub.length
                                                   if (count>acc) count else acc
                                     }
    }
    
    def countPrefix (s: String, p:String, r:Int) : Int = {
        if(s == "" | p.length > s.length) r 
        else if(s.startsWith(p)) countPrefix(s.substring(1), p, r+1)
        else countPrefix(s.substring(1), p, r)
    }

    

   def repeat[T](x: T) : Stream[T] = {
        x #:: repeat(x)
    } 
    
}
