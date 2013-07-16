package code.lib

import org.specs2.mutable._

class ThesaurusSpec extends Specification {

  "The Thesaurus" should {

    "Load records from disk" in {

      val abomb = Thesaurus.words.drop(3) head

      abomb.stem must_== "A-bomb"
      abomb.synonyms must_== Set("fusion", "dirty", "explosive", "superbomb",
        "weapons", "thermonuclear", "limited", "tactical", "hydrogen", "clean",
        "warhead", "hell", "nuclear", "atomic", "H-bomb", "cobalt", "artillery",
        "bomb", "plutonium")
    }

    
    "Can lookup by stem" in {

      val lookedUp = Thesaurus.database get "lift"

      val expected = Set("ebb", "liberate", "lever", "snare", "comb", "levitate",
        "drive", "gantry", "support", "ether", "advancement", "satisfy", "dismantle", "cancel",
        "reform", "ameliorate", "vanish", "deify", "aspire", "revival", "nick", "lug", "in",
        "soar", "pocket", "poach", "off", "forklift", "staircase", "hydraulic", "point", "erect",
        "improvement", "larceny", "dirty", "nurture", "redeem", "extort", "enlighten", "serene",
        "break", "elate", "tide", "disappear", "meliorate", "lifter", "bag", "bear", "noosphere",
        "tote", "upcast", "cerulean", "discontinue", "bettering", "rescind", "nip", "uplift",
        "upbuoy", "enrich", "touch", "perk", "enhance", "ascent", "burglary", "jerk", "jack",
        "whirl", "sea", "thieve", "civilize", "blue", "lifts", "aid", "swipe", "mend", "boot",
        "zenith", "crab", "roller", "aerosphere", "stick", "heavens", "up", "billow", "lob",
        "purloining", "clear", "dignify", "eminence", "betterment", "upbeat", "hand", "crash",
        "exalt", "theft", "quiver", "strike", "manhandle", "euthenics", "trough", "run", "jackscrew",
        "robbery", "azure", "fatten", "purloin", "swell", "set", "void", "full", "Great", "progression",
        "heighten", "headway", "enhancement", "enrichment", "refine", "aggrandize", "abstract",
        "atmosphere", "spin", "pack", "a", "moving", "Forward", "sky", "job", "amend", "rustle",
        "amendment", "snatch", "advance", "lard", "height", "walk", "forward", "Leap", "eugenics",
        "windlass", "rising", "whisk", "rear", "pay", "hoick", "settle", "emotion", "acme", "tidal",
        "reassurance", "joyride", "hyaline", "discharge", "gravity", "balance", "emend", "comfort",
        "firmament", "hoist", "upthrow", "heave", "promote", "scend", "lop", "snitch", "helping",
        "stop", "eagre", "crib", "undulation", "acculturate", "borrow", "square", "out", "comber",
        "edify", "terminate", "peak", "confiscate", "upon", "freight", "encouragement", "surf",
        "amelioration", "fall", "shot", "steep", "wing", "reverse", "succor", "dissipate", "upswing",
        "water", "rocket", "shiver", "heist", "Caelus", "heights", "derrick", "escalator", "elevator",
        "recovery", "hold", "transport", "kick", "steal", "roll", "riffle", "excitement", "liquidate",
        "undulate", "swindle", "upraise", "raise", "go", "escalate", "wave", "pickup", "cast", "thievery",
        "scrounge", "bill", "sensation", "elevation", "boost", "uprear", "gaseous", "pinch", "chop",
        "ground", "immortalize", "heavy", "shudder", "uptrend", "welkin", "take", "tingle", "mending",
        "progress", "hike", "withdraw", "wavelet", "palm", "Olympian", "promotion", "appropriate", "chopping",
        "titillation", "pick", "flow", "improve", "tailgate", "stratosphere", "conduct", "socialize",
        "dash", "bore", "horses", "with", "toss", "rough", "hook", "Sunday", "smash", "stealage",
        "heaven", "transfigure", "cop", "mobility", "biosphere", "surge", "rip-off", "restoration",
        "furtherance", "uphoist", "bring", "copy", "melioration", "make", "charge", "knock", "uprise",
        "end", "retire", "flush", "popple", "tsunami", "grab", "loft", "filch", "magnify", "educate",
        "erector", "breakers", "starry", "straighten", "an", "caper", "air", "recall", "pilfer", "be",
        "foster", "plagiarize", "empyrean", "embezzle", "repeal", "uphold", "better", "dizzy", "tingling",
        "straight", "leg", "tackle", "vantage", "arise", "mount", "airing", "upward", "buoy", "assistance",
        "inducement", "ride", "stimulus", "stealing", "crane", "envelope", "amortize", "choppiness", "upheave",
        "defraud", "upgrade", "caelum", "convey", "vault", "throw", "rush", "thrill", "cope", "inspiration",
        "tremor", "apex", "fly", "waft", "transform", "preferment", "dumbwaiter", "whitecaps", "rise", "jollies",
        "bang", "tower", "poised", "white", "upping", "lift", "ripple", "favor", "elevate", "enshrine", "ennoble",
        "aerial", "assist", "of", "away", "ecosphere", "and", "annex", "ascend", "accounts", "heft", "canopy",
        "carry","relief", "shoplift", "annul", "thieving", "the", "honor", "send")

      lookedUp must beSome(expected)
    }

  }

}
