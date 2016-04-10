package com.github.ikhoon.test

import com.github.ikhoon.modules.{ CustomJacksonModule, TypesafeConfigModule }
import com.twitter.inject.IntegrationTest
import com.twitter.inject.app.TestInjector

class FinatraServiceTest extends IntegrationTest {
  val modules = Seq(TypesafeConfigModule, CustomJacksonModule)

  override val injector = TestInjector(modules: _*)

  "product get" should {

    "item " in {
      //      val productService = injector.instance[ProductService]

      //      val response = productService.getProductById(1)

    }
  }

}
