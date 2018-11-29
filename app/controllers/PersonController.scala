package controllers

import javax.inject._

import domain.Person
import persistence.PersonRepository
import play.api._
import play.api.libs.json.{Json, Writes}
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class PersonController @Inject()(cc: ControllerComponents, personRepository: PersonRepository)(implicit executionContext: ExecutionContext) extends AbstractController(cc) {




  def index() = Action.async { implicit request: Request[AnyContent] =>

    val futurePersons: Future[Seq[Person]] = personRepository.all()

    futurePersons.map(person => Ok( Json.toJson(person) ))
  }

 def add() = Action.async(parse.json[Person]) { request =>
    insertPerson(request.body)
  }


  private def insertPerson(person: Person): Future[Result] = {
    personRepository.insert(person)
      .map(_ => Ok(""))
      .recoverWith {
        case _: Exception => Future.successful( InternalServerError("Couldn't save register") )
      }
  }


}
