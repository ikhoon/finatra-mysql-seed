package com.github.ikhoon.app.v1.fake

import javax.inject.Inject

import com.github.ikhoon.swagger.SimpleSwaggerSupport
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

/**
 * Created by ikhoon on 2016. 4. 11..
 */
class FakeController @Inject() (fakeService: FakeService) extends Controller {

  get("/sleep/:id") { request: Request =>
    fakeService.withSleep(request.getIntParam("id"))
  }
}
