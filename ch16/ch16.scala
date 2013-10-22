val fruits = "Orange"::"Pear"::"Ananas"::Nil
val h = fruits.head
val t = fruits.tail
val l = fruits.last
val i = fruits.init
val ie = fruits.isEmpty
//list patterns 

val List(a,b,c) = List(1,2,3)
val a :: b :: rs = fruits


def insert(x: Int, xs: List[Int]) : List[Int] = xs match {
    case Nil => List(x)
    case y::ys => if (x <= y) x::xs else y :: insert(x,ys) 
}

def isort(xs : List[Int]) : List[Int] = xs match {
    case Nil => Nil
    case x::rs =>  insert(x, isort(rs))
}


def append[T](xs: List[T], ys: List[T]) : List[T] = xs match {
    case Nil => ys
    case x::rs => x :: append(rs, ys) 
}

val rf = fruits.reverse

def rev[T](xs: List[T]) : List[T] = xs match {
    case Nil => Nil
    case x::rs => rev(rs) ++ List(x) 
}

val d = fruits.drop(1)
val t = fruits.take(1)
val s = fruits.splitAt(1)

//flatten
val f = fruits.flatten
//zip
val z = fruits.indices zip fruits
//zip with index
val zi = fruits.zipWithIndex
//unzip
val (first,second) = List(("Jamam", 'j'),("Claire", 'c')).unzip
//mkString
val str = fruits.mkString("Pre ", " sep ", " Post")
//Add String
val buf = new StringBuilder

val adds = fruits.addString(buf.append("les fruits sont : "), "( ", " , ", " )")

val arr = fruits.toArray
val lf = arr.toList == fruits

//to array with positions
val arr2 = new Array[Int](10) //new is important 
List(1,2,3) copyToArray (arr2, 3)

val it = fruits.iterator
println(it.hasNext)
println(it.next)

val map = List(1,2,3).map(_ + 1)
val flatmap = List(1,2,3).flatMap((x:Int) => x::x::Nil)
val ij = List.range(1,5,1) flatMap {
    i => List.range(1,i,1) map(j => (i,j))
}

val ijf = for( i <- List.range(1,5);
               j <- List.range(1,i)) yield (i,j)

//Ici le (x to x) n'est pas jerclé
val ijr = for( i <- 1 to 4;
               j <- 1 to i) yield (i,j)
val opl = List.range(1,10)
val filter = opl.filter(_ <= 5)
val find = opl.find(_ <= 5)
val partition = opl.partition(_ == 5)
val takewhile = opl.takeWhile(_<=5)
val dropWhile = opl.dropWhile(_ <= 5)
val span = opl.span(_ <= 5)
val forall = opl.forall(_ >0)
val exists = opl.exists(_ == 5)
//val foldSum = (0 :\ opl) (_ + _) 
val foldSum = opl.foldRight(0) (_ + _) 

def rev2[T](xs: List[T]) : List[T] = xs.foldLeft(List[T]()) ((acc,x) => x::acc)


val words = List("A","AA","AAA")
val sortedByLength = words sortWith (_.length > _.length)

val replicate = List.fill(5)('c')

val tabulate = List.tabulate(5)(n => n * n)

val concat = List.concat(List(1,2), List(1,2),List(1,2),List(1,2))

val zipped = (List('j', 'c'), List(1,2)).zipped.map((x,y) => x * y)
















 

