package controllers

import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import play.api.test.{PlaySpecification, WithBrowser}

@RunWith(classOf[JUnitRunner])
class HanPersonSpaSpec extends PlaySpecification {
  "work from within a browser SPA" in new WithBrowser {
    browser.goTo("http://localhost:" + port + "/hangperson/spa")
    browser.pageSource must contain("Hang Person SPA")
  }
}
