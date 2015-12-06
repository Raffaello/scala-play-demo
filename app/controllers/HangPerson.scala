package controllers

import helpers.HangPersonGame
import play.api.mvc._

class HangPerson extends Controller
{
  var _game:HangPersonGame = null
  def game_=(_game: HangPersonGame) = this._game = _game
  def game = this._game

  def index = Action {
    Redirect(routes.HangPerson.newAction)
  }

  def newAction = Action {
    Ok(views.html.HangPerson.index("hang person game"))
  }

  def create = Action {
    request =>
      val word: String = request.session.get("word").getOrElse(HangPersonGame.randomWord).toString
      game = new HangPersonGame(word)
      Redirect(routes.HangPerson.show()).withSession(
        "word" -> word
      )
  }

  def show = Action {
    implicit request => Ok(views.html.HangPerson.show(game.wrongGuesses, game.wordWithGuesses))
  }
}
