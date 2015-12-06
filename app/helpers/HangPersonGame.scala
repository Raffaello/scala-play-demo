package helpers

import play.api.libs.ws._
import play.api.Play.current
import scala.annotation.tailrec
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.matching.Regex

class HangPersonGame(val word: String)
{
  var _guesses: String = ""
  def guesses_=(_guesses: String) = this._guesses = _guesses
  def guesses = this._guesses

  var _wrong_guesses: String = ""
  def wrongGuesses_=(_wrong_guesses: String) = this._wrong_guesses = _wrong_guesses
  def wrongGuesses = this._wrong_guesses

  def guess(letter: Char): Boolean = {
    val l = letter.toLower
    def isNotValidLetter(c: Char): Boolean = c < 'a' || c > 'z'
    if (isNotValidLetter(l)) throw new IllegalArgumentException(letter + " is not a valid letter (" + l + ")")
    if (word.contains(l))
      if (guesses.contains(l))
        false
      else {
        guesses += l
        true
      }
    else if (wrongGuesses.contains(l))
      false
    else {
      wrongGuesses += l
      true
    }
  }

  def randomWord: Unit = {
    HangPersonGame.randomWord
  }

  def checkWinOrLose: Option[Boolean] = {
    if (wrongGuesses.length >= 7) Some(false)
    else if (guesses.toSet == word.toSet) Some(true)
    else None
  }

  def wordWithGuesses: String = {
    word.map(l => if (guesses.contains(l)) l else '-' )
  }
}

object HangPersonGame
{
  def randomWord = {
    WS.url("http://watchout4snakes.com/wo4snakes/Random/RandomWord").get().map {
        response => (response.xml \ "span").text.toLowerCase
      }
  }
}