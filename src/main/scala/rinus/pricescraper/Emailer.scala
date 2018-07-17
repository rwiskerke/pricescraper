package rinus.pricescraper

import com.amazonaws.regions.Regions
import com.amazonaws.services.simpleemail.model._
import com.amazonaws.services.simpleemail.{AmazonSimpleEmailService, AmazonSimpleEmailServiceClientBuilder}
import Products.Product

trait Emailer {
  private val EMAILADDRESS_FROM = Settings.getString("PRICESCRAPER_EMAIL_FROM")
  private val EMAILADDRESS_TO = Settings.getString("PRICESCRAPER_EMAIL_TO")

  val client: AmazonSimpleEmailService = AmazonSimpleEmailServiceClientBuilder.standard
  .withRegion(Regions.US_EAST_1)
  .build

  def sendEmail(product: Product, newPrice: String): Unit = {
    import product._
    val emailBody:String = s"""|Old price $expectedPrice
                               |New price: $newPrice
                               |Url: $url""".stripMargin

    println(s"Sending email [${emailBody.replace("\n"," || ")}]")
    val request = new SendEmailRequest()
     .withDestination(
       new Destination().withToAddresses(EMAILADDRESS_TO))
     .withMessage(new Message()
       .withBody(new Body()
         .withText(new Content()
           .withCharset("UTF-8").withData(emailBody)))
       .withSubject(new Content()
         .withCharset("UTF-8").withData(s"New price $name: $newPrice")))
     .withSource(EMAILADDRESS_FROM)
     val emailResult = client.sendEmail(request)
  }
}
