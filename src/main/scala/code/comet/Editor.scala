package code.comet

import net.liftweb.http.{SHtml, CometListener, CometActor}
import net.liftweb.http.js.JsCmds._
import net.liftweb.http.js.JsCmd
import net.liftweb.http.js.JE._
import net.liftweb.json.JsonAST.JValue

class Editor extends CometActor with CometListener {

  // The server code we send messages to, and receive messages from:
  def registerWith = EditServer

  // When shown, bind key presses to server events:
  def render =
    "#poem [onkeypress]" #> SHtml.jsonCall(Call("charAndPos(arguments)"), updateServer _) &
    "#poem [onkeyup]"    #> SHtml.jsonCall(Call("detectDelete(arguments)"), updateServer _)

  // When we get a key event, notify the server:
  def updateServer(json: JValue): JsCmd = {
    EditServer ! json
    Noop
  }

  // When we receive an event from the server, update our browser display:
  override def lowPriority = {

    // Process an edit: send the change via a JavaScript Call:
    case edit: JValue => partialUpdate(Call("accept", edit))

    // If we've joined late, apply all the changes so far:
    case edits: Vector[JValue] =>
      val js = edits.foldLeft(Noop) { (z,edit) => z & Call("accept",edit) }
      partialUpdate(js)
  }
}