import akka.actor.{Actor, ActorRef, Props}

/**
  * Created by tobias on 02.02.17.
  */
case class HistoRequest(n: Int)
case class HistoResult(res: Array[Int])

class Histo extends Actor {
  import Lottery._
  val lottery = context.actorOf(Props[Lottery], "lottery")
  var results = Array.tabulate(49){_ => 0}
  var counter = 0
  var limit = 0
  var master: ActorRef = self

  def doHisto(n: Int): Unit = {
    println(s"doHisto $n")
    results = Array.tabulate(49){_ => 0}
    counter = 0
    limit = n
    1 to n foreach(x => { lottery !  DrawRequest(49) })
  }

  def receive = {
    case HistoRequest(num) => {
      master = sender()
      doHisto(num)
    }

    case DrawResult(num) => {
      results(num) += 1
      counter += 1
      if (counter == limit) {
        println("counter limit reached.")
        master ! HistoResult(results)
      }
    }
  }
}
