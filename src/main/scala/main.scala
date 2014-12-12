import spray.http._
import spray.json._
import DefaultJsonProtocol._
import spray.client.pipelining._
import akka.actor.ActorSystem
import scala.concurrent._
import scala.util.{Success, Failure}
import spray.httpx.SprayJsonSupport._
import HttpCharsets._
import MediaTypes._
import com.typesafe.config.ConfigFactory

object Application {
  private def isExpectedResponse(actual : String, expected : String) : Boolean = {
      if(actual == expected) {
        println("API responded correctly.")
        return true
      } else {
        println(s"Error: Got ${actual} where ${expected} was expected.")
        return false
      }
  }
  def main(args: Array[String]) {
    implicit val system = ActorSystem()
    import system.dispatcher // execution context for futures
    
    val config = ConfigFactory.load()
    
    val pipeline: HttpRequest => Future[HttpResponse] = sendReceive

    val body = HttpEntity(
      contentType = ContentType(`application/json`, `UTF-8`),
      string =
        s"""{
            "jsonrpc" : "2.0",
            "id" : "${config.getString("id")}",
            "method" : "${config.getString("method")}",
            "params" : ${config.getString("params")}
        }"""
    )
    
    val response: Future[HttpResponse] = pipeline(Post(config.getString("url"),body))

    response onComplete {
        case Success(response) => {
          if(isExpectedResponse(response.entity.asString, config.getString("response")) == true) {
            system.shutdown()
            System.exit(0)
          } else {
            system.shutdown()
            System.exit(1)
          }
        }
        case Failure(err) => {
          println(err)
          system.shutdown()
          System.exit(1)
        }
    }

    println(s"Testing method ${config.getString("method")} at ${config.getString("url")}")
  }
}