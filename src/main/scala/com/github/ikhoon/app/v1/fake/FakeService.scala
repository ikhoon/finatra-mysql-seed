package com.github.ikhoon.app.v1.fake

import javax.inject.{ Inject, Named }

import com.fasterxml.jackson.databind.JsonNode
import com.twitter.finatra.httpclient.{ HttpClient, RequestBuilder }
import com.twitter.util.{ Await, Duration, Future }
import com.typesafe.config.Config
import net.ceedubs.ficus.Ficus._

class FakeService @Inject() (@Named("fake") httpClient: HttpClient, config: Config) {
  def withSleepAsync(sec: Int): Future[JsonNode] = {
    val url = config.as[String]("fake.host") + s"?sleep=$sec"
    httpClient.executeJson[JsonNode](RequestBuilder.get(url))
  }

  def withSleepSync(sec: Int): JsonNode = {
    val url = config.as[String]("fake.host") + s"?sleep=$sec"
    val jsonNode = httpClient.executeJson[JsonNode](RequestBuilder.get(url))
    Await.result(jsonNode, Duration.fromSeconds(100))
  }
}
