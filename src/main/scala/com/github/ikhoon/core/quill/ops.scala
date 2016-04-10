package com.github.ikhoon.core.quill

object ops {

  import java.util.Date

  import io.getquill._
  implicit class RichDate(left_l: Date) {
    def >=(right_r: Date) = quote(infix"$left_l >= $right_r".as[Boolean])
    def <=(right_r: Date) = quote(infix"$left_l <= $right_r".as[Boolean])
    def <(right_r: Date) = quote(infix"$left_l < $right_r".as[Boolean])
    def >(right_r: Date) = quote(infix"$left_l > $right_r".as[Boolean])
  }

  implicit class Pageable[Q <: Query[_]](q: Q) {
    def slice(limit_l: Int, offset_o: Int) = quote(infix"$q LIMIT $limit_l OFFSET $offset_o".as[Q])
  }

}
