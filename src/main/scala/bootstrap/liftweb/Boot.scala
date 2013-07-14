package bootstrap.liftweb

import net.liftweb._
import util._
import Helpers._

import common._
import http._
import sitemap._
import Loc._
import net.liftmodules.JQueryModule
import net.liftweb.http.js.jquery._
import code.rest.PoemResource


/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {
  def boot {
    // where to search snippet
    LiftRules.addToPackages("code")

    LiftRules.statelessDispatch.append(PoemResource)

    val PremiumCustomersOnly = If(
      () => false,
      () => S.redirectTo("https://shop.example.org/") )

    lazy val home = Menu.i("home") / "index"

    lazy val poets = Menu.i("poets") / "list" submenus (
      Menu("Larkin") / "a-z" / "larkin",
      Menu("Tennyson") / "a-z" / "tennyson" >> PremiumCustomersOnly
    )


   import code.snippet.PoetFact
   import code.snippet.PoetFacts.database

    lazy val fact = Menu.param[PoetFact]("poet", "Poet Fact",
      surname => database.get(surname),
      fact => fact.surname.toLowerCase
      ) / "fact" >> Hidden


    LiftRules.setSiteMap(SiteMap(home, poets, fact))


    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)
    
    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) =>
      new Html5Properties(r.userAgent))

    //Init the jQuery module, see http://liftweb.net/jquery for more information.
    LiftRules.jsArtifacts = JQueryArtifacts
    JQueryModule.InitParam.JQuery=JQueryModule.JQuery191
    JQueryModule.init()

  }
}
