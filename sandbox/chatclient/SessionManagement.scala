package sandbox.chatclient

import collection.mutable.HashMap
import akka.actor.{
	Actor,
	ActorLogging,
	ActorRef,
	PoisonPill
}

trait SessionManagement extends ActorLogging { this: Actor =>
	val storage: ActorRef
	val sessions = new HashMap[String, ActorRef]

	protected def sessionManagement: Receive = {
		case Login(username) =>
			log.info("User [%s] has logged in".format(username))
			// the following is good practice
			// see http://doc.akka.io/docs/akka/snapshot/scala/actors.html
			val session = context.actorOf(Session.props(username, storage))
			sessions += (username -> session)

		case Logout(username) =>
			log.info("User [%s] has logged out".format(username))
			val session = sessions(username)
			session ! PoisonPill
			sessions -= username
	}

	protected def shutdownSessions =
		sessions.foreach { case (_, session) => session ! PoisonPill }
}
