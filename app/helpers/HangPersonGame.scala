package helpers

import play.api.libs.ws._
import play.api.Play.current
import scala.concurrent.ExecutionContext.Implicits.global

class HangPersonGame(val word: String)
{
  var _guesses: String = ""
  def guesses_=(_guesses: String) = this._guesses = _guesses
  def guesses = this._guesses

  var _wrong_guesses: String = ""
  def wrongGuesses_=(_wron_guesses: String) = this._wrong_guesses = _wrong_guesses
  def wrongGuesses = this._wrong_guesses

  def guess(letter: Char) = ???

  def randomWord(): Unit = {
    HangPersonGame.randomWord
  }
}

object HangPersonGame
{
  def randomWord = {
    val response = WS.url("http://watchout4snakes.com/wo4snakes/Random/RandomWord").get().map {
        response => (response.xml \ "span").text
      }
  }
}