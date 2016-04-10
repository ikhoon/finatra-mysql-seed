package com.github.ikhoon.modules

import javax.inject.{ Named, Singleton }

import com.google.inject.Provides
import com.twitter.finatra.httpclient.HttpClient
import com.twitter.finatra.httpclient.modules.HttpClientModule
import com.twitter.finatra.json.FinatraObjectMapper
import com.twitter.inject.TwitterModule
import com.typesafe.config.Config
import net.ceedubs.ficus.Ficus._

object HttpClientModules {
  val modules = Seq(FakeHttpClientModule())
}

/**
 * Finatra Http Client - BasicHttpClientModule Usage :
 * {{{
 * import com.twitter.finatra.json.FinatraObjectMapper
 * import com.typesafe.config.Config
 * import net.ceedubs.ficus.Ficus._
 * object QueryHttpClientModule {
 *   def apply() = new BasicHttpClientModule() {
 *     @Named("query") @Singleton @Provides
 *     def provideHttpClient(mapper: FinatraObjectMapper, config: Config) =
 *       super.provideHttpClient(mapper, config.as[String]("query.host"), config.as[Int]("query.port"))
 *   }
 * }
 * }}}
 */
abstract class BasicHttpClientModule() extends TwitterModule {

  protected def provideHttpClient(mapper: FinatraObjectMapper, host: String, port: Int = 80): HttpClient = {
    val httpClientModule = new HttpClientModule {
      override def dest: String = s"$host:$port"
      override def defaultHeaders: Map[String, String] = Map("Host" -> host)
    }
    httpClientModule.provideHttpClient(mapper, httpClientModule.provideHttpService)
  }
}

object FakeHttpClientModule {
  def apply() = new BasicHttpClientModule {
    @Named("fake") @Provides @Singleton
    def provideHttpClient(mapper: FinatraObjectMapper, config: Config) =
      super.provideHttpClient(mapper, config.as[String]("fake.host"), config.as[Int]("fake.port"))
  }
}
