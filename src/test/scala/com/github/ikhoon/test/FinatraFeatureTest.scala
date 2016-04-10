package com.github.ikhoon.test

import com.github.ikhoon.FinatraServer
import com.twitter.finagle.http.Status.Ok
import com.twitter.finatra.http.test.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest

class FinatraFeatureTest extends FeatureTest {

  override val server = new EmbeddedHttpServer(new FinatraServer)

  "Server" should {
    "ping" in {
      server.httpGet(
        path = "/ping",
        andExpect = Ok,
        withBody = "pong"
      )
    }
  }
}
