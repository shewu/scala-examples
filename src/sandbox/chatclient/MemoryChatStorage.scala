package sandbox.chatclient

import collection.mutable.ArrayBuffer
import akka.actor.{
	ActorLogging
}

class MemoryChatStorage extends ChatStorage with ActorLogging {
	private var chatLog = ArrayBuffer[Array[Byte]]()

	log.info("Memory-based chat storage is starting up...")

	def receive = {
		case msg @ ChatMessage(from, message) =>
			chatLog.synchronized {
				chatLog append message.getBytes("UTF-8")
			}

		case GetChatLog(_) =>
			chatLog.synchronized {
				val messageList = chatLog.map(bytes => new String(bytes, "UTF-8")).toList
				sender ! ChatLog(messageList)
			}
	}

	override def postRestart(reason: Throwable) = chatLog = ArrayBuffer[Array[Byte]]()
}
