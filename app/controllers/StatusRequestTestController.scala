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

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class StatusRequestTestController @Inject()(cc: ControllerComponents) (implicit assetsFinder: AssetsFinder)
  extends AbstractController(cc) {



  def goodResponse = Action { request => Ok("This is a test of a 200 response") }

  def badResponse = Action { request => BadRequest("This is a test of a 404 response") }

  def serverErrorResponse = Action { request => InternalServerError("Something happend, error in server") }

  def usePathParams(name: String)=Action{
    Ok(s"Hello ${name}, this service uses path params")

  }

  def useQueryParams(name: String)=Action{
    Ok(s"Hello ${name}, this service uses query params")
  }

}
