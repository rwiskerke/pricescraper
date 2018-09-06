## Pricescraper
Small project to test out some "new" things. 
* AWS Lambda a.k.a. "serverless"
* AWS SES
* Dotty
* HTML scraping


### Usage
sbt run


### Emails using SES
Make sure the environment contains these vars (see .env.sample):

|variable|type|
|--------|----|
|AWS_ACCESS_KEY_ID|string
|AWS_SECRET_ACCESS_KEY|string
|PRICESCRAPER_ALWAYS_MAIL|boolean ("true" or "false")
|PRICESCRAPER_EMAIL_FROM|string
|PRICESCRAPER_EMAIL_TO|string

You can create a .env file by copying .env.sample and replacing the values with something that is coming from your AWS account
