package com.github.ikhoon.modules

import javax.inject.Singleton

import com.google.inject.Provides
import com.twitter.inject.{ Logging, TwitterModule }
import com.typesafe.config.{ Config, ConfigFactory }

object TypesafeConfigModule extends TwitterModule with Logging {

  val mode = flag("mode", "dev", "application run mode [dev:default, alpha, sandbox, beta, real]")

  val configPath = "conf/"

  private lazy val config = {
    logger info s"LOADING CONFIG FROM: ${mode()}"
    ConfigFactory load (configPath + mode())
  }

  @Provides @Singleton
  def provideConfig(): Config = config
}
