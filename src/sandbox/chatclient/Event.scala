package sandbox.chatclient

sealed trait Event
case class Login(user: String) extends Event
case class Logout(user: String) extends Event
case class GetChatLog(from: String) extends Event
case class ChatLog(chatLog: List[String]) extends Event
case class ChatMessage(from: String, message: String) extends Event

sealed trait ClientEvent
case class MyLogin() extends ClientEvent
case class MyLogout() extends ClientEvent
case class MyGetChatLog() extends ClientEvent
case class Post(message: String) extends ClientEvent
