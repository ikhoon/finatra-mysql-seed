package com.github.ikhoon.app.v1.fake

import javax.inject.{ Inject, Named }

import com.fasterxml.jackson.databind.JsonNode
import com.twitter.finatra.httpclient.{ HttpClient, RequestBuilder }
import com.twitter.util.Future
import com.typesafe.config.Config
import net.ceedubs.ficus.Ficus._

class FakeService @Inject() (@Named("fake") httpClient: HttpClient, config: Config) {
  def withSleep(sec: Int): Future[JsonNode] = {
    val url = config.as[String]("fake.host") + s"?sleep=$sec"
    httpClient.executeJson[JsonNode](RequestBuilder.get(url))
  }
}
