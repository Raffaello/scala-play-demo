package ScalaJS

import ScalaJS.Component.Main
import org.scalajs.dom.document

import scala.scalajs.js.JSApp

object App extends JSApp {
  def main(): Unit = {
    println("App starting...")
//    Main().renderIntoDOM(document.getElementById("root"))
    AppRouter.router().renderIntoDOM(document.getElementById("root"))
  }
}
