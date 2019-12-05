package org

import java.net.URL

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import _root_.org.scalatest.Assertions
import akka.http.scaladsl.marshalling.PredefinedToEntityMarshallers
import akka.http.scaladsl.model.{HttpEntity, HttpMethods, HttpRequest, MediaTypes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.util.ByteString

import scala.io.Source

class RouteTest
    extends AnyWordSpec
    with Matchers
    with ScalatestRouteTest
    with PredefinedToEntityMarshallers {

  "The service" should {
    "return a greeitings for GET requets to the root path " in {

      Get("/api/new/info") ~> WebServer.route ~> check {
        val result = responseAs[String]
        Assertions.assert(result equals "Hello")
      }

    }
  }

  "testing create user method" should {
    "should return correct response" in {
      print(JsonTestUtils.getJsonData("data/user-data.json"))

      val jsonRequest =
        ByteString(JsonTestUtils.getJsonData("data/user-data.json"))

      val postRequest = HttpRequest(
        HttpMethods.POST,
        uri = "/api/user/create",
        entity = HttpEntity(MediaTypes.`application/json`, jsonRequest)
      )

      postRequest ~> WebServer.route ~> check {
        status.isSuccess() shouldEqual true
      }
    }
  }

  "testing update user method" should {
    "should return correct response" in {
      print(JsonTestUtils.getJsonData("data/user-data.json"))

      val jsonRequest =
        ByteString(JsonTestUtils.getJsonData("data/user-data.json"))

      val postRequest = HttpRequest(
        HttpMethods.PUT,
        uri = "/api/user/update",
        entity = HttpEntity(MediaTypes.`application/json`, jsonRequest)
      )

      postRequest ~> WebServer.route ~> check {
        status.isSuccess() shouldEqual true
      }
    }
  }
}

object JsonTestUtils {
  val EMPTY = ""
  val NEW_line = "\n"
  val TAB_CHAR = "\t"
  val SPACES = "  "

  val purifyData: String => String = (str: String) => {
    str
      .stripLeading()
      .stripTrailing()
      .stripLineEnd
      .replaceAll(NEW_line, EMPTY)
      .replaceAll(TAB_CHAR, EMPTY)
      .replaceAll(SPACES, EMPTY)
  }

  val getJsonData: String => String = (fileName: String) => {
    val url: URL = getClass.getResource("/" + fileName)
    val source = Source.fromFile(url.getPath)
    val rowData = source.iterator.mkString("")
    source.close()
    purifyData(rowData)
  }

}
