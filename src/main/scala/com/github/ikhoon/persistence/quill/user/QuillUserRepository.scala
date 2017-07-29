package com.github.ikhoon.persistence.quill.user

import javax.inject.{ Inject, Singleton }

import com.github.ikhoon.core.quill.QuillExtensions
import com.github.ikhoon.modules.QuillDatabaseModule.QuillDatabaseSource
import com.twitter.util.Future
import io.getquill._

@Singleton
class QuillUserRepository @Inject() (val ctx: QuillDatabaseSource) extends QuillExtensions {

  import ctx._

  def findById(id: Int): Future[Option[Users]] = {
    val q = quote { (id: Int) =>
      query[Users].filter(i => i.id == id).take(1)
    }
    ctx.run(q(lift(id))).map(_.headOption)
  }

  def findByEmail(email: String): Future[Option[Users]] = {
    val q = quote { (email: String) =>
      query[Users].filter(i => i.email == email).take(1)
    }
    ctx.run(q(lift(email))).map(_.headOption)
  }
}