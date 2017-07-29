package com.github.ikhoon.app.v1.fake

import javax.inject.Inject

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

/**
 * Created by ikhoon on 2016. 4. 11..
 */
class FakeController @Inject() (fakeService: FakeService) extends Controller {

  get("/sleep/:id/async") { request: Request =>
    fakeService.withSleepAsync(request.getIntParam("id"))
  }

  get("/sleep/:id/sync") { request: Request =>
    fakeService.withSleepSync(request.getIntParam("id"))
  }
}
