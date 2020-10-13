package infra

import io.circe.Json

package object vo {
  final case class Message(msg:String)
  final case class Response(headers: Map[String, String], json:Json)
}
