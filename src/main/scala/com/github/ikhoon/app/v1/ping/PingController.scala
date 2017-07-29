package com.github.ikhoon.app.v1.ping

import javax.inject.Inject

import com.github.ikhoon.swagger.SimpleSwaggerController
import com.google.inject.Singleton
import com.twitter.finagle.http.Request
import com.twitter.util.Future
import com.typesafe.config.Config

case class Pong(pong: String)

@Singleton
class PingController @Inject() (config: Config) extends SimpleSwaggerController {

  {
    get("/ping") { request: Request =>
      info("ping")
      Future.value(Pong("pong"))
    }
  }
}
