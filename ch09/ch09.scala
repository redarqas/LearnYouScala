//Simplify client code
def containsOdd(nums: List[Int]) = nums.exists(_ % 2 == 1)
def allOdd(nums: List[Int]) = nums.forall(_ % 2 == 1)
//currying

def curriedSum(x:Int)(y:Int) = x + y
def onePlus = curriedSum(1)_

def first(x:Int) = (y:Int) => x + y
def second = first(1)

//new control structure
def twice(op: Double => Double, x:Double) = op(op(x))
println(twice(_ + 1, 5))


def withPrintWriter(file: java.io.File) (op: java.io.PrintWriter => Unit) = {
  val writer = new java.io.PrintWriter(file)
  try { 
    op(writer)
  } finally {
    writer.close()
  }
}

//curly braces for one argument
val file = new java.io.File("date.txt")

withPrintWriter(file) {
    writer => writer.println(new java.util.Date)
}

//By-name parameter

var assertionEnabled = true
def myAssert(predicate: () => Boolean) = {
    if(assertionEnabled && !predicate())
      throw new AssertionError
} 

myAssert(() => 5 > 3)

def byNameAssert(predicate: => Boolean) = {
     if(assertionEnabled && !predicate)
      throw new AssertionError  
}

byNameAssert(5>3)

def boolAssert(predicate: Boolean) =
  if (assertionEnabled && !predicate)
   throw new AssertionError

boolAssert(5>3)

//Deffered evaluation
assertionEnabled = false
//the parameter is not evaluated before the call
byNameAssert(3/0 == 0)







