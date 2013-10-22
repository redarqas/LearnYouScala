trait OutputChannel[-T] {
    def write(x: T)
}

val so = new OutputChannel[Int] {
    def write(x: Int) = println(x+1)
}

val ao = new OutputChannel[Any] {
    def write(x: Any) = println(x)
}

def consumeSO(so: OutputChannel[Int], x: Int) = so.write(x)

//Contravariance : liskov => parameter is less, and porvide more (same Unit result)
//then OutputChannel[Any] is  subtype of OutputChannel[Int]

consumeSO(so, 1)
consumeSO(ao, 1)

class Publication(val title: String)
class Book(title: String) extends Publication(title)

object Library {
  val books: Set[Book] = Set(
      new Book("Programming Scala"),
      new Book("Ubik")
    )

  def printBookInfo(f : Book => AnyRef) {
    for(book <- books) println(f(book))
  }   
}

object Customer extends App{
    type LiskovLess = Publication
    type LiskovMore = String
    def getTitle(pub: LiskovLess) : LiskovMore = pub.title
    Library.printBookInfo(getTitle)
}






