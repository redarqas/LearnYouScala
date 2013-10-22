class Cell[T] (init : T) {
    private[this] var current = init
    def get = current
    def set(x: T) = current = x
}

val c1 = new Cell[String]("abc")
val c2 : Cell[Any] = c1
val c2.set(1) //Will nott compile, assign an It to String
val s: String = c1.get
