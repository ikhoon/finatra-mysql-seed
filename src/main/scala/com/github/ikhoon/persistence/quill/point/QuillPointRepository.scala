package com.github.ikhoon.persistence.quill.point

import javax.inject.{ Inject, Singleton }

import com.github.ikhoon.modules.QuillDatabaseModule._
import com.twitter.util.Future
import io.getquill._

@Singleton
class QuillPointRepository @Inject() (db: QuillDatabaseSource) {

  def findByUserId(userId: Int): Future[Option[Points]] = {
    val q = quote { (userId: Int) =>
      query[Points].filter(p => p.userId == userId).take(1)
    }
    db.run(q)(userId).map(_.headOption)
  }

}
