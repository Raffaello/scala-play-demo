package ScalaJS

import autowire.Bounds.None
import org.scalajs.dom.ext.Ajax

import scala.scalajs.js
import scala.scalajs.js.JSON
import scala.concurrent.ExecutionContext.Implicits.global

@js.native
trait randomWordResult extends js.Object {
  def word: String
}

object AjaxClient extends autowire.Client[String, None, None] {
  override def doCall(req: AjaxClient.Request) = {
    Ajax.get("/spa/api/" + req.path.mkString("/")).map { xhr =>
      JSON.parse(xhr.responseText).asInstanceOf[randomWordResult].word
    }
  }

  override def read[Result](p: String)(implicit evidence$1: None[Result]) = ???

  override def write[Result](r: Result)(implicit evidence$2: None[Result]) = ???
}

trait Api {
  def getRandomWord(): String
}