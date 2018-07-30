package controllers

import javax.inject.Inject

import play.api.libs.json._
import play.api.libs.ws.WSClient
import play.api.mvc._

class HangPersonSpa @Inject()(val ws: WSClient) extends Controller with RandomWordClient {
  def index = Action {
    Ok(views.html.HangPerson.spa.index())
  }

  /**
    * TODO: if someone inspect the network tab of the browser can read the word and cheat.
    *       find a solution to communicate in a secure way the data among client and server.
    *       try use JWT or just an encrypted one
    *
    */
  def getRandomWord = Action {
    val json: JsValue = JsObject(Seq(
      "word" -> JsString(randomWord)
    ))

    Ok(Json.toJson(json))
  }
}
