package code.comet

import net.liftweb.actor.LiftActor
import net.liftweb.http.ListenerManager

object EditServer extends LiftActor with ListenerManager {

  // All the edits so far:
  var history = Vector[CharAndPos]()

  // Handle messages from comet actors:
  override def lowPriority = {

    // When we receive an edit, save it in the history and pass it on to all browsers:
    case change : CharAndPos =>
      history = history :+ change
      updateListeners(change)
  }

  // When a collaborator joins, send them this:
  def createUpdate = history

}
