package controllers

import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.mvc.Flash
import play.api.test.Helpers._
import play.api.test._

@RunWith(classOf[JUnitRunner])
class HangPersonSpec extends Specification
{
  // Set sequential execution
  sequential

  "HangPerson" should  {
    "/hangperson redirect to 'hangperson/new'" in new WithApplication {
      val Some(response) = route(app, FakeRequest(GET, "/hangperson"))
      status(response) must equalTo(SEE_OTHER)
      redirectLocation(response) must beSome("/hangperson/new")
    }

    "GET 'hangperson/create' redirect to 'hangperson/show' " in new WithApplication {
      val Some(response) = route(app, FakeRequest(GET, "/hangperson/create"))
      status(response) must equalTo(SEE_OTHER)
      redirectLocation(response) must beSome("/hangperson/show")
    }

    "POST 'hangperson/create' redirect to 'hangperson/show' " in new WithApplication {
      val Some(response) = route(app, FakeRequest(POST, "/hangperson/create"))
      status(response) must equalTo(SEE_OTHER)
      redirectLocation(response) must beSome("/hangperson/show")
    }

    "GET 'hangperson/show' redirect to 'hangperson/new' " in new WithApplication {
      val Some(response) = route(app, FakeRequest(GET, "/hangperson/show"))
      status(response) must equalTo(SEE_OTHER)
      redirectLocation(response) must beSome("/hangperson/new")
    }

    "GET 'hangperson/win redirect to 'hangperson/new'" in new WithApplication {
      val Some(response) = route(app, FakeRequest(GET, "/hangperson/win"))
      status(response) must equalTo(SEE_OTHER)
      redirectLocation(response) must beSome("/hangperson/new")
    }

    "GET 'hangperson/lose redirect to 'hangperson/new'" in new WithApplication {
      val Some(response) = route(app, FakeRequest(GET, "/hangperson/lose"))
      status(response) must equalTo(SEE_OTHER)
      redirectLocation(response) must beSome("/hangperson/new")
    }

    "work from within a browser" in new WithBrowser {
      browser.goTo("http://localhost:" + port + "/hangperson")
      browser.pageSource must contain("Hangperson")
    }

    "work from within a browser SPA" in new WithBrowser {
      browser.goTo("http://localhost:" + port + "/hangperson/spa")
      browser.pageSource must contain("Hang Person SPA")
    }

    "work from a browser click new game and go to show page" in new WithBrowser {
      browser.goTo("http://localhost:" + port + "/hangperson/new")
      browser.pageSource must contain("New Game")
      browser.click("input[id='newgame']")
      browser.url() must contain("/show")
      println(browser.url())
    }
  }

  // these are useless test, but just for learning...
  "HangPerson VIEWS" should {
    "render new" in new WithApplication {
      contentAsString(views.html.HangPerson.newAction()(new Flash())) must contain("New Game")
    }
  }
}
