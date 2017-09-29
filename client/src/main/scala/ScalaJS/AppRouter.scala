package ScalaJS

import ScalaJS.Component.{Main, Show}
import japgolly.scalajs.react.extra.router.{BaseUrl, Redirect, Router, RouterConfigDsl}
import japgolly.scalajs.react.vdom.Implicits._


object AppRouter {
  case object Home extends View
//  case class Play(game: HangPersonGame) extends View
  case object Play extends View

  val routerConfig = RouterConfigDsl[View].buildConfig { dsl =>
    import dsl._

    (emptyRule
      | staticRoute(root, Home) ~> renderR(ctl => Main.component(ctl))
      | staticRoute("#show", Play) ~> renderR(ctl => Show.component(ctl))
      ).notFound(redirectToPath("/")(Redirect.Replace))
      .verify(Home)
  }

  val router = Router(BaseUrl.fromWindowOrigin + "/hangperson/spa", routerConfig)
}
