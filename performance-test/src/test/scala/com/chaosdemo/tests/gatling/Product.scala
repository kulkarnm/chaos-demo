package com.chaosdemo.tests.gatling

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
  setUp(productscn.inject(atOnceUsers(1)).protocols(http))
}

object RegisterProduct {

  val createProductUrl = "http://localhost:8092/products/all"

  val productDetailsJsonFeeder = jsonFile("productdetails.json")

  val registerProduct = feed(productDetailsJsonFeeder)
    .exec(
      http("Insert Product")
        .post(createProductUrl)
        .header("Content-Type", "application/json")
        .body(ElFileBody("productdetails.json"))
        .asJSON
        .check(status.is(200), jsonPath("$.id").saveAs("productId"))
    )
}

/*
object RegisterProductODS {

  val createProductODSUrl = "http://localhost:8094/products"

  val productDetailsJsonFeeder = jsonFile("productdetails.json").circular

  val registerProductODS = feed(productDetailsJsonFeeder)
    .exec(
      http("Insert Product ODS")
        .post(createProductODSUrl)
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
*/
