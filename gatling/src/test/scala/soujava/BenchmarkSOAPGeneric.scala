/**
 * @author Thomas Modeneis
 */
package soujava

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class BenchmarkSOAPGeneric extends Simulation {

  //Definitions for the endpoint 1
  def threads_ = System.getProperty("NUM_THREADS")

  def rampup_ = System.getProperty("RAMP_TIME")

  def duration_ = System.getProperty("DURATION")

  def target = System.getProperty("TARGET")

  def endpoint_ = System.getProperty("ENDPOINT")

  def endpointName_ = System.getProperty("ENDPOINT_NAME")

  def testFile = System.getProperty("TEST_FILE")

  def statusCheck = System.getProperty("STATUS_CHECK")

  val IntStatusCheck = Integer.getInteger("statusCheck", statusCheck.toInt).toInt

  def regexCheck = System.getProperty("REGEX_CHECK")

  //Definitions for the Switch
  def randomSwitch_Target1 = System.getProperty("TARGET1_SWITCH")

  //Definitions for the test itself (basics)
  val threads = Integer.getInteger("threads", threads_.toInt)
  val rampup = Integer.getInteger("rampup", rampup_.toInt).toInt
  val duration = Integer.getInteger("duration", duration_.toInt).toInt

  def randSwitch1 = randomSwitch_Target1.toDouble

  val httpConf = http
    .baseURL(target)

  def extractMessage(str: String, session: Session): String = {
    println("extractMessage "+str)
    var ID = str
    println("---- ID --------------------------------------------------------------")
    println(ID)
    println("---- ID --------------------------------------------------------------")
    ID
  }

  def generateMessage(session: Session): String = {
    val myID = session("myID").as[String]

    var message =
      """
        <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body><xml xmlns="http://schema.soujava.org.br/webservice/wsdl/1.1">"""+myID+"""</xml></soap:Body></soap:Envelope>
      """

    println("---- Message --------------------------------------------------------------")
    println(message)
    println("---- message --------------------------------------------------------------")

    message
  }


  val get_endpoint =
    exec(http(endpointName_).post(endpoint_).body(RawFileBody(testFile)).check(status.is(IntStatusCheck))
      .check(regex(regexCheck).exists)
      .check(xpath(
        "/soap:Envelope/soap:Body/w:WebServiceInvocationResult/w:message/text()",
        List(
          ("w", "http://schema.soujava.org.br/webservice/wsdl/1.1"),
          ("soap", "http://schemas.xmlsoap.org/soap/envelope/")
        ))
        .find
        .transform((m, session) =>
          extractMessage(m,session)
        ).saveAs("myID")))
      .exec(http(endpointName_).post(endpoint_).body(StringBody(session => "" + generateMessage(session))).asXML.check(status.is(IntStatusCheck))
        .check(regex(regexCheck).exists))

  val scn = scenario(endpointName_)
    .forever("counter") {
      randomSwitch(
        randSwitch1 -> get_endpoint
      )
    }
  setUp(scn.inject(rampUsers(threads) over rampup))
    .protocols(httpConf)
    .maxDuration(duration)
}



