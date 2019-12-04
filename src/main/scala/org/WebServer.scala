package org
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import scala.io.StdIn
import akka.http.scaladsl.server.Route

object paths {
  val pathHello1 = path("hello") {
    concat(path("hello3") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "hello"))
      }
    })
  }
}

object WebServer {
  def main(args: Array[String]) {

    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    val route: Route =
      pathPrefix("api") {
        concat(
          path("info") {
            get {
              complete(HttpEntity("this is /api/info path"))
            }
          },
          path("details") {
            get {
              complete(HttpEntity("this is /api/details path"))
            }
          }
        )
      }

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
