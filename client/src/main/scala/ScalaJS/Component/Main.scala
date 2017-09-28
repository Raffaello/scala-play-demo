package ScalaJS.Component


import ScalaJS.AppRouter.{Home, Play}
import ScalaJS.View
import helpers.HangPersonGame
import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.vdom.html_<^._

object Main {
  var game: HangPersonGame = new HangPersonGame("react")
  val component = ScalaComponent.builder[RouterCtl[View]]("Main")
    .render_P( ctl => <.div(
      <.p("ReactJS Hello World"),
      <.p(game.word),
      <.a(^.cls := "btn btn-default",ctl setOnClick Play ,"link")
    ))
    .build
  def apply() = component
}
