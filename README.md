Scala Examples
==============

These require Java and Scala, and Ant if you want to use the provided buildfile.

Get started by typing `ant build` to compile.

Examples
--------
- `sandbox.actors` is an example for actors running locally (no remoting).
- `sandbox.remoteactors` is an example for remoting.
- `sandbox.chatclient` is a more advanced remoting example that demonstrates how classes and objects should be composed in scala. This is an updated version of http://doc.akka.io/docs/akka/1.3.1/scala/tutorial-chat-server.html .

Running
-------
- To run `sandbox.actors`, type `ant run-local-pingpong`.
- To run `sandbox.remoteactors`, open two terminals. In the first one, type `ant run-remote-pong`. In the other terminal, type `ant run-remote-ping`.
- To run `sandbox.chatclient`, open two terminals. In the first one, type `ant run-chat-server`. In the other terminal, type `ant run-chat-demo`.
