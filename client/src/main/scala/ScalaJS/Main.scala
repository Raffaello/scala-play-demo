package ScalaJS

import org.scalajs.dom
import scala.scalajs.js

object Main extends js.JSApp {
  def main(): Unit = {
    dom.document.getElementById("scalajsShoutOut").textContent = "scalaJs entry point"
  }
}
