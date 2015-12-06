package helpers

import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.test._

@RunWith(classOf[JUnitRunner])
class HangPersonGameSpec extends Specification
{
  def guess_several_letters(game: HangPersonGame, letters: String) = {
      letters.toCharArray.foreach(c => game.guess(c))
  }

  "HangPersonGame" should {
    "create new object" in new WithApplication {
      val hangpersonGame = new HangPersonGame("test")
      hangpersonGame must beAnInstanceOf[HangPersonGame]
      hangpersonGame.word must beEqualTo("test")
      hangpersonGame.guesses must beEqualTo("")
      hangpersonGame.wrongGuesses must beEqualTo("")
    }

    "guessing correctly" in new WithApplication() {
      val game = new HangPersonGame("garply")
      val valid = game.guess('a')
      game.guesses must beEqualTo("a")
      game.wrongGuesses must beEqualTo("")
      valid.equals(true)
    }

    "guessing wrongly" in new WithApplication() {
      val game = new HangPersonGame("garply")
      val valid = game.guess('z')
      game.guesses must beEqualTo("")
      game.wrongGuesses must beEqualTo("z")
      valid.equals(true)
    }

    "Several letter" in new WithApplication() {
      val game = new HangPersonGame("garply")
      guess_several_letters(game, "aq")

      val valid1 = game.guess('a')
      game.guesses must beEqualTo("a")
      valid1.equals(false)

      val valid2 = game.guess('q')
      game.wrongGuesses must beEqualTo("q")
      valid2.equals(false)

      game.guess('A').equals(false)
      game.guess('Q').equals(false)
      game.guesses must not contain ("A")
      game.guesses must not contain ("Q")
    }

  }
}
