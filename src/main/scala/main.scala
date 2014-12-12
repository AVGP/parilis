import spray.http._
import spray.client.pipelining._
import akka.actor.ActorSystem
import scala.concurrent._
import scala.util.{Success, Failure}

object Hi {
  def main(args: Array[String]) {
    implicit val system = ActorSystem()
    import system.dispatcher // execution context for futures
    
    val pipeline: HttpRequest => Future[HttpResponse] = sendReceive
    
    val response: Future[HttpResponse] = pipeline(Get("http://quotes.zencu.be/"))

    response onComplete {
        case Success(response) => println(response.entity.asString)
        case Failure(err) => println(err)
    }

    println("Hi!")
  }
}