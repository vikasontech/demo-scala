name := "demo-scala"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies ++= Seq (
    "org.scalatest" %% "scalatest" % "3.2.0-M1" % Test,
    "com.lightbend.akka.management" %% "akka-management" % "1.0.5",
    "com.typesafe" % "config" % "1.3.0",
    "com.typesafe.akka" %% "akka-http" % "10.1.11"

)