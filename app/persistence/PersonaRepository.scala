package persistence

import javax.inject.{Inject, Singleton}

import domain.Person
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class PersonRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  private val Persons = TableQuery[PersonsTable]

  def all() : Future[Seq[Person]] = db.run(Persons.result)

  def insert(person: Person): Future[Unit] = db.run(Persons += person).map { _ => () }



  private class PersonsTable(tag: Tag) extends Table[Person](tag, "PERSON") {
    def id = column[String]("ID", O.PrimaryKey)
    def name = column[String]("NAME")

    def * = (id, name) <> ( (Person.apply _).tupled, Person.unapply _)
  }

}
