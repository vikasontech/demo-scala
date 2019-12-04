package org
import _root_.org.scalatest.wordspec.AnyWordSpec
import _root_.org.scalatest.matchers.must.Matchers
import akka.http.scaladsl.testkit.ScalatestRouteTest

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.http.scaladsl.server._
import Directives._
import _root_.org.scalatest.Assertions
import akka.http.javadsl.model.HttpEntity

class RouteTest extends AnyWordSpec with Matchers with ScalatestRouteTest {
  // val smallRoute =
  // get {
  //   concat(
  //     pathSingleSlash {
  //       complete {
  //         "Captain on the bridge!"
  //       }
  //     },
  //     path("ping") {
  //       complete("PONG!")
  //     }
  //   )
  // }

  "The service" should {
    "return a greeitings for GET requets to the root path " in {

      Get("/api/new/info") ~> WebServer.route ~> check {
        val result = responseAs[String]
        Assertions.assert(result equals "Hello")
      }

    }
  }

}
