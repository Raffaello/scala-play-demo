package controllers

import org.junit.runner.RunWith
import org.specs2.matcher.JsonMatchers
import org.specs2.runner.JUnitRunner
import play.api.test.{FakeRequest, PlaySpecification, WithApplication, WithBrowser}
import play.api.libs.json._


@RunWith(classOf[JUnitRunner])
class HangPersonSpaSpec extends PlaySpecification with JsonMatchers {
  "work from within a browser SPA" in new WithBrowser {
    browser.goTo("http://localhost:" + port + "/hangperson/spa")
    browser.pageSource must contain("Hang Person SPA")
  }

  "random word in JSON response format" in new WithApplication {
    val Some(response) = route(app, FakeRequest(GET, "/hangperson/spa/randomWord"))
    status(response) must equalTo(OK)
    contentType(response) must beSome("application/json")

    val wordLength = (contentAsJson(response) \ "word").getOrElse(JsString("")).as[String].length()
    contentAsString(response) must /("word" -> s"[a-z]{${wordLength}}".r)
  }
}
