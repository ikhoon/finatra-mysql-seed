package com.github.ikhoon.persistence.quill.user

import javax.inject.{ Inject, Singleton }

import com.github.ikhoon.modules.QuillDatabaseModule.QuillDatabaseSource
import com.twitter.util.Future
import io.getquill._

@Singleton
class QuillUserRepository @Inject() (db: QuillDatabaseSource) {

  def findById(id: Int): Future[Option[Users]] = {
    val q = quote { (id: Int) =>
      query[Users].filter(i => i.id == id).take(1)
    }
    db.run(q)(id).map(_.headOption)
  }

  def findByEmail(email: String): Future[Option[Users]] = {
    val q = quote { (email: String) =>
      query[Users].filter(i => i.email == email).take(1)
    }
    db.run(q)(email).map(_.headOption)
  }
}