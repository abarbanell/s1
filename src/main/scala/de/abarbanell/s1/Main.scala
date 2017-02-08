package de.abarbanell.s1

import akka.actor.Actor

import scala.util.Random

/**
  * Created by tobias on 02.02.17.
  */
object Main extends App {
  println("Main started.")


}

case class DrawRequest(n: Int)
case class DrawResult(n: Int)

class DrawNumber extends Actor {

  def receive =  {
    case DrawRequest(n) => {
      println(s"received DrawRequest($n)")
      sender ! DrawResult(Random.nextInt(n))
    }
  }

}