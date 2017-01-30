package controllers

import play.api.mvc._
import play.api.Logger

class ReactJs extends Controller
{
  def index = Action {
    Ok(views.html.ReactJs.index())
  }

}