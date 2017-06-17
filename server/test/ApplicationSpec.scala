import org.specs2.runner._
import org.junit.runner._
import play.api.test._

/**
  * Add your spec here.
  * You can mock out a whole application including requests, plugins etc.
  * For more information, consult the wiki.
  */
@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends PlaySpecification {
  // Set sequential execution
  sequential

  "Application" should {
    "send 404 on a bad request" in new WithApplication() {
      route(app, FakeRequest(GET, "/boum")) must beSome.which(status(_) == NOT_FOUND)
    }

    "render the index page" in new WithApplication {
      val Some(home) = route(app, FakeRequest(GET, "/"))
      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain("Your new application is ready.")
    }

    val baseUri = "http://localhost:"
    "click on HangMan redirect to /newgame" in new WithBrowser {
      browser.goTo(baseUri + port)
      browser.pageSource must contain("HangMan Game")
      browser.click("a[id='hangman']")
      browser.url() must contain("/new")
    }

    "click on HangMan redirect to /spa" in new WithBrowser {
      browser.goTo(baseUri + port)
      browser.pageSource must contain("HangMan Game SPA")
      browser.click("a[id='hangman-spa']")
      browser.url() must contain("/hangperson/spa")
    }
  }
}
