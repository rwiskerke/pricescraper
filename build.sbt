val dottyVersion = "0.8.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "pricescraper",
    version := "0.1.0",

    scalaVersion := dottyVersion,

    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
    libraryDependencies += "org.jsoup" % "jsoup" % "1.11.3",
    libraryDependencies += "com.amazonaws" % "aws-java-sdk-ses" % "1.11.344",
    libraryDependencies += "com.amazonaws" % "aws-java-sdk-lambda" % "1.11.344"
  )
