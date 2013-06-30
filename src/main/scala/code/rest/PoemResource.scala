package code.rest

import net.liftweb.http.rest.RestHelper
import net.liftweb.json.JsonDSL._

object PoemResource extends RestHelper {

  val poems = Map(
    "Larkin" -> List("This Be The Verse", "Aubade")
  )

  serve {
    case "poems" :: "by" :: author :: Nil Get request =>
      for ( titles <- poems.get(author) )
        yield ("titles" -> titles) ~ ("author" -> author)
  }
}
