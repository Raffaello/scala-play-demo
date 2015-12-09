package controllers

import helpers.HangPersonGame
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._
import play.api.Logger

class HangPerson extends Controller
{
  var _game:HangPersonGame = new HangPersonGame(HangPersonGame.randomWord)
  def game_=(_game: HangPersonGame) = this._game = _game
  def game = this._game

  def newAction = Action {
    Ok(views.html.HangPerson.newAction())
  }

  def create = Action {
    request =>
      val word: String = HangPersonGame.randomWord
      game = new HangPersonGame(word)
      Redirect(routes.HangPerson.show()).withSession(
        "word" -> word
      )
  }

  def show = Action {
    implicit request =>
      if(game == null) {
        Redirect(routes.HangPerson.newAction())
      } else {
        game.checkWinOrLose match {
          case Some(true)   => Redirect(routes.HangPerson.win)
          case Some(false)  => Redirect(routes.HangPerson.lose)
          case _            => Ok(views.html.HangPerson.show(game.wrongGuesses, game.wordWithGuesses))
        }
      }
  }

  def win = Action {
      game.checkWinOrLose match {
        case Some(true) => Ok(views.html.HangPerson.win(game.word))
        case _          => Redirect(routes.HangPerson.show)
      }
  }

  def lose = Action {
      game.checkWinOrLose match {
        case Some(false)  => Ok(views.html.HangPerson.lose(game.word))
        case _            => Redirect(routes.HangPerson.show)
      }
  }

//  val guessForm = Form(
//    "guess" -> nonEmptyText
//  )

  def guess = Action {
    request => {
      val c = request.body.asFormUrlEncoded.get("guess").toArray.head.head
      Logger.debug("c="+c)
      if (game.guess(c))
        Redirect(routes.HangPerson.show)
      else
        Redirect(routes.HangPerson.show()).flashing("error" -> "You have already used that letter.")
    }
  }
}
