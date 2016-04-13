package com.github.ikhoon.modules

import javax.inject.Singleton

import com.google.inject.Provides
import com.twitter.inject.TwitterModule
import com.typesafe.config.Config

object SlickDatabaseModule extends TwitterModule {
  import slick.driver.MySQLDriver.api._
  type SlickDataBaseSource = slick.driver.MySQLDriver.api.Database

  @Singleton @Provides
  def provideDatabase(config: Config): SlickDataBaseSource = Database.forConfig("slick.db", config)

}
