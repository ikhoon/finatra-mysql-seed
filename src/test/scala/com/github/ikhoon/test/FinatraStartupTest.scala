package com.github.ikhoon.test

import com.github.ikhoon.FinatraServer
import com.google.inject.Stage
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.inject.Test

class FinatraStartupTest extends Test {

  val server = new EmbeddedHttpServer(
    stage = Stage.PRODUCTION,
    twitterServer = new FinatraServer
  )

  test("server") {
    server.assertHealthy()
  }
}
