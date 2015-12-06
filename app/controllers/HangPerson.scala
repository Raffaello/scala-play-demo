package controllers

import helpers.HangPersonGame
import play.api.mvc._

class HangPerson extends Controller
{
  var _game:HangPersonGame = null
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
        Ok(views.html.HangPerson.show(game.wrongGuesses, game.wordWithGuesses))
      }
  }

  def guess = Action {
    // TODO
    Redirect(routes.HangPerson.show())
  }
}
