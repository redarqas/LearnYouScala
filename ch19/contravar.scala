trait OutputChannel[-T] {
    def write(x: T) : Unit
}

class OSS extends OutputChannel[String] {
    def write(x: String) = {
        println("String : "+ x)
    }
}

class OSA extends OutputChannel[AnyRef] {
  def write(x: AnyRef) {
    println("AnyRef : "+ x)
  }   
}


def doClassicCovar(o: AnyRef) = println(o)

//usage of covariance
doClassicCovar("Jamal") // String is-an AnyRef

def doContraVar(o: OutputChannel[String], s:String) = o.write(s)

//usage of contravariance 
//OutputChannel[AnyRef] supports the same method write, wich can take AnyRef then a String as parameter
doContraVar(new OSA(), "Jamal") 

