package com.github.ikhoon.modules

/**
 * Created by ikhoon on 2016. 4. 10..
 */
import javax.inject.Singleton

import com.google.inject.Provides
import com.twitter.inject.TwitterModule
import com.typesafe.config.Config
/**
 * Created by ikhoon on 2016. 4. 9..
 */

object SlickDatabaseModule extends TwitterModule {
  import slick.driver.MySQLDriver.api._
  type SlickDataBaseSource = slick.driver.MySQLDriver.api.Database

  @Singleton @Provides
  def provideDatabase(config: Config): SlickDataBaseSource = Database.forConfig("slick.db", config)

}
