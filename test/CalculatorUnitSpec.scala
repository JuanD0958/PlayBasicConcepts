import org.scalatestplus.play.PlaySpec
import services.Calculator

class CalculatorUnitSpec extends PlaySpec {

  "Service of SUM:" should {

    "Sum two numbers " in {
      val calculator = new Calculator()
      val result = calculator.sum(5, 2)
      result mustBe 7
    }
  }

  "Service of SUBTRACT" should {

    "Diference between two numbers " in {
      val calculator = new Calculator()
      val result = calculator.subtract(5, 2)
      result mustBe 3
    }
  }

  "Service of DIVIDE" should {

    "Divide between two numbers" in {
      val calculator = new Calculator()
      val result = calculator.divide(4, 2)
      result mustBe 2
    }


    "Ilegal division by ZERO" in {
      val calculator = new Calculator()
      an [ArithmeticException] must be thrownBy calculator.divide(5, 0)
    }
  }

  "Service of multiplication" should {

    "multiply two numbers" in {
      val calculator = new Calculator()
      val result = calculator.mult(5, 2)
      result mustBe 10
    }
  }
}