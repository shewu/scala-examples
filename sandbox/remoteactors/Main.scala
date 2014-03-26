package sandbox.remoteactors

import akka.actor.{
	ActorSystem,
	Props
}
import com.typesafe.config.ConfigFactory

object Main extends App {
	val actorSystem = ActorSystem("RemotePingPongSystem", ConfigFactory.load(ConfigFactory.parseString("""akka {
	loglevel = "DEBUG"
	stdout-loglevel = "DEBUG"

	actor {
		provider = "akka.remote.RemoteActorRefProvider"
	}

	remote {
		enabled-transports = ["akka.remote.netty.tcp"]
		netty.tcp {
			hostname = "127.0.0.1"
			port = 2552
		}
	}
}
""")))
	//println(actorSystem.settings)
	//val actorSystem = ActorSystem("RemotePingPongSystem", ConfigFactory.load(System.getProperty("config.resource")))
	//println(System.getProperty("config.resource"))

	val pong = actorSystem.actorOf(Props[RemotePong], name="pong")
	val ping = actorSystem.actorOf(Props(classOf[RemotePing], 16))
}
