
package domain
import play.api.libs.json.{Json, Reads, Writes,OFormat,OWrites}
import play.api.libs.json.JsPath
import play.api.libs.functional.syntax._

case class Person(val id: String, val name: String)

object Person {
  implicit val personWrites: Writes[Person] = ((JsPath \ "id").write[String] and (JsPath \ "name").write[String])(unlift(Person.unapply))
  implicit val placeReads: Reads[Person] = ((JsPath \ "id").read[String] and (JsPath \ "name").read[String] )(Person.apply _)
  


  var list: List[Person]  = { List(
    Person  ("1", "Juan"),
    Person("2", "Miguel"),
  )}

  def save(person: Person) = {
    list = list :+ person
  }


}