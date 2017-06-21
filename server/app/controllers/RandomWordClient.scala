package controllers

import play.api.Logger
import play.api.libs.ws.WSClient

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.language.postfixOps

trait RandomWordClient {
  val ws: WSClient

  def randomWord: String = {
    val url = "http://watchout4snakes.com/wo4snakes/Random/RandomWord"
    val word = Await.result[String](ws.url(url).post(Map("LastWord" -> Seq(""))).map {
      response => {
        response.body
      }
    }, 5 minute)

    Logger.debug("word: " + word)
    if (word.isEmpty) "empty" else word
  }
}
