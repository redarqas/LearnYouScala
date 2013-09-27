import scala.io.Source

object LongLines  {

    def processFile(fileName: String, width: Int)  {
        for( line <- Source.fromFile(fileName).getLines) {
            processLine(line)
        }
        //Local function
        def processLine(line: String) = {
            if(line.length > width) println(fileName + " : " + line)
        }
    }
}

