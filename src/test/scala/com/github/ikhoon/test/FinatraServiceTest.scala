package com.github.ikhoon.test

import com.github.ikhoon.app.v1.fake.FakeService
import com.github.ikhoon.modules.{ CustomJacksonModule, HttpClientModules, TypesafeConfigModule }
import com.twitter.inject.IntegrationTest
import com.twitter.inject.app.TestInjector
import com.twitter.util.Await

class FinatraServiceTest extends IntegrationTest {
  val modules = Seq(TypesafeConfigModule, CustomJacksonModule) ++ HttpClientModules.modules

  override val injector = TestInjector(modules: _*).create

  test("fake servic get") {

    val faceService = injector.instance[FakeService]
    val future = faceService.withSleepAsync(1)
    val response = Await.result(future)
    assert(response != null)

  }

}
