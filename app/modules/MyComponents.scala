package modules

import _root_.controllers.AssetsComponents
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import cats.effect.{ContextShift, IO}
import hammock.akka.AkkaInterpreter
import play.api._
import _root_.controllers.HomeController
import play.api.routing.Router
import play.filters.HttpFiltersComponents
import play.libs.concurrent.CustomExecutionContext
import router.Routes

import scala.concurrent.ExecutionContext
import scala.language.higherKinds


class MyComponents(context: ApplicationLoader.Context)
  extends BuiltInComponentsFromContext(context) with AssetsComponents with HttpFiltersComponents {

  LoggerConfigurator(context.environment.classLoader).foreach {
    _.configure(context.environment, context.initialConfiguration, Map.empty)
  }

  lazy val externalAPIContext: ExecutionContext =
    new CustomExecutionContext(actorSystem, "dispatchers.external-api-io-dispatcher") {}
  implicit val cs: ContextShift[IO] = IO.contextShift(externalAPIContext)
  implicit val mat:ActorMaterializer = materializer.asInstanceOf[ActorMaterializer]


  implicit val akkaInterpreter: AkkaInterpreter[IO] = new AkkaInterpreter[IO](Http(actorSystem))

  lazy val homeController:HomeController = new HomeController(controllerComponents)

  lazy val router: Router = new Routes(httpErrorHandler, homeController)
}
