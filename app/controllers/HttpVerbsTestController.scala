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
class HttpVerbsTestController @Inject()(cc: ControllerComponents) (implicit assetsFinder: AssetsFinder)
  extends AbstractController(cc) {


  def getRequest = Action { request => Ok("This is a GET request") }
  def postRequest = Action { request => Ok("This is a POST request") }
  def putRequest = Action { request => Ok("This is a PUT request") }
  def deleteRequest = Action { request => Ok("This is a DELETE request") }
  def patchRequest = Action { request => Ok("This is a PATCH request") }


}
