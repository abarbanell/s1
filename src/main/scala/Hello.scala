import akka.actor.{ActorSystem, Inbox, Props}
import scala.concurrent.duration._


/**
  * Created by tobias on 17.01.17.
  */
object Hello extends App {

  val system = ActorSystem("s1")

  val inbox = Inbox.create(system)

  val histo = system.actorOf(Props[Histo], "histo")

  inbox.send(histo, HistoRequest(5000))

  val HistoResult(res) = inbox.receive(5.seconds)
  println(s"historesult received.")

  res.indices foreach  (x => {
      val v = res(x)
      val k = x+1
      println(s"$k: $v")
    }
    )

  system.terminate()
}

