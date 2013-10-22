def qsort[T <: Ordered[T] ] (xs: List[T]) : List[T] = xs match {
   case Nil => Nil
   case (y::ys) => { val (sm,bi) = ys.partition(_ <= y) 
                     qsort[T](sm) ::: List(y) ::: qsort[T](bi) }  
}


class Person(val firstName: String, val lastName: String) extends Ordered[Person] {
  def compare(that: Person) = {
    val lastNameComparison =
      lastName.compareToIgnoreCase(that.lastName)
    if (lastNameComparison != 0)
      lastNameComparison
    else
      firstName.compareToIgnoreCase(that.firstName)
  }
  override def toString = firstName +" "+ lastName
}