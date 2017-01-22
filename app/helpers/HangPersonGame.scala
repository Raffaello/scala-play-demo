package helpers

import play.api.libs.ws._
import play.api.Play.current
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.Logger
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

class HangPersonGame(val word: String)
{
  var guesses:String = ""
  var wrongGuesses:String = ""

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
  def randomWord: String = {
    val word = Await.result[String](WS.url("http://watchout4snakes.com/wo4snakes/Random/RandomWord").post(Map("LastWord" -> Seq(""))).map {
        response => {
          Logger.debug("resp: " + response.body)
          response.body
        }

      },5 minute)

    Logger.debug("word: " + word)
    if(word.isEmpty)
      // TODO generate from a dictionary some random word. the WS is not working as aspected.
      "empty"
    else
      word
  }
}