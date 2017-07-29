package com.github.ikhoon.modules

import com.google.inject.{ Provides, Singleton }
import com.twitter.inject.TwitterModule
import com.typesafe.config.Config
import io.getquill.{ FinagleMysqlContext, SnakeCase }

object QuillDatabaseModule extends TwitterModule {

  type QuillDatabaseSource = FinagleMysqlContext[SnakeCase]

  @Provides @Singleton
  def provideDataBaseSource(conf: Config): QuillDatabaseSource =
    new FinagleMysqlContext[SnakeCase](conf.getConfig("quill.db"))

}
