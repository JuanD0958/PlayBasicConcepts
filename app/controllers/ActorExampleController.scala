package controllers

import play.api.mvc._
import akka.actor._
import javax.inject._  
import actors.HelloActor
import scala.concurrent.duration._
import akka.pattern.ask
import akka.util.Timeout
import akka.actor.ActorSystem
import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.concurrent.duration._
import akka.pattern.ask

@Singleton
class ActorExampleController @Inject() (system: ActorSystem, cc:ControllerComponents)
  (implicit exec: ExecutionContext) extends AbstractController(cc) {

val helloActor: ActorRef = system.actorOf(HelloActor.props, "hello-actor")
  	
implicit val timeout: Timeout = 5.seconds

def sayHello(name: String) = Action.async {
  (helloActor ? HelloActor.SayHello(name)).mapTo[String].map { message =>
    Ok(message)
  }
}


def putSomeString(something: String) = Action.async {
	(helloActor ? HelloActor.Put(something)).mapTo[String].map{
		list => Ok(list)
	}
}

// ELIMINA UN ACTOR
def cleanActor(actor: ActorRef)={
	actor ! PoisonPill
}

}