package com.github.ikhoon.test

import com.github.ikhoon.FinatraServer
import com.google.inject.Stage
import com.twitter.finatra.http.test.EmbeddedHttpServer
import com.twitter.inject.Test

class FinatraStartupTest extends Test {

  val server = new EmbeddedHttpServer(
    stage = Stage.PRODUCTION,
    twitterServer = new FinatraServer
  )

  "server" in {
    server.assertHealthy()
  }
}
