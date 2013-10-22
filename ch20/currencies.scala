
object Converter {
    var exchangeRate = Map(
      "USD" -> Map("USD" -> 1.0   , "EUR" -> 0.7596,
                   "JPY" -> 1.211 , "CHF" -> 1.223),
      "EUR" -> Map("USD" -> 1.316 , "EUR" -> 1.0   ,
                   "JPY" -> 1.594 , "CHF" -> 1.623),
      "JPY" -> Map("USD" -> 0.8257, "EUR" -> 0.6272,
                   "JPY" -> 1.0   , "CHF" -> 1.018),
      "CHF" -> Map("USD" -> 0.8108, "EUR" -> 0.6160,
                   "JPY" -> 0.982 , "CHF" -> 1.0  )
   ) 
}



abstract class CurrencyZone {
  
    type Currency <: AbstractCurrency
    val CurrencyUnit : Currency

    def make(a: Long) : Currency 

    abstract class AbstractCurrency  {
        val amount : Long
        def designation : String
        def + (c: Currency) : Currency = make(this.amount + c.amount)
        //def - (c: Currency) : Currency 
        private def decimals(n: Long): Int =
          if (n == 1) 0 else 1 + decimals(n / 10)
        override def toString =
          ((amount.toDouble / CurrencyUnit.amount.toDouble)
          formatted ("%."+ decimals(CurrencyUnit.amount) +"f") +" "+ designation)

        def from(other: CurrencyZone#AbstractCurrency): Currency =
          make(math.round(
            other.amount.toDouble * Converter.exchangeRate
              (other.designation)(this.designation)))
    }
}


object US extends CurrencyZone {

    type Currency = Dollar
    def make(a: Long) = new Dollar {val amount = a}

    abstract class Dollar extends AbstractCurrency {
        val designation = "USD"
    }

    object Dollar {
        implicit def dollarToEuro(x: Dollar): Euro.Euro = Euro.make(x.amount * 1)
    }

    val Cent = make(1)
    val Dollar = make(100)
    val CurrencyUnit = Dollar 

}


object Euro extends CurrencyZone {

    type Currency = Euro
    def make(a: Long) = new Euro {val amount = a}

    abstract class Euro extends AbstractCurrency {
        val designation = "EUR"
    }

    val Cent = make(1)
    val Euro = make(100)
    val CurrencyUnit = Euro 

}

//Pre-initialized field
//val r = new {
//    val amount = 30l
//} with Dollar

//Post-initailized field
//val r2 = new Dollar {
//    val amount = 32l
//}

