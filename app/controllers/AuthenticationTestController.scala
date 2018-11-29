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
class AuthenticationTestController @Inject()(cc: ControllerComponents) (implicit assetsFinder: AssetsFinder)
  extends AbstractController(cc) {


  def getHeader = Action {request =>
    val headers: Headers = request.headers
    val ucType: Option[String] = headers.get("Content-Type")
    val lcType: Option[String] = headers.get("content-type")


    Ok(Seq(
      s"Headers: $headers",
      s"Content-Type: $ucType",
      s"content-type: $lcType",
    ) mkString "\n")
  }

  def getCookies=Action{request =>
    val cookies: Cookies = request.cookies
    val cookie: Option[Cookie] = cookies.get("myCookie")
    val value: Option[String] = cookie.map(_.value)

    Ok(Seq(
      s"Cookies: $cookies",
      s"Cookie value: $value"
    ) mkString "\n")
  }

  def requestWithHeader() = Action {
    request => {
      val headers = request.headers
      val authentication = headers.get("Authentication").get
      Ok(s"request received with an auth token of: ${authentication}")
    }
  }

  def authentication = Action.async { request =>
    Future {
      val token: Option[String] = request.headers.get("tkn")
      request.headers.get("tkn").map(tkn => Ok(s"Token is $tkn")).getOrElse(BadRequest("Authentication failed, missing token"))
    }
  }

}
