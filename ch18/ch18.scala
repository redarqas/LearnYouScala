class BankAccount {
    private var bal: Int = 0
    def balance: Int = bal
    def deposit(amount: Int) {
        if (amount > 0) 
        bal += amount
    }

    def withdraw(amount: Int): Boolean = {
      if (amount > bal) false
      else {
        bal -= amount
        true
      }
    }
}

//Keyed is purely functional
class Keyed {
    def computeKey: Int = (1 to 1000000000).last
}

//MemoKeyed is purely fucntional, var is only for speed
class MemoKeyed extends Keyed {
    private var keyCache: Option[Int] = None
    override def computeKey: Int = {
        if(!keyCache.isDefined) keyCache = Some(super.computeKey)
        keyCache.get
    }
}

//Getter and setter automatically generated for var fields
class Time {
    //private[this] var h = 12
    //def hour : Int = h
    //def hour_=(x: Int) = h = x  
    //Previous is automatically generated for us
    //And equivalent to
    var hour = 12
    //If we have some validation we can define our getter/setter
    private[this] var m = 0
    def minutes:Int = m
    def minutes_=(x:Int) {
      require (0 <= x && x < 60)
      m = x
    }
}


class Thermometer {
    var celcius : Float = _
    //flexible mechanism to read, write, or compute the value of a private field
    def fahrenheit:Float = celcius * 9 / 5 + 32
    def fahrenheit_= (f:Float) {
        celcius = (f - 32) * 5 / 9
    }

    override def toString = fahrenheit + "F/" + celcius + "C"
}











