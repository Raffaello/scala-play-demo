package ScalaJS.Component

import ScalaJS.View
import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.vdom.html_<^._

object Show {
  val component = ScalaComponent.builder[RouterCtl[View]]("Show")
    .renderStatic(
//      <.div(
      <.p("Show game view")
//    )
  )
    .build
  def apply() = component
}
