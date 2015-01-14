package code.lib

import net.liftweb.http.LiftRules
import java.util.Scanner
import java.io.InputStream
import scala.util.Random

object Thesaurus {

  /** Container for an entry in the thesaurus. */
  case class Entry(stem: String, synonyms: Set[String])

  /**
   * An in-memory database of the Thesaurus.
   *
   * NB: the thesaurus contains alternate entries for some stems, so this is a lossy representation.
   */
  val database: Map[String,Set[String]] =
    Thesaurus.words.map(e => e.stem.toLowerCase -> e.synonyms).toMap

  /**
   * Randomly pick a word association.
   *
   * @param word  the word to look up.
   * @return      an associated word, or the original if no alternatives found.
   */
  def association(word: String): String =
    (database get word.toLowerCase).map(pickOne) getOrElse word

  private def pickOne(words: Set[String]) = Random.shuffle(words.toSeq).head

  /**
   * Read the entries in the Moby thesaurus.
   */
  def words: Stream[Entry] = {

    /* The thesaurus file contains ^M separated entries. Each entry starts with the stem word.
    followed by a space, and then comma-separated list of synonyms. */
    val dataPath = "/mobythes.aur"

    def asScanner(in: InputStream): Scanner = new Scanner(in, "ASCII").useDelimiter("\r")

    def parse(in: Scanner): Stream[Entry] =
      in.hasNext match {
        case false =>
          in.close()
          Stream.empty
        case true  =>
          val line = in.next() split ",|\\s"
          Entry(line.head, line.tail.toSet) #:: parse(in)
      }

    LiftRules.getResource(dataPath).
      map(_.openStream).
      map(asScanner).
      map(parse) openOr Stream.empty

  }
}