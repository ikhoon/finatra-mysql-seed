package com.github.ikhoon.swagger

import com.github.ikhoon.app.v1.ping.Pong
import com.github.ikhoon.app.v1.user.UserDto
import com.github.ikhoon.persistence.quill.point.Points
import com.github.xiaodongw.swagger.finatra.FinatraOperation._
import com.twitter.finagle.http.Response
import io.swagger.models.Info
import io.swagger.models.Swagger
import io.swagger.models.Operation

object FinatraSwaggerDocument extends Swagger

object SwaggerDocument {
  FinatraSwaggerDocument.info(new Info()
    .description("The Finatra Seed Project, this is a sample for swagger document generation")
    .version("0.0.1-SNAPSHOT")
    .title("Finatra Seed Project API"))

  implicit protected val finatraSwagger = FinatraSwaggerDocument

  implicit lazy val PingDocument: Operation = swagger { o =>
    o.summary("Ping")
    o.tag("Default")
    o.responseWith[Pong](200, "OK!")
    o.responseWith(404, "Oops, not found!")
  }

  implicit lazy val FindUserByIdWithQuillDocument: Operation = swagger { o =>
    o.summary("By Id with Quill")
    o.tag("Find User")
    o.routeParam[Int]("Id", "User Id")
    o.responseWith[UserDto](200, "OK!")
    o.responseWith(404, "Oops not found")
  }

  implicit lazy val FindUserByIdWithSlickDocument: Operation = swagger { o =>
    o.summary("By Id with Slick")
    o.tag("Find User")
    o.routeParam[Long]("Id", "User Id")
    o.responseWith[UserDto](200, "OK!")
    o.responseWith(404, "Oops not found")
  }

  implicit lazy val GetUserPointByEmailDocument: Operation = swagger { o =>
    o.summary("By Email")
    o.tag("Get Point")
    o.routeParam[String]("Id", "User Id")
    o.responseWith[Points](200, "OK!")
    o.responseWith(404, "Oops not found")
  }
  protected def swagger(f: Operation => Unit): Operation = {
    val op = new Operation
    f(op)
    op
  }

}
