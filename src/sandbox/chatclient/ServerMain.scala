package sandbox.chatclient

import akka.actor.{
	ActorSystem,
	Props
}
import com.typesafe.config.ConfigFactory

object ServerMain extends App {
	val config = ConfigFactory.load().getConfig("chatserver")
	val actorSystem = ActorSystem("ChatSystem", config)
	val chatService = actorSystem.actorOf(Props[ChatService], name="chatService")
}
