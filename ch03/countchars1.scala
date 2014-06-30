import scala.io.Source

if(args.length > 0) {
  val lines = Source.fromFile(args(0)).getLines.toList
  val maxWidth = lines.maxBy(_.length).length.toString.length
  def fromatLine(line: String) = {
     val width = line.length.toString.length
     val padding = " " * (maxWidth - width)
     line.length + padding + " | " +line 
  }
  lines.foreach(line => println(fromatLine(line)))
} else
Console.err.println("Please enter file name") 

