package sandbox.chatclient

import akka.actor.{
	Actor,
	ActorLogging,
	ActorRef,
	OneForOneStrategy,
	PoisonPill
}
import akka.actor.SupervisorStrategy.Restart
import scala.concurrent.duration._
import scala.language.postfixOps

trait ChatServer extends Actor with ActorLogging {
	override val supervisorStrategy = OneForOneStrategy(maxNrOfRetries=5, withinTimeRange=5 minute) {
		case _: Exception => Restart
	}

	val storage: ActorRef

	log.info("Chat server is starting up...")

	def receive: Receive = sessionManagement orElse chatManagement

	protected def chatManagement: Receive
	protected def sessionManagement: Receive
	protected def shutdownSessions(): Unit

	override def postStop() = {
		log.info("Chat server is shutting down...")
		shutdownSessions
		storage ! PoisonPill
	}
}
