/**
 * @author: Thomas Modeneis
 */
package soujava

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

//Work in progress, this still not ready to use parametrised
class WebsocketSimulation extends Simulation {
  val duration = Integer.getInteger("duration", 60).toInt

  val httpConf = http
    .baseURL("http://localhost:9191")
    .wsBaseURL("ws://localhost:9193/ws")

  val scn = scenario("WebSocket")
    .exec(ws("Open WS")
    .open("/ws"))
    .pause(1)
    .repeat(2, "i") {
    exec(ws("Subscribe a WS topic")
      .sendText("hello")
//      .check(wsAwait.within(30).until(1).regex(".*I'm still alive.*"))
//       .check(wsAwait.within(30).until(1).regex(".*<RD>*"))
    )
    .pause(1)
  }
//  .exec(ws("Close WS").close)

  setUp(scn.inject(rampUsersPerSec(10) to 10 during (60 seconds)))
    .protocols(httpConf)
    .maxDuration(duration)

}