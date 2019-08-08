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
  var productscn = scenario("Insert Product").exec(RegisterProduct.registerProduct).pause(30)
  var productODSscn = scenario("Insert Product ODS").exec(RegisterProductODS.registerProductODS).pause(30)
  setUp(productscn.inject(atOnceUsers(1)).protocols(http),productODSscn.inject(atOnceUsers(1)).protocols(http))
}

object RegisterProduct {

  val createProductUrl = "http://localhost:8092/products"

  val productDetailsJsonFeeder = jsonFile("productdetails.json").circular

  val registerProduct = scenario("Register Product Feed").exec(
      http("Insert Product")
        .post(createProductUrl)
        .header("Content-Type", "application/json")
        .body(
          RawFileBody("productdetails.json")).asJSON
        .check(status.is(200), jsonPath("$.id").saveAs("productId"))
    )
}

object RegisterProductODS {

  val createProductODSUrl = "http://localhost:8094/odsproducts"

  val productDetailsJsonFeeder = jsonFile("productdetails.json").circular

  val registerProductODS = scenario("Register ODS Product Feed").
    .exec(
      http("Insert Product ODS")
        .post(createProductODSUrl)
        .header("Content-Type", "application/json")
        .body(
          RawFileBody("productdetails.json")
        ).asJSON
        .check(status.is(200), jsonPath("$.id").saveAs("productId"))
    )
}
