package rinus.pricescraper

object Main extends KarweiScraper with Emailer {
  def main(args: Array[String]): Unit = {
    entrypoint(null)
  }

  def entrypoint(input: java.util.Map[String,Object]): String = {
    val summary =  Products.list.map { product =>
      val currentPrice = getPrice(product.url)
      if(currentPrice != product.expectedPrice || Settings.getBoolean("PRICESCRAPER_ALWAYS_MAIL")){
        sendEmail(product, currentPrice)
      }
      s"$product & currentPrice = $currentPrice"
    }.mkString(",")

    println(s"Summary: $summary")
    summary
  }
}
