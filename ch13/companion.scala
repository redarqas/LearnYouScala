class Rockect {
    import Rockect._
    private def canGoHomeAgain = fuel > 20
}

object Rockect {
    private def fuel = 10
    def chooseStrategy(rocket : Rockect) = {
      if (rocket.canGoHomeAgain) 
        goHome()
      else 
        pickAStar()
    }
    def goHome() {}
    def pickAStar() {}
}