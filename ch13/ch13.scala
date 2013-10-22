import bobsdelights.Fruit
import bobsdelights.Fruits.{Apple => Pomme, Orange, Pear => _}
import bobsrockets.navigation.{launch => l}

object ch13  {
  def displayFruit(fruit: Fruit) {
     import fruit._
     println(name + "" + color)
  }
}

println(Pomme.name)
println(new l.Booster1)
//println(Pear.name) : because excuded


class Outer {
    class Inner {
        private val okForinner = 1
        private[this] val notOkForinner = 1
        private def f() {println("f")}
    
        class InnerMost {
          def g() {
            f()
           }
        }
       
        def yes() {val in = new Inner; println(in.okForinner)}

        //def no() {val in = new Inner; println(in.notOkForinner)} : limited to this


    }

    

    //(new Inner).f() : f not accessible from there
}

