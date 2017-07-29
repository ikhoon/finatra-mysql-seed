package com.github.ikhoon.swagger

import com.github.ikhoon.modules.SwaggerModule
import com.jakehschwartz.finatra.swagger.SwaggerController
import com.twitter.finagle.http.RouteIndex
import io.swagger.models.{ Operation, Swagger }

trait SimpleSwaggerController extends SwaggerController {

  implicit lazy val swagger: Swagger = SwaggerModule.swagger

  def Post[RequestType: Manifest, ResponseType: Manifest](
    route:      String,
    name:       String             = "",
    admin:      Boolean            = false,
    routeIndex: Option[RouteIndex] = None
  )(callback: RequestType => ResponseType)(implicit doc: SwaggerOperation): Unit =
    super.postWithDoc[RequestType, ResponseType](route, name, admin, routeIndex)(doc.op)(callback)

  def Get[RequestType: Manifest, ResponseType: Manifest](
    route:      String,
    name:       String             = "",
    admin:      Boolean            = false,
    routeIndex: Option[RouteIndex] = None
  )(callback: RequestType => ResponseType)(implicit doc: SwaggerOperation): Unit =
    super.getWithDoc[RequestType, ResponseType](route, name, admin, routeIndex)(doc.op)(callback)

  def Put[RequestType: Manifest, ResponseType: Manifest](
    route:      String,
    name:       String             = "",
    admin:      Boolean            = false,
    routeIndex: Option[RouteIndex] = None
  )(callback: RequestType => ResponseType)(implicit doc: SwaggerOperation): Unit =
    super.putWithDoc[RequestType, ResponseType](route, name, admin, routeIndex)(doc.op)(callback)

  def Patch[RequestType: Manifest, ResponseType: Manifest](
    route:      String,
    name:       String             = "",
    admin:      Boolean            = false,
    routeIndex: Option[RouteIndex] = None
  )(callback: RequestType => ResponseType)(implicit doc: SwaggerOperation): Unit =
    super.patchWithDoc[RequestType, ResponseType](route, name, admin, routeIndex)(doc.op)(callback)

  def Delete[RequestType: Manifest, ResponseType: Manifest](
    route:      String,
    name:       String             = "",
    admin:      Boolean            = false,
    routeIndex: Option[RouteIndex] = None
  )(callback: RequestType => ResponseType)(implicit doc: SwaggerOperation): Unit =
    super.deleteWithDoc[RequestType, ResponseType](route, name, admin, routeIndex)(doc.op)(callback)

  def Head[RequestType: Manifest, ResponseType: Manifest](
    route:      String,
    name:       String             = "",
    admin:      Boolean            = false,
    routeIndex: Option[RouteIndex] = None
  )(callback: RequestType => ResponseType)(implicit doc: SwaggerOperation): Unit =
    super.headWithDoc[RequestType, ResponseType](route, name, admin, routeIndex)(doc.op)(callback)

  def Options[RequestType: Manifest, ResponseType: Manifest](
    route:      String,
    name:       String             = "",
    admin:      Boolean            = false,
    routeIndex: Option[RouteIndex] = None
  )(callback: RequestType => ResponseType)(implicit doc: SwaggerOperation): Unit =
    super.optionsWithDoc[RequestType, ResponseType](route, name, admin, routeIndex)(doc.op)(callback)

}

