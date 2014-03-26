package sandbox.chatclient

import collection.mutable.HashMap
import akka.actor.{
	Actor,
	ActorLogging,
	ActorRef
}

trait ChatManagement extends ActorLogging { this: Actor =>
	val sessions: HashMap[String, ActorRef]

	protected def chatManagement: Receive = {
		case msg @ ChatMessage(from, _) =>
			getSession(from).foreach(_ ! msg)

		case msg @ GetChatLog(from) =>
			getSession(from).foreach(_ forward msg)
	}

	private def getSession(from: String): Option[ActorRef] = {
		if (sessions.contains(from)) {
			Some(sessions(from))
		} else {
			log.info("Session expired for %s".format(from))
			None
		}
	}
}
