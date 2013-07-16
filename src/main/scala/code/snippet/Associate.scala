package code
package snippet

import net.liftweb.util.Helpers._
import net.liftweb.http.SHtml.ajaxCall
import net.liftweb.http.js.JsCmds.SetValById
import net.liftweb.http.js.JE.ValById

import lib.Thesaurus

class Associate {
  def render =
    "@submit [onclick]" #> ajaxCall(
      ValById("word"),
      w => SetValById("word", Thesaurus.association(w))
    )
}
