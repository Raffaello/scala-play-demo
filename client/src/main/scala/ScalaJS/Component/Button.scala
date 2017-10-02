package ScalaJS.Component

import ScalaJS.Styles.Common
import japgolly.scalajs.react.Callback
import scala.language.implicitConversions

object Button {
  case class Props(onClick: Callback, style: Common.Value = Common.default, addStyles:Seq = Seq())


}