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
  var customerscn = scenario("Insert Customer").exec(RegisterCustomer.registerCustomer).pause(30)
  var customerODSscn = scenario("Insert Customer ODS").exec(RegisterCustomerODS.registerCustomerODS).pause(30)
  setUp(customerscn.inject(atOnceUsers(1)).protocols(http),customerODSscn.inject(atOnceUsers(1)).protocols(http))
}

object RegisterCustomer {

  val createCustomerUrl = "http://localhost:8093/customers"
  val customerDetailsJsonFeeder = jsonFile("customerdetails.json").circular
  val insertCustomer = http("Insert Customer")
    .post(createCustomerUrl)
    .header("Content-Type", "application/json")
    .header("Accept", "application/json")
    .body(
      RawFileBody("customerdetails.json")
    ).asJSON
    .check(status.is(200), jsonPath("$.id").saveAs("customer"))


  val registerCustomer = scenario("Register Customer Feed").exec(RegisterCustomer.insertCustomer).exitHereIfFailed
}
object RegisterCustomerODS {

  val createCustomerODSUrl = "http://localhost:8094/customers"
  val customerDetailsJsonFeeder = jsonFile("customerdetails.json").circular


  val registerCustomerODS = scenario("Register ODS Customer Feed").exec(
      http("Insert Customer ODS")
        .post(createCustomerODSUrl)
        .header("Content-Type", "application/json")
        .body(
          RawFileBody("customerdetails.json")
        ).asJSON
        .check(status.is(200), jsonPath("$.id").saveAs("customerODS"))
    )
}