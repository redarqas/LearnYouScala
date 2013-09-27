import LongLines.processFile

object FindLongLines {
    def main(args: Array[String]): Unit = {
      val width = args(0).toInt
      for(fileName <- args.drop(1)) {
         processFile(fileName, width)  
       } 
    }

}

