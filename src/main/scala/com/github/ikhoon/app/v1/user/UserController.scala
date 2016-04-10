package com.github.ikhoon.app.v1.user

import javax.inject.Inject

import com.github.ikhoon.swagger.SimpleSwaggerSupport
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

class UserController @Inject() (userService: UserService) extends Controller with SimpleSwaggerSupport {

  {
    import com.github.ikhoon.swagger.SwaggerDocument.FindUserByIdWithQuillDocument
    get("/users/:id/quill") { request: Request =>
      userService.findByIdWithQuill(request.getIntParam("id"))
    }
  }

  {
    import com.github.ikhoon.swagger.SwaggerDocument.FindUserByIdWithSlickDocument
    get("/users/:id/slick") { request: Request =>
      userService.findByIdWithSlick(request.getLongParam("id"))
    }
  }
}
