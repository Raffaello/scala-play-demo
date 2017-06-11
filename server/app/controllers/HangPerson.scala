package controllers

import javax.inject.Inject

import helpers.HangPersonGame
import play.api.mvc._
import play.api.Logger
import play.api.libs.ws.WSClient

import scala.concurrent.duration._
import scala.concurrent.Await
import scala.language.postfixOps
import scala.concurrent.ExecutionContext.Implicits.global
import forms.HangPersonForm
import play.api.i18n.MessagesApi
import play.api.i18n.I18nSupport

class HangPerson @Inject()(val messagesApi: MessagesApi, ws: WSClient) extends Controller with I18nSupport {
  var game: HangPersonGame = _

  def randomWord: String = {
    val URL = "http://watchout4snakes.com/wo4snakes/Random/RandomWord"
    val word = Await.result[String](ws.url(URL).post(Map("LastWord" -> Seq(""))).map {
      response => {
        Logger.debug("resp: " + response.body)
        response.body
      }

    }, 5 minute)

    Logger.debug("word: " + word)
    if (word.isEmpty)
    // TODO generate from a dictionary some random word. if WS is not working as aspected.
      "empty"
    else
      word
  }

  def newAction = Action {
    implicit request => {
      Ok(views.html.HangPerson.newAction())
    }
  }

  def create = Action {
    val word: String = randomWord
    game = new HangPersonGame(word)
    Redirect(routes.HangPerson.show())
  }

  def show = Action {
    implicit request => {
      if (game == null) {
        Redirect(routes.HangPerson.newAction())
      } else {
        game.checkWinOrLose match {
          case Some(true) => Redirect(routes.HangPerson.win())
          case Some(false) => Redirect(routes.HangPerson.lose())
          case _ => Ok(views.html.HangPerson.show(game.wrongGuesses, game.wordWithGuesses, HangPersonForm.HangPersonForm))
        }
      }
    }
  }

  def win = Action {
    implicit request => {
      if (null == game) {
        Redirect(routes.HangPerson.newAction()).flashing("error" -> "Game was not created!")
      } else {
        game.checkWinOrLose match {
          case Some(true) => Ok(views.html.HangPerson.win(game.word))
          case _ => Redirect(routes.HangPerson.show())
        }
      }
    }
  }

  def lose = Action {
    implicit request => {
      if (null == game) {
        Redirect(routes.HangPerson.newAction()).flashing("error" -> "Game was not created!")
      } else {
        game.checkWinOrLose match {
          case Some(false) => Ok(views.html.HangPerson.lose(game.word))
          case _ => Redirect(routes.HangPerson.show())
        }
      }
    }
  }

  def guess = Action {
    implicit request => {
      if (null == game) {
        Redirect(routes.HangPerson.newAction()).flashing("error" -> "Game was not created!")
      } else {
        HangPersonForm.HangPersonForm.bindFromRequest.fold(
          formWithErrors => {
            BadRequest(views.html.HangPerson.show(game.wrongGuesses, game.guesses, formWithErrors))
          },
          HangPersonData => {
            val c = HangPersonData.letter
            Logger.debug("c=" + c)
            try {
              if (game.guess(c)) {
                Redirect(routes.HangPerson.show())
              } else {
                Redirect(routes.HangPerson.show()).flashing("error" -> "You have already used that letter.")
              }
            } catch {
              case e: IllegalArgumentException =>
                Redirect(routes.HangPerson.show()).flashing("error" -> e.getMessage)
            }
          }
        )
      }
    }
  }

  def spa = Action {
    Ok(views.html.HangPerson.spa.index())
  }
}
