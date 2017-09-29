package ScalaJS.Component

import ScalaJS.AppRouter.Play
import ScalaJS.View
import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.vdom.html_<^._

object Main {
  val component = ScalaComponent.builder[RouterCtl[View]]("Main")
    .render_P( ctl =>
      <.div(^.cls := "panel-footer",
        <.a(^.cls := "btn btn-primary", ^.id := "newgame", ^.value := "New Game", ctl setOnClick Play)
      )
    )
    .build

  def apply() = component
}
