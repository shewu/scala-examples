package sandbox.remoteactors

import akka.actor.{
	ActorSystem,
	Props
}
import com.typesafe.config.ConfigFactory

object RemotePongApp extends App {
	val config = ConfigFactory.load().getConfig("remotepong")
	val actorSystem = ActorSystem("RemotePingPongSystem", config)

	val pong = actorSystem.actorOf(Props[RemotePong], name="pong")
}
