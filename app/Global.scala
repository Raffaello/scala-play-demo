import helpers.HangPersonGame
import play.api._
import play.api.mvc.{Handler, RequestHeader}

object Global extends GlobalSettings {
  //val hangperson: HangPersonGame = new HangPersonGame("word")

  override def onRouteRequest(request: RequestHeader): Option[Handler] = {

    super.onRouteRequest(request)
  }
}
