package org

import java.nio.file.attribute.UserPrincipalLookupService

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.github.swagger.akka.SwaggerHttpService

import scala.reflect.runtime.{universe => ru}

private class SwaggerDocService(
    address: String,
    port: Int,
    system: ActorSystem
) extends SwaggerHttpService{
  protected implicit val actorSystem: ActorSystem = system
  implicit val meterializer: ActorMaterializer = ActorMaterializer()
  val apiTypes = Seq(ru.typeOf[CreateService])
  override val host = address + ":"+port
//  val info = Info
  override def apiClasses: Set[Class[_]] = {
    Set(classOf[CreateService])
  }
}
