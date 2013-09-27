class ChecksumAccumulator {
    private var sum = 0
    def add(b: Byte) {sum += b}
    def checksum(): Int = ~(sum & 0xFF) + 1
}

//companion object : singleton
object ChecksumAccumulator {
    private val cache = scala.collection.mutable.Map[String, Int]()

    def calculate(s: String) : Int = {
        if (cache.contains(s)) {
           cache(s)  
        } else {
          val acc = new ChecksumAccumulator()
          s.foreach(c => acc.add(c.toByte))
          val cs = acc.checksum 
          cache += (s -> cs)
          cs
        }
    }
}

