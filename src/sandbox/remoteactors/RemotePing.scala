package sandbox.remoteactors

import akka.actor.{
	Actor,
	ActorLogging,
	PoisonPill
}

class RemotePing(count: Int) extends Actor with ActorLogging {
	// XXX this needs to use 127.0.0.1 instead of localhost
	val pong = context.actorSelection("akka.tcp://RemotePingPongSystem@127.0.0.1:2552/user/pong")
	log.info("Ping started")

	pong ! Ping(count)
	log.info("Ping sent a message")

	def receive = {
		case Pong(0) =>
			log.info("Ping: start termination")
			pong ! PoisonPill
			context.stop(self)

		case Pong(pingsLeft) =>
			log.info("Ping: pong %d".format(pingsLeft))
			pong ! Ping(pingsLeft)
	}

	override def postStop = {
		log.info("Ping: terminated")
	}
}
