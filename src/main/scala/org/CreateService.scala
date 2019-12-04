package org

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import scala.io.StdIn
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.unmarshalling.{FromRequestUnmarshaller}
im

case class User (name: String, address: String)

object CreateService {
  pathPrefix("create") {
    concat(
      path("user") {
        post {
          entity(as[User]) {

          }
        }
      },
      path("details") {
        get {
          complete(HttpEntity("/api/new/details"))
        }
      }
    )
  }
}