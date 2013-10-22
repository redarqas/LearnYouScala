class Food

abstract class Animal {
    type SuitableFood <: Food
    def eat(food: SuitableFood)
}

class Grass extends Food
class Cow extends Animal {
    type SuitableFood = Grass
    def eat(food: Grass) = println("Eats grass")
}

class Fish extends Food

class Dolphin extends Animal {
    type SuitableFood = Fish
    def eat(food: SuitableFood) = println("Eats fish")
}


val c1 = new Cow
val c2 = new Cow

c1.eat(new Grass)
c2.eat(new c1.SuitableFood)


//Compared to java

class Outer {
    def outerMethod = println("outer")
    class Inner {
        def innerMethod = Outer.this.outerMethod
    }
}

val outer = new Outer
//path-dependant type
val inner = new outer.Inner
inner.innerMethod


//Structural subtyping
class Pasture {
    var animals: List[Animal {type SuitableFood = Food}] = Nil
}

def using[T <: { def close() : Unit}, S](o: T)(f: T => S) = {
  val result = f(o)
  o.close
  result
}

using(new java.io.PrintWriter("date.txt")) {
    w => w.println(new java.util.Date)
}












