def quickSort[T <: Ordered[T]] (xs: List[T]) : List[T] = xs match {
   case Nil => Nil
   case x::rs => {
      val smaller = for( e <- rs; if e <= x) yield e
      val bigger = for( e <- rs; if e > x) yield e
      quickSort(smaller) ::: List(x) ::: quickSort(bigger)
   } 
}


class Person(val f: String, val l: String) extends Ordered[Person] {
   def compare(that :Person) = {
      val lc = l.compareToIgnoreCase(that.l)
      if (lc != 0) 
        lc 
      else
        f.compareToIgnoreCase(that.f)
   }

   override def toString = f + " . " + l
}


