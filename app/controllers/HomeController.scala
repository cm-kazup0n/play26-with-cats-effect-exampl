package controllers

import cats.effect.{ContextShift, Effect, IO}
import infra.ExternalAPI
import infra.vo._
import play.api.libs.circe.Circe
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}
import io.circe.syntax._
import io.circe.generic.auto._

import scala.language.higherKinds

class HomeController(cc: ControllerComponents)(implicit cs:ContextShift[IO], api: ExternalAPI[IO], ec:ExecutionContext) extends AbstractController(cc) with Circe {

  implicit def effectToFuture[F[_]:Effect, A](f: F[A]): Future[A] = Effect[F].toIO(f).unsafeToFuture

  def index:Action[AnyContent] = Action.async(_ =>
    for {
      _ <- cs.shift
      res <- api.post(Message("hello"))
    } yield Ok(res.asJson)
  )


}
