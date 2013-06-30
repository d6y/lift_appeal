package code.snippet

import net.liftweb.util.Helpers._

class Poems {
  def render =
    ".author *" #> "Philip Larkin" &
    ".title *" #> "This Be The Verse"
}
