package com.github.ikhoon.swagger

import com.github.xiaodongw.swagger.finatra.SwaggerSupport
import com.twitter.finatra.http.Controller
import io.swagger.models.{ Operation, Swagger }

trait SimpleSwaggerSupport extends SwaggerSupport {
  self: Controller =>

  override protected implicit val swagger: Swagger = FinatraSwaggerDocument

  def post[RequestType: Manifest, ResponseType: Manifest](path: String)(callback: RequestType => ResponseType)(implicit doc: Operation): Unit = {
    super.post[RequestType, ResponseType](path, doc)(callback)
  }

  def get[RequestType: Manifest, ResponseType: Manifest](path: String)(callback: RequestType => ResponseType)(implicit doc: Operation): Unit = {
    super.get[RequestType, ResponseType](path, doc)(callback)
  }

  def put[RequestType: Manifest, ResponseType: Manifest](path: String)(callback: RequestType => ResponseType)(implicit doc: Operation): Unit = {
    super.put[RequestType, ResponseType](path, doc)(callback)
  }

  def patch[RequestType: Manifest, ResponseType: Manifest](path: String)(callback: RequestType => ResponseType)(implicit doc: Operation): Unit = {
    super.patch[RequestType, ResponseType](path, doc)(callback)
  }

  def delete[RequestType: Manifest, ResponseType: Manifest](path: String)(callback: RequestType => ResponseType)(implicit doc: Operation): Unit = {
    super.delete[RequestType, ResponseType](path, doc)(callback)
  }

  def head[RequestType: Manifest, ResponseType: Manifest](path: String)(callback: RequestType => ResponseType)(implicit doc: Operation): Unit = {
    super.head[RequestType, ResponseType](path, doc)(callback)
  }

  def options[RequestType: Manifest, ResponseType: Manifest](path: String)(callback: RequestType => ResponseType)(implicit doc: Operation): Unit = {
    super.options[RequestType, ResponseType](path, doc)(callback)
  }
}
