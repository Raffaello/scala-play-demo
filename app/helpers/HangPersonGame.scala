package helpers

import play.api.libs.ws._
import scala.concurrent.Future
import play.api.Play.current

class HangPersonGame(val word: String)
{
    def randomWord(): Unit = {
      HangPersonGame.randomWord
    }
}

object HangPersonGame {
  def randomWord = {
    WS.url("http://watchout4snakes.com/wo4snakes/Random/RandomWord")
      .get()
      .map {
        response => (response.xml \ "span").text
      }
  }
}