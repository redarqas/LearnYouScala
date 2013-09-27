import ChecksumAccumulator.calculate

object Seasons extends App {
    val seasons = List("Ete", "Automne", "Hiver", "Printemps")
    for(season <- seasons)
      println(season + " : " + calculate(season))
}


