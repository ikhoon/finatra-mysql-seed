package com.github.ikhoon

import com.github.ikhoon.app.v1.fake.FakeController
import com.github.ikhoon.app.v1.ping.PingController
import com.github.ikhoon.app.v1.user.UserController
import com.github.ikhoon.modules._
import com.jakehschwartz.finatra.swagger.DocsController
import com.twitter.finagle.http.{ Request, Response }
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.{ CommonFilters, LoggingMDCFilter, TraceIdMDCFilter }
import com.twitter.finatra.http.routing.HttpRouter

object FinatraServerMain extends FinatraServer

class FinatraServer extends HttpServer {

  override def modules = Seq(
    TypesafeConfigModule, QuillDatabaseModule, SlickDatabaseModule, SwaggerModule
  ) ++ HttpClientModules.modules

  override def jacksonModule = CustomJacksonModule

  override def defaultFinatraHttpPort = ":9999"

  override def configureHttp(router: HttpRouter) {
    router
      .filter[LoggingMDCFilter[Request, Response]]
      .filter[TraceIdMDCFilter[Request, Response]]
      .filter[CommonFilters]
      .add[DocsController]
      .add[PingController]
      .add[UserController]
      .add[FakeController]

  }

}
