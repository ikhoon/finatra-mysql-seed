package com.github.ikhoon.modules

import javax.inject.Singleton

import com.google.inject.Provides
import com.twitter.inject.TwitterModule
import io.swagger.models.{ Info, Swagger }

object SwaggerModule extends TwitterModule {

  flag(name = "swagger.docs.endpoint", default = "/api-docs", help = "The key to use")

  lazy val swagger = {
    val swag = new Swagger()

    val info = new Info()
      .description("The Finatra Mysql Seed API, this is a sample for swagger document generation")
      .version("1.0.1")
      .title("Finatra Mysql Seed API")

    swag
      .info(info)
    swag
  }

  @Singleton
  @Provides
  def provideSwagger: Swagger = swagger
}