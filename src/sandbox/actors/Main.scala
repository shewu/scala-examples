package sandbox.actors

import akka.actor.{
	ActorSystem,
	Props
}

object Main extends App {
	val actorSystem = ActorSystem("PingPongSystem")

	val pong = actorSystem.actorOf(Props(classOf[LocalPong]), name="pong")
	val ping = actorSystem.actorOf(Props(classOf[LocalPing], 10000, pong))

//	ping.start
}
