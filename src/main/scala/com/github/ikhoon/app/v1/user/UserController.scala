package com.github.ikhoon.app.v1.user

import javax.inject.Inject

import com.github.ikhoon.swagger.SimpleSwaggerController
import com.twitter.finagle.http.Request
import io.swagger.models.Swagger

class UserController @Inject() (
  userService:      UserService,
  userPointService: UserPointService,
  s:                Swagger
) extends SimpleSwaggerController {

  {
    import com.github.ikhoon.swagger.SwaggerDocument.FindUserByIdWithQuillDocument
    Get("/users/:id/quill") { request: Request =>
      userService.findByIdWithQuill(request.getIntParam("id"))
    }
  }

  {
    import com.github.ikhoon.swagger.SwaggerDocument.FindUserByIdWithSlickDocument
    Get("/users/:id/slick") { request: Request =>
      userService.findByIdWithSlick(request.getLongParam("id"))
    }
  }

  {
    import com.github.ikhoon.swagger.SwaggerDocument.GetUserPointByEmailDocument
    Get("/users/:email/point") { request: Request =>
      userPointService.getPointByUserEmail(request.getParam("email"))
    }
  }
}
