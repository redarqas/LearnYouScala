// Paremeterize by value
val big = new java.math.BigInteger("12345")
//Paremeritze by type and value
val greetSrings:Array[String] = new Array[String](3)
greetSrings(0) = "Scala "
greetSrings(1) = "is "
greetSrings(2) = "fun "
//for comprehension with ranges
for (g <- 0 to 2)
  println(g)
//Object methods call equivalence
//get
println(greetSrings.apply(0))
//update
greetSrings.update(1, "is very ")
println(greetSrings.mkString)

//Array init 
val numNames : Array[String] = Array("1", "2", "3")

//Lists 

val oneTwTree : List[Int] = List(1,2,3)
println(oneTwTree.apply(0) == oneTwTree(0))

//append
val first:List[Int] = List(1,2,3)
val second:List[Int] = List(4,5,6)
val third:List[Int] = first ::: second

//cons (prepend)
val initList = 1::2::3::Nil
val forth = 0 :: initList

//Some usefull methods
val myList = (1 to 20).toList
val h = myList.head
val t = myList.tail
val i = myList.init
val l = myList.last
val c = myList.count(_ > 7)
val c2 = myList.filter(_ > 7).length
val dl = myList.drop(10)
val dr = myList.dropRight(10)
val or = myList.exists(_ > 20)
val and = myList.forall(_ % 2 == 0)
val noreturn = myList.foreach(print)
val isEmpty = myList.isEmpty
val map = myList.map(_ * 2)
val string = myList.mkString(" , ")
val remove = myList.filter(_ % 2 != 0)
val sort = myList.sortWith(_ != _ )

//tuples 
val pair = (1, "Scala")
println(pair._1)
println(pair._2)

//Sets and maps : immutable by default

//reassign var for immutable Set
var jetSet = Set(1,2)
jetSet += 3
//Use mutable version
val numSet = scala.collection.mutable.Set(1,2)
numSet += 3
println(numSet)
//mutable Map

val treasureMap : scala.collection.mutable.Map[Int, String] = scala.collection.mutable.Map[Int, String]()
treasureMap += (1 -> "One")
treasureMap += (2 -> "Two")
treasureMap += (3 -> "Three")
//Imutable
val phones:Map[Int, String] = Map[Int, String] (1 -> "066666", 2 -> "0999999")
println(phones(2))
println(phones.get(0))



