package com.github.ikhoon.core.quill

import java.util.Date

import com.github.ikhoon.modules.QuillDatabaseModule.QuillDatabaseSource

trait QuillExtensions {

  val ctx: QuillDatabaseSource
  import ctx._

  implicit class RichDate(left_l: Date) {
    def >=(right_r: Date) = quote(infix"$left_l >= $right_r".as[Boolean])
    def <=(right_r: Date) = quote(infix"$left_l <= $right_r".as[Boolean])
    def <(right_r: Date) = quote(infix"$left_l < $right_r".as[Boolean])
    def >(right_r: Date) = quote(infix"$left_l > $right_r".as[Boolean])
  }

  implicit class Pageable[T](q: Query[T]) {
    def slice(limit_l: Int, offset_o: Int) = quote(infix"$q LIMIT $limit_l OFFSET $offset_o".as[Query[T]])
  }

  implicit class Like(a: Option[String]) {
    def like(b: Option[String]) = quote(infix"$a like $b".as[Boolean])
  }

}
