trait Generator[+T] { self =>
  def generate: T
  def map[S](f: T => S): Generator[S] = new Generator[S] {
    def generate: S = f(self.generate)
  }
  def flatMap[S](f: T => Generator[S]): Generator[S] = {
    f(self.generate)
  }
}

val integers = new Generator[Int] {
  val rand = new java.util.Random
  def generate: Int = rand.nextInt()
}

val booleans = integers map (i => i > 0)

def pairs[T,U](t: Generator[T], u: Generator[U]): Generator[(T, U)] = for {
  i <- t
  j <- u
} yield (i, j)

val pairs2 = integers flatMap(i => booleans map(b => (i,b)))

def single[T](x: T) : Generator[T] = new Generator[T] {
    def generate: T = x
}

def emptyList = single(Nil)

def lists: Generator[List[Int]] = for {
  isEmpty <- booleans
  list <- if (isEmpty) emptyList else nonEmptyList
} yield list

def nonEmptyList : Generator[List[Int]] = for {
  h <- integers
  list <- lists
} yield h::list


def test[T](g: Generator[T], times : Int = 100)(f: T => Boolean): Unit = {
  for (i <- 0 until times) {
    val v = g.generate
    assert(f(v), "test filed fo : " + v)
  }
}

val testList: ((List[_], List[_])) => Boolean = {case (xs, ys) => (xs ++ ys).length > xs.length}


//test(pairs(lists, lists))(testList) 
