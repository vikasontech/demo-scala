import akka.management.scaladsl.AkkaManagement
import akka.actor.ActorSystem

object Main  {
    val system = ActorSystem("mySystem")
    AkkaManagement(system).start()
}
