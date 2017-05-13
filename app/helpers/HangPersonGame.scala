package helpers

class HangPersonGame(val word: String) {
  var guesses: String = ""
  var wrongGuesses: String = ""

  def guess(letter: Char): Boolean = {
    val l = letter.toLower

    def isNotValidLetter(c: Char): Boolean = c < 'a' || c > 'z'

    if (isNotValidLetter(l)) throw new IllegalArgumentException(letter + " is not a valid letter (" + l + ")")

    if (word.contains(l))
      if (guesses.contains(l))
        false
      else {
        guesses += l
        true
      }
    else if (wrongGuesses.contains(l))
      false
    else {
      wrongGuesses += l
      true
    }
  }

  def checkWinOrLose: Option[Boolean] = {
    if (wrongGuesses.length >= 7) Some(false)
    else if (guesses.toSet == word.toSet) Some(true)
    else None
  }

  def wordWithGuesses: String = {
    word.map(l => if (guesses.contains(l)) l else '-')
  }
}
