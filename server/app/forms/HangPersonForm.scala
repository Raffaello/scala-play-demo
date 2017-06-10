package forms

import play.api.data.Form
import play.api.data.Forms.{mapping, char}

object HangPersonForm {

  case class HangPersonData(letter: Char)

  val HangPersonForm = Form[HangPersonData](
    mapping(
      "letter" -> char
    )(HangPersonData.apply)(HangPersonData.unapply)
  )
}
