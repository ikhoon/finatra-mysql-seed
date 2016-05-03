package com.github.ikhoon.app.v1.user

import javax.inject.Inject

import com.github.ikhoon.core.FutureOption
import com.github.ikhoon.persistence.quill.point.{ Points, QuillPointRepository }
import com.github.ikhoon.persistence.quill.user.{ QuillUserRepository, Users }
import com.twitter.util.Future
import scala.concurrent.ExecutionContext.Implicits.global

class UserPointService @Inject() (
  quillUserRepository:  QuillUserRepository,
  quillPointRepository: QuillPointRepository
) {
  def getPointByUserEmail(email: String): Future[Option[Points]] =
    for {
      userOption: Option[Users] <- quillUserRepository.findByEmail(email)
      pointOption: Option[Points] <- userOption match {
        case Some(user) => quillPointRepository.findByUserId(user.id)
        case _          => Future.value(None)
      }
    } yield pointOption

  def getPointByUserEmailMonadTransformer(email: String): Future[Option[Points]] =
    (for {
      user <- FutureOption(quillUserRepository.findByEmail(email))
      point <- FutureOption(quillPointRepository.findByUserId(user.id))
    } yield point).future
}
