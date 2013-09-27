//first-class functions

val increase = (x:Int) => x + 1

val lostNumbers = (1 to 3).toList

lostNumbers.foreach((x:Int) => println(x)) 
lostNumbers.filter((x:Int) => x > 2)

//Short forms
lostNumbers.filter(_ > 2)

//place holder precise
val somme1 = (x:Int, y:Int) => x + y
val somme2 = (_:Int) + (_:Int) 

//partially applied functions
def tuple3(x:Int, y:Int, z:Int) = (x,y,z)
val tuple3_ = tuple3 _
def tuple2 = tuple3(_:Int, 2,_:Int)

//Closure
def makeIncreaser(n : Int) = (x:String) => (n, x * n)
val inc1 = makeIncreaser(2)
println(inc1("Jamal"))

//currify function
def makeIncreaser2(n:Int)(x:String) = (n, x * n)
val inc2 = makeIncreaser2(2) _
println(inc2("Claire"))

//repeated parameters

def echo(args: String*) = {
    for( arg <- args) {
        println(arg)
    }
}

val test = List("AAA", "BBB")
echo(test : _*)

//named parameters

def speed(distance:Float, time:Float = 30) = distance/time

println(speed(time=30, distance=120))
println(speed(30))

//Not tail-recursive : usage of recursive call

def boom(n: Int) : Int = {
   if (n == 0) throw new Exception("boom")
   else boom(n - 1) + 1 
}

//Not tail-recursive : indirect recursive call
def isOK(x: Int) : Boolean = {
    if (x == 0) true else isNotOK (x-1)
}

def isNotOK(x: Int) : Boolean = {
    if(x == 0) false else isOK(x-1)
}

println(isOK(3))

//Not tail-recursive : recusive call using value function
val funVal = realFun _

def realFun(x:Int) {
    if(x != 0) {println(x); funVal(x-1)}
}

realFun(3)

//tail recursive
def baam(n: Int) : Int = {
    if(n==0) throw new Exception("baam")
    else baam(n-1)
}   




