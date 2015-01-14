package code.rest

import net.liftweb.http.rest.RestHelper
import net.liftweb.json.JsonDSL._
import net.liftweb.json.JsonAST._

object PoemResource extends RestHelper {

  val poems = Map(
    "Larkin" -> List("This Be The Verse", "Aubade")
  )

  def asJSON(titles: List[String]): JValue =
    ("titles" -> titles)

  serve {
    case "poems" :: "by" :: author :: Nil Get request =>
      poems.get(author).map(asJSON)
  }

}

/* I might be just as likely to write this as:

    case "poems" :: "by" :: author :: Nil Get request =>
      for ( titles <- poems.get(author) )
        yield ("titles" -> titles) ~ ("author" -> author)
 */