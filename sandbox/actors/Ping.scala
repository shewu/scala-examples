package sandbox.actors

import akka.actor.{
	Actor,
	ActorLogging,
	ActorRef,
	PoisonPill
}

class LocalPing(count: Int, pong: ActorRef) extends Actor with ActorLogging {
	var pingsLeft = count - 1

//	def start = pong ! Ping
	pong ! Ping

	def receive = {
		case Pong =>
			if (pingsLeft % 1000 == 0) {
				log.info("Ping: pong")
			}

			if (pingsLeft > 0) {
				pong ! Ping
				pingsLeft -= 1
			} else {
				log.info("Ping: stop")
				pong ! PoisonPill
				context.stop(self)
			}
	}
}
