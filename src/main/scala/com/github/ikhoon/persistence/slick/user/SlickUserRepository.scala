package com.github.ikhoon.persistence.slick.user

import javax.inject.Inject

import com.github.ikhoon.modules.SlickDatabaseModule.SlickDatabaseSource
import com.github.tototoshi.slick.MySQLJodaSupport._
import org.joda.time.DateTime

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class SlickUserRepository @Inject() (db: SlickDatabaseSource) {

  import driver.api._
  private class UserTable(tag: Tag) extends Table[Users](tag, "users") {
    def id = column[Long]("id", O.PrimaryKey)
    def name = column[String]("name")
    def createdAt = column[DateTime]("created_at")
    def * = (id, name, createdAt) <> ((Users.apply _).tupled, Users.unapply)
  }

  private val users = TableQuery[UserTable]

  def findById(id: Long): Future[Option[Users]] = {
    db.run {
      users.filter(_.id === id).take(1).result
    }.map(_.headOption)
  }
}

