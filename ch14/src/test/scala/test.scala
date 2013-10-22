import org.scalatest.Suite
import org.scalatest.FunSuite
import org.scalatest.junit.JUnit3Suite
import org.scalatest.testng.TestNGSuite


class TestSuite extends Suite {
   def testUniformElement() {
        val e = List(1,2,3)
        assert(e.length == 3)
    }
}

class TestFunSuite() extends FunSuite {
    val e = List(1,2,3)

    test("Test with ===") {
        
        //===, to show the compared values
        assert(e.length === 2)
    }

    test("test with expect") {
       expect(2) {
         e.length
       }
    }

    test("test an exception") {
        intercept[java.lang.ArithmeticException] {
            List(1/0,4)
        }
    }
}

class WrapJunit extends JUnit3Suite {
    val e = List(1,2,3)
    def testList() {
        assert(e.length === 3)
        expect(3) {
         e.length
       }
        intercept[java.lang.ArithmeticException] {
            List(1/0,4)
        }
    }
}


class WrapTestNg extends TestNGSuite {
    val e = List(1,2,3)
    def testList() {
        assert(e.length === 3)
        expect(3) {
         e.length
       }
        intercept[java.lang.ArithmeticException] {
            List(1/0,4)
        }
    }  
}


//BDD

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class ListSpec extends FlatSpec with ShouldMatchers {

   "A list" should "have a length equal to the number of elements " in {
      val e = List(1,2,3)
      e.length should be (3)
   }

   it should "have a head equal the first element" in {
      val e = List(1,2,3)
      e.head should be (1)
   }

}

//BDD : specs2 framework
import org.specs2.mutable._

object ListSpecs extends Specification {
    "A list" should {
          "have a length equal to the number of elements " in {
             val e = List(1,2,3)
             e must have length(3)
        }

        "have a head equal the first element" in {
          val e = List(1,2,3)
          e.head must be_==(3)   
        }        
    }
}


//Properties testing : TODO

















