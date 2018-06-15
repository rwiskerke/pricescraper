package rinus.pricescraper

object Settings {
  def getString(varName: String): String = sys.env.getOrElse(varName, throw new IllegalStateException(s"Could not find env var: $varName"))
  def getBoolean(varName: String): Boolean = getString(varName).toLowerCase == "true"
}
