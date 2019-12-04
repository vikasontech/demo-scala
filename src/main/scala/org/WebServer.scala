package org
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import scala.io.StdIn
import akka.http.scaladsl.server.Route

object WebServer {
  def main(args: Array[String]) {

    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher

    val route: Route =
      concat(
        path("hello") {
          concat(path("hello3") {
            get {
              complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "hello"))
            }
          })
        },
        path("hello1") {
          get {
            complete(HttpEntity("Hello1"))
          }
        },
        pathPrefix("hello1") {
          concat(path("hello12") {
            get {
              complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "hello"))
            }
          }, path("hello21") {
            get {
              complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "hello21"))
            }
          })
        },
        path("hello2") {
          get {
            complete(
              HttpEntity(ContentTypes.`text/html(UTF-8)`, "Hello! again")
            )
          }
        }
      )

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
