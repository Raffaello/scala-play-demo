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

class HangPerson @Inject()(ws: WSClient) extends Controller {
  var game: HangPersonGame = new HangPersonGame(randomWord)

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
    Ok(views.html.HangPerson.newAction())
  }

  def create = Action {
    val word: String = randomWord
    game = new HangPersonGame(word)
    Redirect(routes.HangPerson.show()).withSession(
      "word" -> word
    )
  }

  def show = Action {
    implicit request => {
      if (game == null) {
        Redirect(routes.HangPerson.newAction())
      } else {
        game.checkWinOrLose match {
          case Some(true) => Redirect(routes.HangPerson.win())
          case Some(false) => Redirect(routes.HangPerson.lose())
          case _ => Ok(views.html.HangPerson.show(game.wrongGuesses, game.wordWithGuesses))
        }
      }
    }
  }

  def win = Action {
    game.checkWinOrLose match {
      case Some(true) => Ok(views.html.HangPerson.win(game.word))
      case _ => Redirect(routes.HangPerson.show())
    }
  }

  def lose = Action {
    game.checkWinOrLose match {
      case Some(false) => Ok(views.html.HangPerson.lose(game.word))
      case _ => Redirect(routes.HangPerson.show())
    }
  }

  def guess = Action {
    request => {
      val c = request.body.asFormUrlEncoded.get("guess").toArray.head.head
      Logger.debug("c=" + c)
      try {
        if (game.guess(c))
          Redirect(routes.HangPerson.show())
        else
          Redirect(routes.HangPerson.show()).flashing("error" -> "You have already used that letter.")
      } catch {
        case e: IllegalArgumentException =>
          Redirect(routes.HangPerson.show()).flashing("error" -> e.getMessage)
      }
    }
  }
}
