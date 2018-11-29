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
class FileTestController @Inject()(cc: ControllerComponents)(implicit assetsFinder: AssetsFinder)
  extends AbstractController(cc) {



  def upload = Action(parse.multipartFormData) { request =>
    request.body.file("file").map { file =>
      val filename = Paths.get(file.filename).getFileName

      file.ref.moveTo(Paths.get(s"/tmp/file/$filename"), replace = true)
      Ok("File uploaded")
    }.getOrElse {
      BadRequest("Something went wrong")
    }
  }


  def getFile = Action {
    Ok.sendFile(new java.io.File("public/pdf/anyStuff.pdf"), true)
  }


}
