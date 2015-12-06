package controllers

import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.test.Helpers._
import play.api.test._

@RunWith(classOf[JUnitRunner])
class HangPersonSpec extends Specification
{
  "HangPerson" should  {
    "/hangperson redirect to 'hangperson/new'" in new WithApplication {
      val Some(response) = route(FakeRequest(GET, "/hangperson"))
      status(response) must equalTo(SEE_OTHER)
      redirectLocation(response) must beSome("/hangperson/new")
    }

    "GET 'hangperson/create' redirect to 'hangperson/show' " in new WithApplication {
      val Some(response) = route(FakeRequest(GET, "/hangperson/create"))
      status(response) must equalTo(SEE_OTHER)
      redirectLocation(response) must beSome("/hangperson/show")
    }

    "init session" in new WithApplication() {
      failure
    }.pendingUntilFixed("not written")

    "word with guesses" in new WithApplication {
      failure
    }.pendingUntilFixed

    "work from within a browser" in new WithBrowser {
      browser.goTo("http://localhost:" + port + "/hangperson")
      browser.pageSource must contain("hang person game")
    }
  }
}
