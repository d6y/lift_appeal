package code.snippet

import net.liftweb.util.Helpers._

case class PoetFact(first: String, surname:String, born: Int)

object PoetFacts {

  val database = Map[String, PoetFact](
    "larkin" -> PoetFact("Philip", "Larkin", 1922),
    "tennyson" -> PoetFact("Alfred", "Tennyson", 1809)
    )

}

class PoetInfo(fact: PoetFact) {
    def render =
      ".first-name *" #> fact.first &
      ".last-name *" #> fact.surname &
      ".dob *" #> fact.born
}
