import annotation._

//Tail rec annotations
object ch27  {
  @tailrec 
  def sumL(l: List[Int], r: Int) : Int = l match {
    case Nil => r
    case x::rs => sumL(rs, x + r) 
  } 
}


