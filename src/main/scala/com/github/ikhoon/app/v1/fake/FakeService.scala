package com.github.ikhoon.app.v1.fake

import javax.inject.{ Inject, Named }

import com.fasterxml.jackson.databind.JsonNode
import com.twitter.finatra.httpclient.{ HttpClient, RequestBuilder }
import com.twitter.util.{ Await, Duration, Future }
import com.typesafe.config.Config

class FakeService @Inject() (@Named("fake") httpClient: HttpClient, config: Config) {
  def withSleepAsync(sec: Int): Future[JsonNode] = {
    val url = s"/api/?sleep=$sec"
    httpClient.executeJson[JsonNode](RequestBuilder.get(url))
  }

  def withSleepSync(sec: Int): JsonNode = {
    val url = s"/api/?sleep=$sec"
    val jsonNode = httpClient.executeJson[JsonNode](RequestBuilder.get(url))
    Await.result(jsonNode, Duration.fromSeconds(100))
  }
}
