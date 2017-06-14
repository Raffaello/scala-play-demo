package controllers

import play.api.mvc._

class HangPersonSpa extends Controller {
  def index = Action {
    Ok(views.html.HangPerson.spa.index())
  }
}
