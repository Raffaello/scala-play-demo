package ScalaJS.Component

import ScalaJS.View
import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.vdom.html_<^._

object Show {
  val component = ScalaComponent.builder[RouterCtl[View]]("Show")
    .render_P(ctl =>
      <.div(^.cls := "panel-body",
        <.h2("Guess a letter"),
        <.p("Wrong Guesses:",
          <.span(^.cls := "text-guesses","wrong_guesses")
        ),
        <.p("Word so far:",
          <.span(^.cls := "text-word", "word_with_guesses")
        ),
        <.p("error handling is a todo")

      )
    )
    .build
  def apply() = component
}
