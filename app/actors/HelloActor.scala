package actors

import akka.actor._
object HelloActor {
  
  def props = Props[HelloActor] // Para instanciar un actor se necesita el Props. ( Buena practica al hacerlo en el companion object)
  case class SayHello(name: String) // Mensajes que espera el actor
  case class Put(something: String) // Mensajes que espera el actor
}

class HelloActor extends Actor {
  import HelloActor._
  var list: List[String] = List.empty[String]
  def receive = {
    case SayHello(name: String) =>
      sender() ! "Hello, " + name
    case Put(something: String) => 
      list = something :: list
      sender() ! list.mkString(",") // Sender() --> Es la referencia con el ultimo actor del que se recibe el mensaje.
    case _ => sender() ! "Something went wrong!"
  }
}