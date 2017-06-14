package ScalaJS

import ScalaJS.Component.Main
import org.scalajs.dom.document

import scala.scalajs.js.JSApp

object App extends JSApp {
  def main(): Unit = {
    println("App starting...")
    //      document.getElementById("root").textContent = "scalaJs entry point"
    Main().renderIntoDOM(document.getElementById("root"))
  }
}
