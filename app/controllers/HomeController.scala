package controllers

import javax.inject._
import play.api.mvc._
import scala.concurrent._
import ExecutionContext.Implicits.global
import play.api.libs.json._
import akka.stream.scaladsl.{FileIO, Source, StreamConverters}
import akka.util.ByteString
import play.api.http.HttpEntity
import play.api.mvc.{BaseController, ControllerComponents, ResponseHeader, Result}

import play.api.libs.ws._
import java.nio.file.Paths
import domain._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents)(ws: WSClient) (implicit assetsFinder: AssetsFinder)
  extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def webServiceGet = Action.async {
      val requestWs: WSRequest = ws.url("https://reqres.in/api/users?page=2")

      requestWs.get().map(
        response =>
          Ok(response.json)

      )

  }


  def listSync = Action {
    Ok(Json.toJson(Person.list))
  }

  def listAsync = Action.async {
    Future.successful(
      Ok(Json.toJson(Person.list))
    )
  }


}
