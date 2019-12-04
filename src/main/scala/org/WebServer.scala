package org
import scala.annotation.implicitNotFound
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.ContentType
import akka.http.scaladsl.model.ContentTypes
import akka.http.scaladsl.Http
import scala.io.StdIn

object WebServer1 extends App {

  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val route =
    path("hello") {
      complete(
        HttpEntity(
          ContentTypes.`text/html(UTF-8)`,
          "<h1>JSR; Say hello to akka-http</h1>"
        )
      )
    }

  val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

  println(
    "server is running on http://localhost:8080\nPress RETURN to stop ..."
  )
  StdIn.readLine()
  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())
}
