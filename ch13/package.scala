package object  bobsdelights {
    def displayFruit(fruit: Fruit) {
        import fruit._
        println(name +  " " + color)
    }
}


package printmenu {

import bobsdelights.Fruits
import bobsdelights.displayFruit

object PrintMenu {
  def main(args: Array[String]): Unit = {
     for(fruit <- Fruits.menu)
      displayFruit(fruit)
  }  
 }
}
  
