package org

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import scala.io.StdIn
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.unmarshalling.{FromRequestUnmarshaller}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._


case class User (name: String, address: String)


object CreateService {
  implicit val userFormat = jsonFormat2(User)

  val createServiceRoute = pathPrefix("user") {
    concat(
      path("create") {
        post {
          entity(as[User]) { user =>
            complete(s"user received $user")
          }
        }
      },
      path("update") {
        put {
          entity(as[User]) { user =>
            complete(s"User updated $user")
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
