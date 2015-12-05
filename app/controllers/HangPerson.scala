package controllers

import play.api._
import play.api.mvc._

class HangPerson extends Controller
{
  def index = Action {
    Redirect(routes.HangPerson._new)
  }

  def _new = Action {
    Ok(views.html.HangPerson.index("hang person game"))
  }

  def create = Action {
    // @TODO: create the game
    Redirect(routes.HangPerson.show())
  }

  def show = Action {
    // @TODO: display the game
    Ok(views.html.HangPerson.show())
  }
}
