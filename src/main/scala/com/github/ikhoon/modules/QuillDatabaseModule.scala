package com.github.ikhoon.modules

import com.google.inject.{ Singleton, Provides }
import com.twitter.inject.TwitterModule
import com.typesafe.config.Config
import io.getquill.FinagleMysqlSourceConfig
import io.getquill.naming.SnakeCase
import io.getquill._
import io.getquill.sources.finagle.mysql.FinagleMysqlSource

/**
 * Created by ikhoon on 2016. 4. 10..
 */
object QuillDatabaseModule extends TwitterModule {

  type QuillDatabaseSource = FinagleMysqlSource[SnakeCase]

  @Provides @Singleton
  def provideDataBaseSource(conf: Config): QuillDatabaseSource = source(new FinagleMysqlSourceConfig[SnakeCase]("") {
    override def config = conf.getConfig("quill.db")
  })

}
