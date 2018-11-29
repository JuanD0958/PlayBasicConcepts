import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.http.Status
import play.api.test.FakeRequest
import play.api.test.Helpers._
class CalculatorFunctionalSpec extends PlaySpec with GuiceOneAppPerSuite {


  "Calculator controller" should {

    "Send 200 on a good response SUM" in {
      val result = route(app, FakeRequest(GET, "/sum/3/and/5")).get
      status(result) mustBe Status.OK
      contentType(result) mustBe Some("text/plain")
    }


    "Send 200 on a good response SUBTRACT" in {
      val result = route(app, FakeRequest(GET, "/subtract/5/and/2")).get
      status(result) mustBe Status.OK
      contentType(result) mustBe Some("text/plain")
    }

    "Send 200 on a good response MULTIPLY" in {
      val result = route(app, FakeRequest(GET, "/mult/5/and/2")).get
      status(result) mustBe Status.OK
      contentType(result) mustBe Some("text/plain")
    }


    "Send 200 on a good response DIVIDE" in {
      val result = route(app, FakeRequest(GET, "/divide/10/in/2")).get
      status(result) mustBe Status.OK
      contentType(result) mustBe Some("text/plain")
    }
  }

}