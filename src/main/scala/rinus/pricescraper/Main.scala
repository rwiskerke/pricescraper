package rinus.pricescraper

object Main extends KarweiScraper with Emailer {
  def main(args: Array[String]): Unit = {
    entrypoint(null)
  }

  def entrypoint(input: java.util.Map[String,Object]): String = {
    val summary =  Products.list.map { product =>
      val currentPrice = getPrice(product.url)
      val priceChanged = currentPrice != product.expectedPrice
      
      if(!priceChanged) println(s"No change in price detected for ${product.name}")
      
      if(priceChanged || Settings.getBoolean("PRICESCRAPER_ALWAYS_MAIL")){
        sendEmail(product, currentPrice)
      } 
      
      s"$product & currentPrice = $currentPrice"
    }.mkString(",")

    println(s"Summary: $summary")
    summary
  }
}
