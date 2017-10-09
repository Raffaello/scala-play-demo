package ScalaJS.Component

import ScalaJS.Styles.Common
import japgolly.scalajs.react
import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.html_<^._

import scala.language.implicitConversions

object Button {
  case class Props(onClick: Callback, style: Common.Value = Common.default)

//  val component = react.ScalaComponent.builder[String]("Button")
//      .render_P((_, props, children) => <.button(^.cls := "btn-"+ props))
}