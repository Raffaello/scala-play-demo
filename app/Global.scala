import play.api._
import play.api.mvc.{Handler, RequestHeader}

object Global extends GlobalSettings {
  override def onRouteRequest(request: RequestHeader): Option[Handler] = {
    super.onRouteRequest(request)
  }
}
