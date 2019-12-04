name := "demo-scala"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies ++= Seq (
    "org.scalatest" %% "scalatest" % "3.2.0-M1" % Test,
    "com.typesafe" % "config" % "1.3.0",
    "com.typesafe.akka" %% "akka-http" % "10.1.11",
    "com.typesafe.akka" %% "akka-stream" % "2.5.26"

)