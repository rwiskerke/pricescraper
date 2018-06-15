package rinus.pricescraper
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

trait KarweiScraper {
  def getPrice(url: String): String = {
  
    val doc: Document = Jsoup.connect(url).get
    val price = doc.getElementsByAttributeValue("itemprop","price").get(0).attributes.get("content")
    
    price
  }
}
