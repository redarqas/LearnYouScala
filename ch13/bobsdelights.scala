
package bobsdelights 


abstract class Fruit (
    val name: String,
    val color: String 
)

object Fruits  {
     object Apple extends Fruit("Apple", "Green")
     object Ananas extends Fruit("Ananas", "Yellow")
     object Orange extends Fruit ("Orange", "Orange")
     object Pear extends Fruit ("Pear", "Yellowish")
     val menu = List(Apple, Ananas, Orange, Pear)
 }




