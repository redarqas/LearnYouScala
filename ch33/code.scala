import scala.collection.JavaConversions._

object Solution {
    def solution(arr: Array[Int], index: Int) : Int = {
        val (a1, a2) = arr.splitAt(index)
        val a3 = a2.splitAt(1)._2
        if (a1.sum == a3.sum) index
        else solution(arr, index + 1)
    }
}








