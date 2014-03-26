package sandbox.chatclient

import akka.actor.{
	Actor,
	ActorLogging,
	ActorRef,
	Props
}

class Session(user: String, storage: ActorRef) extends Actor  {
	private val loginTime = System.currentTimeMillis
	private var userLog: List[String] = Nil

	//log.info("New session for user [%s] has been created at [%s]".format(user, loginTime))

	def receive = {
		case msg @ ChatMessage(from, message) =>
			userLog ::= message
			storage ! msg

		case msg @ GetChatLog(_) =>
			storage forward msg
	}

	override def postStop() = {
		//log.info("Session for [%s] has stopped".format(user))
	}
}

object Session {
	def props(user: String, storage: ActorRef): Props =
		Props(new Session(user, storage))
}
