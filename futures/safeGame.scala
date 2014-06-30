
object notSafe {

  sealed abstract class Coin {
    def value: Int
  }
  case object Gold extends Coin {
    def value = 10
  }
  case object Silver extends Coin {
    def value = 5
  }

  sealed abstract class Treasure
  case object Sword extends Treasure

  trait Adventure {
    def collectCoins(): TryList[Coin]
    def buyTreasure(coins: List[Coin]): Treasure
  }

  class notSafeGame() extends Adventure {
    def collectCoins(): List[Coin] = {
      if (obstacle())
        throw new Exception("Oops coins")
      else
        List[Coin](Gold, Silver)
    }
    def obstacle(): Boolean = {
      val rand = new java.util.Random
      rand.nextInt() > 0
    }

    def buyTreasure(coins: List[Coin]): Treasure = {
      val amount = coins.foldLeft(0)((acc, e) => e.value + acc)
      if (amount < treasurePrcie())
        throw new Exception("Oops")
      else
        Sword

    }

    def treasurePrcie(): Int = {
      val rand = new java.util.Random
      math.abs(rand.nextInt())
    }
  }
  //Exception are not materalized
  val game = new notSafeGame                      
  val coins = game.collectCoins()                 
  val treasure = game.buyTreasure(coins)          
}