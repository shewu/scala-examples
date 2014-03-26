package sandbox.chatclient

import akka.actor.Actor
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import scala.concurrent.Await
import scala.language.postfixOps

class ChatClient(name: String) extends Actor {
	implicit val timeout = Timeout(10 seconds)
	val chat = context.actorSelection("akka.tcp://ChatSystem@127.0.0.1:2552/user/chatService")

	private def displayChatLog(cl: List[String]) = {
		println(name + "'s chat log:\n\t" + cl.mkString("\n\t"))
	}

	def receive = {
		case MyLogin => chat ! Login(name)
		case MyLogout => chat ! Logout(name)
		case Post(message) => chat ! ChatMessage(name, name + ": " + message)
		case MyGetChatLog => Await.result(chat ? GetChatLog(name), 5 seconds) match {
			case ChatLog(cl) => displayChatLog(cl)
			case None => throw new Exception("Couldn't get the chat log from ChatServer")
		}
	}
}
