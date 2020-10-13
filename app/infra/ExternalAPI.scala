package infra

import cats.effect.IO
import cats.implicits._
import com.typesafe.scalalogging.Logger
import hammock.Hammock
import hammock.circe.implicits._
import hammock._
import infra.vo._
import io.circe.generic.auto._
import io.circe.syntax.EncoderOps

import scala.language.higherKinds



trait ExternalAPI[F[_]] {
  def post(msg:Message): F[Response]
}

object ExternalAPI {
  implicit def onIO(implicit interpreter: InterpTrans[IO]):ExternalAPI[IO] = new ExternalAPI[IO]  {
    private val logger = Logger[ExternalAPI[IO]]
    override def post(msg:Message): IO[Response] =
    IO( logger.info("calling external API")) *>
      Hammock.request(
        Method.POST,
        uri"https://httpbin.org/post",
        Map.empty,
        msg.asJson.some)
      .as[Response]
      .exec[IO]
  }





}
