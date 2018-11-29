
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
class JsonTestController @Inject()(cc: ControllerComponents) (implicit assetsFinder: AssetsFinder)
  extends AbstractController(cc) {

  def jsValueExample = Action {
    request => {
      val json: JsValue = Json.parse(
        """
        {
          "name" : "Juan David",
          "location" : {
            "lat" : 51.235685,
            "long" : -1.309197
          },
          "stuff" : [ {
            "name" : "Fiver",
            "age" : 15,
            "role" : null
          }]
        }
        """)

      Ok(json)
    }
  }

  /*

  The Play JSON API provides implicit Writes for most basic types, such as Int, Double, String, and Boolean.
  It also supports Writes for collections of any type T that a Writes[T] exists.
   */

  def jsonExampleWrite() = Action {
    val jsonString = Json.toJson("hola mundo")
    Ok(jsonString)

  } 
}


