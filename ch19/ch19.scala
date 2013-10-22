//Queue trait is Kind of : * -> *
//To create a concrete type, we ha to fix the paramaterized type : Queue[SomeType]

def doseCompile(concreteType : Queue[Int]) { println(concreteType)}

//Rigid variance type
class Cell[T] (init: T) {
  private[this] var current = init
  def get = current
  def set(x: T)  {
       current = x
   } 
}

//The probleme
//val c1: Cell[String] = new Cell[String]("abc")
//val c2: Cell[Object] = c1
//c2.set(1), => we assign an Int to a String !!
//That's why, unlike Java, Arrays are rigid 


 val q1 : Queue[Apple] = Queue(Apple,Apple)

