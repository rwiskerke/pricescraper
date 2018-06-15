import org.junit.Test
import org.junit.Assert._
import rinus.pricescraper.KarweiScraper

class KarweiScraperSpec extends KarweiScraper {
  @Test def testPriceScrape(): Unit = {
    val price = getPrice("https://www.karwei.nl/assortiment/vliesbehang-hexagon-zwart-dessin-103975/p/B598486")
    assertNotNull(price)
    println(s"Price: $price")
  }
}