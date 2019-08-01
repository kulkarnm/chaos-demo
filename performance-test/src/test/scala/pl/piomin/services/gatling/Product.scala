package pl.piomin.services.gatling

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import java.util.Date
import scala.concurrent.duration.FiniteDuration
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit
import scala.util.Random

class Product extends Simulation {
  var scn = scenario("Insert Product").exec(RegisterProduct.registerProduct).pause(10)
  setUp(scn.inject(atOnceUsers(1)).protocols(http))
}

object RegisterProduct {

  val createProductUrl = "http://localhost:8092/products"


  val productDetailsJsonFeeder = jsonFile("productdetails.json").circular

  val registerProduct = feed(productDetailsJsonFeeder)
    .exec(
      http("Insert Product")
        .post(createProductUrl)
        .header("Content-Type", "application/json")
        .body(
          StringBody(
            """
              |{
              |    "id":"${id}",
              |    "name":"${name}",
              |    "count":"${count}",
              |    "price":"${price}",
              |    "category":"${category}"
              |}
            """.stripMargin
          )
        ).asJSON
        .check(status.is(200), jsonPath("$.id").saveAs("productId"))
    )
}