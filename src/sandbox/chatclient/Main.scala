package sandbox.chatclient

import akka.actor.{
	ActorSystem,
	Props
}
import scala.language.postfixOps
import com.typesafe.config.ConfigFactory

object Main extends App {
	val config = ConfigFactory.load().getConfig("chatclient")
	val actorSystem = ActorSystem("ChatSystem", config)

	val alice = actorSystem.actorOf(Props(classOf[ChatClient], "Alice"))
	val bob = actorSystem.actorOf(Props(classOf[ChatClient], "Bob"))

	alice ! MyLogin
	bob ! MyLogin

	alice ! Post("Hello I am alice")
	bob ! Post("Hi my name is bob")
	alice ! Post("Where is everyone?")

	alice ! MyGetChatLog
}
