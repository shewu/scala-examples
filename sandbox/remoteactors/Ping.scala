package sandbox.remoteactors

import akka.actor.{
	ActorSystem,
	Props
}
import com.typesafe.config.ConfigFactory

object RemotePingApp extends App {
	val config = ConfigFactory.load().getConfig("remoteping")
	implicit val actorSystem = ActorSystem("RemotePingPongSystem", config.withFallback(config))

	val ping = actorSystem.actorOf(Props(classOf[RemotePing], 16), name="ping")
}
