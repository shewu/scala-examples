package sandbox.actors

import akka.actor.{
	Actor,
	ActorLogging,
	PoisonPill
}

class LocalPong extends Actor with ActorLogging {
	var pongCount = 0

	println("hello localpong")
	def receive = {
		case Ping =>
			if (pongCount % 1000 == 0) {
				log.info("Pong: ping " + pongCount)
			}

			sender ! Pong
			pongCount += 1

		case PoisonPill =>
			context.stop(self)
	}

	override def postStop() = {
		log.info("Pong: stop")
	}
}
