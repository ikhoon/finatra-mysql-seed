package com.github.ikhoon.persistence.slick.user

import org.joda.time.DateTime

case class Users(
  id:        Long,
  name:      String,
  createdAt: DateTime
)
