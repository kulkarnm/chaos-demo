package com.chaosdemo.tests.gatling

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import java.util.Date
import scala.concurrent.duration.FiniteDuration
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit
import scala.util.Random
import java.math.BigInteger
import java.util.concurrent.ThreadLocalRandom

class ApiGatlingSimulationTest extends Simulation {

/*
  val scn = scenario("AddAndFindOrders").repeat(100, "n") {
        exec(
          http("AddOrder-API")
            .post("http://localhost:8090/order-service/orders")
            .header("Content-Type", "application/json")
            .body(StringBody("""{"productId":""" + 1 + ""","customerId":""" + 1 + ""","productsCount":1,"price":1000,"status":"NEW"}"""))
            .check(status.is(200),  jsonPath("$.id").saveAs("id"))
        ).pause(Duration.apply(5, TimeUnit.MILLISECONDS))    
        .
        exec(
          http("GetOrder-API")
            .get("http://localhost:8091/order-service/orders/${id}")
            .check(status.is(200))
        )   
  }
  
  setUp(scn.inject(atOnceUsers(20))).maxDuration(FiniteDuration.apply(10, "minutes"))
*/
  val rnd = ThreadLocalRandom.current().nextInt(1,9)
  val scn = scenario("ReadCustomers").repeat(6000, "n") {
    exec(
      http("ReadCustomer-API")
        .get("http://localhost:8090/customer-service/customers/" + rnd)
        .check(status.is(200))
    ).pause(Duration.apply(5, TimeUnit.MILLISECONDS))
      .exec(
          http("ReadProduct-API")
            .get("http://localhost:8090/product-service/products/" + rnd)
            .check(status.is(200))
        )
  }

  setUp(scn.inject(rampUsers(20).during(240))).maxDuration(FiniteDuration.apply(10, "minutes"))
}