package controllers

import play.api.mvc._
import javax.inject._
import services.Calculator

@Singleton
class CalculatorController @Inject() (cc: ControllerComponents, calculator: Calculator) extends AbstractController(cc) {
  def sum(a:Int, b:Int) = Action{
    val result: Integer = calculator.sum(a,b)
    Ok(result.toString)
  }

  def subtract(a: Int, b: Int) = Action {
      val result: Integer = calculator.subtract(a,b)
      Ok(result.toString)
  }

  def mult(a:Int, b:Int) = Action{
    val result: Integer = calculator.mult(a,b)
    Ok(result.toString)
  }

  def divide(a:Int, b:Int) = Action{
    try{
      val result: Integer = calculator.divide(a,b)
      Ok(result.toString)
    }catch{
      case e: IllegalArgumentException => BadRequest("Error! Division by ZERO")
    }
  }
}
