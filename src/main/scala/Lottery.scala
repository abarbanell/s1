import akka.actor.Actor
import scala.util.Random

/**
  * Created by tobias on 29.01.17.
  */

object Lottery {
  case class DrawRequest(n: Int)
  case class DrawResult(n: Int)
}

class Lottery extends Actor {
  import Lottery._
  def receive = {
    case DrawRequest(max) => sender ! DrawResult(Random.nextInt(max))
  }
}
