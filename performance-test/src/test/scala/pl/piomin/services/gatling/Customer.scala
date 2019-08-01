package pl.piomin.services.gatling

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import java.util.Date
import scala.concurrent.duration.FiniteDuration
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit
import scala.util.Random

class Customer extends Simulation {
  var scn = scenario("Insert Customer").exec(RegisterCustomer.registerCustomer).pause(10)
  setUp(scn.inject(atOnceUsers(1)).protocols(http))
}

object RegisterCustomer {

  val createCustomerUrl = "http://localhost:8093/customers"
  val customerDetailsJsonFeeder = jsonFile("customerdetails.json").circular

  val registerCustomer = feed(customerDetailsJsonFeeder)
    .exec(
      http("Insert Customer")
        .post(createCustomerUrl)
        .header("Content-Type", "application/json")
        .body(
          StringBody(
            """
              |{
              |    "id":"${id}",
              |    "name":"${name}",
              |    "availableFunds":"${availableFunds}",
              |    "type":"${type}"
              |}
            """.stripMargin
          )
        ).asJSON
        .check(status.is(200), jsonPath("$.id").saveAs("customerId"))
    )
}