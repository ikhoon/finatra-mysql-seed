package com.github.ikhoon.swagger

import com.github.ikhoon.app.v1.ping.Pong
import com.github.ikhoon.app.v1.user.UserDto
import com.github.ikhoon.modules.SwaggerModule
import com.github.ikhoon.persistence.quill.point.Points
import com.jakehschwartz.finatra.swagger.FinatraOperation._
import io.swagger.models.Swagger
import io.swagger.models.Operation

case class SwaggerOperation(op: Operation => Operation)

trait SwaggerDocument {

  implicit val swagger: Swagger

  implicit lazy val PingDocument: SwaggerOperation = SwaggerOperation { o =>
    o.summary("Ping")
    o.tag("Default")
    o.responseWith[Pong](200, "OK!")
    o.responseWith(404, "Oops, not found!")
  }

  implicit lazy val FindUserByIdWithQuillDocument: SwaggerOperation = SwaggerOperation { o =>
    o.summary("By Id with Quill")
    o.tag("Find User")
    o.routeParam[Int]("id", "User Id")
    o.responseWith[UserDto](200, "OK!")
    o.responseWith(404, "Oops not found")
  }

  implicit lazy val FindUserByIdWithSlickDocument: SwaggerOperation = SwaggerOperation { o =>
    o.summary("By Id with Slick")
    o.tag("Find User")
    o.routeParam[Long]("id", "User Id")
    o.responseWith[UserDto](200, "OK!")
    o.responseWith(404, "Oops not found")
  }

  implicit lazy val GetUserPointByEmailDocument: SwaggerOperation = SwaggerOperation { o =>
    o.summary("By Email")
    o.tag("Get Point")
    o.routeParam[String]("id", "User Id")
    o.responseWith[Points](200, "OK!")
    o.responseWith(404, "Oops not found")
  }

}

object SwaggerDocument extends SwaggerDocument {
  implicit val swagger: Swagger = SwaggerModule.swagger
}
