object Color extends Enumeration {
    val Green, Red, Blue = Value
}

object Direction extends Enumeration {
    val North = Value("North")
    val South = Value("South")
    val Ouest = Value("Ouest")
    val East  = Value("East")
}

//Iterate

for( c <- Color.values) {
    println(c)
}

for( d <- Direction.values) {
    println(d)
}


//Value is path depedent type
def consumeColor(c: Color.Value) = println(c.id)

//Access by id
println(Direction(2))



