package rinus.pricescraper

import com.amazonaws.auth._
import com.amazonaws.regions.Regions
import com.amazonaws.services.simpleemail.model._
import com.amazonaws.services.simpleemail.{AmazonSimpleEmailService, AmazonSimpleEmailServiceClientBuilder}
import Products.Product

trait Emailer {
  private val ACCESS_KEY_ID = Settings.getString("PRICESCRAPER_AWS_ACCESS_KEY_ID")
  private val SECRET_ACCESS_KEY =  Settings.getString("PRICESCRAPER_AWS_SECRET_ACCESS_KEY")
  private val EMAILADDRESS_FROM = Settings.getString("PRICESCRAPER_EMAIL_FROM")
  private val EMAILADDRESS_TO = Settings.getString("PRICESCRAPER_EMAIL_TO")

  val client: AmazonSimpleEmailService = AmazonSimpleEmailServiceClientBuilder.standard
  .withRegion(Regions.US_EAST_1)
  .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY)))
  .build

  def sendEmail(product: Product, newPrice: String): Unit = {
    println("Sending email...")
    import product._
    val request = new SendEmailRequest()
     .withDestination(
       new Destination().withToAddresses(EMAILADDRESS_TO))
     .withMessage(new Message()
       .withBody(new Body()
         .withText(new Content()
           .withCharset("UTF-8").withData(
             s"""|Old price $expectedPrice
                 |New price: $newPrice
                 |Url: $url""".stripMargin)))
       .withSubject(new Content()
         .withCharset("UTF-8").withData(s"New price $name: $newPrice")))
     .withSource(EMAILADDRESS_FROM)
     val emailResult = client.sendEmail(request)
  }
}
