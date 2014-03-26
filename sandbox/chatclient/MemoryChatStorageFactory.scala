package sandbox.chatclient

import akka.actor.{
	Actor,
	ActorDSL
}

trait MemoryChatStorageFactory { this: Actor =>
	// spawns and links Actors atomically
	// new versions of akka do this automatically
	// see step 2 of http://docs.scala-lang.org/overviews/core/actors-migration-guide.html
	val storage = ActorDSL.actor(new MemoryChatStorage)
}
