package sandbox.remoteactors

sealed trait Event
case class Ping(n: Int) extends Event
case class Pong(n: Int) extends Event
