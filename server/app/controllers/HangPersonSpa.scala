package controllers

import javax.inject.Inject

import play.api.libs.json._
import play.api.libs.ws.WSClient
import play.api.mvc._

class HangPersonSpa @Inject()(val ws: WSClient) extends Controller with RandomWordClient {
  def index = Action {
    Ok(views.html.HangPerson.spa.index())
  }

  def getRandomWord = Action {
    val json: JsValue = JsObject(Seq(
      "word" -> JsString(randomWord)
    ))

    Ok(Json.toJson(json))
  }
}
