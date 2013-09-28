import Element.elem

abstract class Element {
    def contents:  Array[String]
    def width: Int = contents(0).length
    def height: Int = contents.length
    def above(that: Element): Element = {
      val this1 = this widen that.width
      val that1 = that widen this.width
      elem(this1.contents ++ that1.contents)
    }
    def beside(that: Element): Element = {
      val this1 = this heighten that.height
      val that1 = that heighten this.height
      elem(
        for ((line1, line2) <- this1.contents zip that1.contents)
        yield line1 + line2)
    }
    def widen(w: Int): Element =
      if (w <= width) this
      else {
        val left = elem(' ', (w - width) / 2, height)
        var right = elem(' ', w - width - left.width, height)
        left beside this beside right
    }
    def heighten(h: Int): Element =
      if (h <= height) this
      else {
        val top = elem(' ', width, (h - height) / 2)
        var bot = elem(' ', width, h - height - top.height)
        top above this above bot
    }
    override def toString = contents mkString "\n"
    
    def displayNme() {
        println("ArrayElement implementation")
    }
}

//Factory object

object Element  {
    //Parametric field
    private class ArrayElement(
          val contents : Array[String]
        ) extends Element {
    
        override def displayNme() {
            println("ArrayElement implementation")
        }
    }
    
    //Super class constructor call
    private class LineElement(s: String) extends Element {
        def contents = Array(s)
        override val height = 0
        override val width = s.length
    }
    
    //Plymorphism and dynamic binding
    
    private class UniformElement(ch: Char, 
        override val height: Int,
        override val width: Int) extends Element {
        private val line = ch.toString * width
        def contents = Array.fill(height)(line)
    }
    def elem(ar: Array[String]) : Element= new ArrayElement(ar)
    def elem(s:String) : Element = new LineElement(s)
    def elem(c: Char, h:Int, w:Int) : Element = new UniformElement(c,h,w)

}






