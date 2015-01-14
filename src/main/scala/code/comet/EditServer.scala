package code.comet

import net.liftweb.actor.LiftActor
import net.liftweb.http.ListenerManager
import net.liftweb.json.JsonAST.JValue

object EditServer extends LiftActor with ListenerManager {

  // All the edits so far:
  var history = Vector[JValue]()

  // Handle messages from comet actors:
  override def lowPriority = {

    // When we receive an edit, save it in the history and pass it on to all browsers:
    case change : JValue =>
      history = history :+ change
      sendListenersMessage(change)
  }

  // When a collaborator joins, send them this:
  def createUpdate = history
}