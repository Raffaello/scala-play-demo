package ScalaJS.Component

import ScalaJS.{AjaxClient, Api, View, randomWordResult}
import helpers.HangPersonGame
import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom.ext.Ajax

import scala.concurrent.Await
import scala.scalajs.js.JSON
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.language.postfixOps


object Show {
//  val getRandomWord: String = AjaxClient[Api].getRandomWord()
  val getRandomWord = Ajax.get("/hangperson/spa/api/randomWord")
    .map(xhr => JSON.parse(xhr.responseText).asInstanceOf[randomWordResult].word)

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
        <.p("error handling is a todo"),
        <.p(s"$getRandomWord")
      )
    )
    .build
  def apply() = component
}
