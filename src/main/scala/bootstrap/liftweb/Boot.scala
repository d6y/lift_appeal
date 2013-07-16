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
import code.lib.Thesaurus


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


    lazy val thesaurus = Menu.i("thesaurus") / "thesaurus"

    lazy val editor = Menu.i("editor") / "edit"

    LiftRules.setSiteMap(SiteMap(home, poets, fact, thesaurus, editor))


    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) =>
      new Html5Properties(r.userAgent))

    //Init the jQuery module, see http://liftweb.net/jquery for more information.
    LiftRules.jsArtifacts = JQueryArtifacts
    JQueryModule.InitParam.JQuery=JQueryModule.JQuery191
    JQueryModule.init()

    // Preload our in-memory database
    Schedule( () => Thesaurus.database )

  }
}
