package code.comet

import net.liftweb.util.Helpers._
import net.liftweb.http.{SHtml, CometListener, CometActor}

import net.liftweb.http.js.JsCmds._
import net.liftweb.http.js.JsCmd
import net.liftweb.http.js.JE._
import net.liftweb.json.JsonAST.JValue

case class CharAndPos(sender: String, pos: Int, char: String) {
  def toCmd = Call("insertAt", sender, pos, char).cmd
}

class Editor extends CometActor with CometListener {

  // The server code we send messages to, and receive messages from:
  def registerWith = EditServer

  // When shown, bind keypresses to server events:
  def render =
    "#poem [onkeypress]" #> SHtml.jsonCall(Call("charAndPos(arguments)"), updateServer _)


  // When we get a key event, notify the server:
  implicit val defaults = net.liftweb.json.DefaultFormats
  def updateServer(json: JValue) : JsCmd = {
    for (update <- json.extractOpt[CharAndPos])
      EditServer ! update
    Noop
  }

  // When we receive an event from the server, update our browser display:
  override def lowPriority = {

    // Process an edit as a JavaScript Call:
    case edit : CharAndPos => partialUpdate(edit.toCmd)

    // If we've joined late, apply all the changes so far:
    case edits: Vector[CharAndPos] =>
      val js = edits.map(_.toCmd).foldLeft(Noop) { (z,edit) => z & edit }
      partialUpdate(js)
  }

}
