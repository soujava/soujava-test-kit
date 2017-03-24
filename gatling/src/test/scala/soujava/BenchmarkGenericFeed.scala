/**
  * @author: Thomas Modeneis
  */
package soujava

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class BenchmarkGenericFeed extends Simulation {

  def threads_ = System.getProperty("NUM_THREADS")
  def rampup_ = System.getProperty("RAMP_TIME")
  def duration_ = System.getProperty("DURATION")
  def target = System.getProperty("TARGET")
  def endpointName_ = System.getProperty("ENDPOINT_NAME")

  val threads = Integer.getInteger("threads", threads_.toInt)
  val rampup = Integer.getInteger("rampup", rampup_.toInt).toInt
  val duration = Integer.getInteger("duration", duration_.toInt).toInt

  val feeder = csv(target).random

  val get_endpoint = feed(feeder).exec(http("${name}").get("${url}"))

  val scn = scenario(endpointName_)
    .forever("counter") {
      randomSwitch(100.0 -> get_endpoint)
    }
  setUp(scn.inject(rampUsers(threads) over rampup))
    .maxDuration(duration)
}
