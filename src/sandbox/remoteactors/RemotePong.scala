package sandbox.remoteactors

import akka.actor.{
	Actor,
	ActorLogging,
	PoisonPill
}

class RemotePong extends Actor with ActorLogging {
	log.info("Pong started at " + self.path)
	def receive = {
		case Ping(pongsLeft) =>
			log.info("Pong: ping")
			sender ! Pong(pongsLeft - 1)
	}

	override def postStop() = {
		log.info("Pong: stop")
	}
}
