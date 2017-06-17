package controllers

import java.util.UUID
import javax.inject.Inject

import play.api.mvc._
import play.api.Logger
import play.api.libs.ws.WSClient
import forms.HangPersonForm
import helpers.HangPersonGame
import play.api.cache.CacheApi
import play.api.i18n.{I18nSupport, MessagesApi}

import scala.concurrent.Future

class HangPerson @Inject()(val messagesApi: MessagesApi, val ws: WSClient, cache: CacheApi) extends Controller
  with I18nSupport with RandomWordClient {
  val sessionKey = "UUID"
  var game: HangPersonGame = _

  def gameAction[A](action: Action[A]): Action[A] = Action.async(action.parser) {
    request => {
      val uuid = request.session.get(sessionKey)
      if (uuid.isEmpty) {
        Future.successful(
          Redirect(routes.HangPerson.newAction()).flashing("danger" -> "Game was not created!")
        )
      } else {
        val maybeGame: Option[HangPersonGame] = cache.get[HangPersonGame](uuid.get)
        maybeGame match {
          case None => {
            Future.successful(
              Redirect(routes.HangPerson.newAction())
                .flashing("danger" -> "Game was not found!"))
          }
          case _ => {
            game = maybeGame.get
            action(request)
          }
        }
      }
    }
  }

  def newAction = Action {
    implicit request => {
      Ok(views.html.HangPerson.newAction())
    }
  }

  def create = Action {
    request => {
      val uuid = request.session.get(sessionKey).getOrElse(UUID.randomUUID().toString)
      game = new HangPersonGame(randomWord)
      cache.set(uuid, game)
      Redirect(routes.HangPerson.show()).withSession((sessionKey, uuid))
    }
  }

  def show = gameAction(Action {
    implicit request => {
      game.checkWinOrLose match {
        case Some(true) => Redirect(routes.HangPerson.win())
        case Some(false) => Redirect(routes.HangPerson.lose())
        case _ => Ok(views.html.HangPerson.show(game.wrongGuesses, game.wordWithGuesses, HangPersonForm.HangPersonForm))
      }
    }
  })

  def win = gameAction(Action {
    implicit request => {
      game.checkWinOrLose match {
        case Some(true) => Ok(views.html.HangPerson.win(game.word))
        case _ => Redirect(routes.HangPerson.show())
      }
    }
  })

  def lose = gameAction(Action {
    implicit request => {
      game.checkWinOrLose match {
        case Some(false) => Ok(views.html.HangPerson.lose(game.word))
        case _ => Redirect(routes.HangPerson.show())
      }
    }
  })

  def guess = gameAction(Action {
    implicit request => {
      HangPersonForm.HangPersonForm.bindFromRequest.fold(
        formWithErrors => {
          BadRequest(views.html.HangPerson.show(game.wrongGuesses, game.guesses, formWithErrors))
        },
        HangPersonData => {
          Logger.debug("c=" + HangPersonData.letter)
          try {
            if (game.guess(HangPersonData.letter)) {
              Redirect(routes.HangPerson.show())
            } else {
              Redirect(routes.HangPerson.show()).flashing("danger" -> "You have already used that letter.")
            }
          } catch {
            case e: IllegalArgumentException =>
              Redirect(routes.HangPerson.show()).flashing("danger" -> e.getMessage)
          }
        }
      )
    }
  })
}
