import spray.http._
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
            "params" : { "msg": "hi" }
        }"""
    )
    
    val response: Future[HttpResponse] = pipeline(Post(config.getString("url"),body))

    response onComplete {
        case Success(response) => isExpectedResponse(response.entity.asString, config.getString("response"))
        case Failure(err) => println(err)
    }

    println(s"Testing method ${config.getString("method")} at ${config.getString("url")}")
  }
}