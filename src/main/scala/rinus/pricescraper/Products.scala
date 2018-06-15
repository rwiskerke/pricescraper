package rinus.pricescraper

object Products {
  case class Product(name: String, url: String, expectedPrice: String)

  val list: List[Product] = List(
    Product("tuinbankje", "https://www.karwei.nl/assortiment/bank-zia-naturel-123-cm/p/B561175", "199.00")
  )
}
