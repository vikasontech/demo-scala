name := "demo-scala"

version := "0.1"

scalaVersion := "2.13.1"
val swaggerVersion = "2.1.0"
val jacksonVersion = "2.10.1"
val akkaHttpVersion= "10.1.11"
val akka_streams = "2.6.0"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.4.0",
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream" % akka_streams,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.github.swagger-akka-http" %% "swagger-akka-http" % "2.0.4",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion,
  "io.swagger.core.v3" % "swagger-core" % swaggerVersion,
  "io.swagger.core.v3" % "swagger-annotations" % swaggerVersion,
  "com.typesafe.akka" %% "akka-stream-testkit" % akka_streams,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion,
  "org.scalatest" %% "scalatest" % "3.2.0-M2",
)
