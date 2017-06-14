package ScalaJS.Component

import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.vdom.html_<^._

object Main {
  val component = ScalaComponent.builder[Unit]("Main")
    .renderStatic(<.p("ReactJS Hello World"))
    .build
  def apply() = component()
}

