package sandbox.actors

sealed trait Event
case object Ping extends Event
case object Pong extends Event
