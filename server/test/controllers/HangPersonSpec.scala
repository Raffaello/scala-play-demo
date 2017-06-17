package controllers

import org.junit.runner._
import org.specs2.runner._
import play.api.mvc.Flash
import play.api.test._

@RunWith(classOf[JUnitRunner])
class HangPersonSpec extends PlaySpecification
{
  // Set sequential execution
  sequential

  "HangPerson" should  {
    val uri = "/hangperson"
    val uriNew = uri + "/new"
    val uriCreate = uri + "/create"
    "/hangperson redirect to 'hangperson/new'" in new WithApplication {
      val Some(response) = route(app, FakeRequest(GET, uri))
      status(response) must equalTo(SEE_OTHER)
      redirectLocation(response) must beSome(uriNew)
    }

    "GET 'hangperson/create' redirect to 'hangperson/show' " in new WithApplication {
      val Some(response) = route(app, FakeRequest(GET, uriCreate))
      status(response) must equalTo(SEE_OTHER)
      redirectLocation(response) must beSome(uri + "/show")
    }

    "POST 'hangperson/create' redirect to 'hangperson/show' " in new WithApplication {
      val Some(response) = route(app, FakeRequest(POST, uriCreate))
      status(response) must equalTo(SEE_OTHER)
      redirectLocation(response) must beSome(uri + "/show")
    }

    "GET 'hangperson/show' redirect to 'hangperson/new' " in new WithApplication {
      val Some(response) = route(app, FakeRequest(GET, uri + "/show"))
      status(response) must equalTo(SEE_OTHER)
      redirectLocation(response) must beSome(uriNew)
    }

    "GET 'hangperson/win redirect to 'hangperson/new'" in new WithApplication {
      val Some(response) = route(app, FakeRequest(GET, uri + "/win"))
      status(response) must equalTo(SEE_OTHER)
      redirectLocation(response) must beSome(uriNew)
    }

    "GET 'hangperson/lose redirect to 'hangperson/new'" in new WithApplication {
      val Some(response) = route(app, FakeRequest(GET, uri + "/lose"))
      status(response) must equalTo(SEE_OTHER)
      redirectLocation(response) must beSome(uriNew)
    }

    "work from within a browser" in new WithBrowser {
      browser.goTo("http://localhost:" + port + uri)
      browser.pageSource must contain("Hangperson")
    }

    "work from a browser click new game and go to show page" in new WithBrowser {
      browser.goTo("http://localhost:" + port + uriNew)
      browser.pageSource must contain("New Game")
      browser.click("input[id='newgame']")
      browser.url() must contain("/show")
    }
  }

  // these are useless test, but just for learning...
  "HangPerson VIEWS" should {
    "render new" in new WithApplication {
      contentAsString(views.html.HangPerson.newAction()(new Flash(Map("success" -> "test")))) must contain("Success!")
    }
  }
}
