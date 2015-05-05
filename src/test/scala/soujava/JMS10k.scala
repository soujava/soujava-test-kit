/**
 * @author: Thomas Modeneis
 *
 */

package soujava

import net.timewalker.ffmq3.FFMQConstants
import io.gatling.core.Predef._
import io.gatling.jms.Predef._
import scala.concurrent.duration._

import io.gatling.core.Predef._
import javax.jms._
import org.apache.activemq.ActiveMQConnectionFactory

//Work in progress, this still not ready to use parametrised
class JMS10k extends Simulation {

  val duration = Integer.getInteger("duration", 60).toInt

  def jmsConfig = jms
    .connectionFactoryName("ConnectionFactory")
    .url("tcp://localhost:61616")
    .contextFactory(classOf[org.apache.activemq.jndi.ActiveMQInitialContextFactory].getName)
    .listenerCount(1)
    .usePersistentDeliveryMode

  val scn = scenario("JMS DSL test").repeat(1) {
    exec(
      jms("req reply testing").reqreply
        .queue("queueName")
//        .replyQueue("queueName")
        .textMessage("hello from gatling jms dsl")
        .property("test_header", "test_value")
        .check(simpleCheck(checkBodyTextCorrect)))
  }

  setUp(scn.inject(rampUsersPerSec(10) to 10 during (60 seconds)))
    .protocols(jmsConfig)
    .maxDuration(duration)

  def checkBodyTextCorrect(m: Message) = {
    m match {
      case tm: TextMessage => tm.getText == "HELLO FROM GATLING JMS DSL"
      case _               => false
    }
  }

}