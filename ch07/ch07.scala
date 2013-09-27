import java.io.FileReader
import java.io.FileNotFoundException
import java.io.IOException
import java.net.URL
import java.net.MalformedURLException

// If control
val resultIf = if (3 > 6) "Yes" else "No"

var i = 3
while (i !=  0) {
  println(i)
  i -= 1
}

var line = "AAAAAAAAAAAAAAAAAAA"
do {
    println(line)
    line = line.tail
} while(line != "")

//For comprehension

val filesHere = (new java.io.File("./ch04")).listFiles
for (file <- filesHere
    if file.isFile
    if file.getName.endsWith(".scala"))
  println(file)

def fileLines(file: java.io.File) = scala.io.Source.fromFile(file,"ISO-8859-1").getLines.toList

def grep(pattern : String) = {
    for( file <- filesHere
         if file.isFile;
         l <- fileLines(file);
         trimmed = l.trim;
         ok= 3
         if trimmed.matches(pattern) )
     println(file.getName + " : " + trimmed ) 
}                                                           

grep("}")
val numbers = (17 to 20).toList
val vowles = List('a','e','y','u','i','o')
val sentence = "one day"

for( num <- numbers;
    plus = num + 3;
    if num > 15;
    c <- sentence;
    s = "Hello "+c;
    if !vowles.contains(c)) 
 yield((plus, s))

try {
    val f = new FileReader("input.txt")
} catch {
    case ex:FileNotFoundException => println("Fichier non trouvé")
    case ex:IOException => println("Probleme io")
} 

def defUrl(path: String) = {

    try { 
      new URL(path)
    } catch {
      case e: MalformedURLException => new URL("http://www.google.fr") 
    }

}

println("http://yahoo.fr")
println(defUrl("jamal"))

//matchs

val names = List("Jamal", "Claire", "Denis", "Holla")

for( name <- names) {
    name match {
        case "Jamal" => println("Homme")
        case "Claire" => println("Femme")
        case "Denis" => println("Homme")
        case _ => println("?")  
    }
    
}

val args = List("-rr.scala","era.txt","area.scala")

def searchForm(i:Int) : Int = {
    if (i >= args.length) -1
    else if (args(i).startsWith("-")) searchForm(i + 1)
    else if (args(i).endsWith(".scala")) i
    else searchForm(i + 1)
}

println(searchForm(0))

//Functional style

def makeRow(r: Int) = {
  val row = for( i <- 1 to 10) yield {
      val prod = (i * r).toString
      val padding = " " * (4 - prod.length)
      padding + prod 
  }
  row.mkString
}

def makeTbale() = {
   val t = for( i <- 1 to 10) yield makeRow(i)
   t.mkString("\n")
}

println(makeTbale)













