package com.github.ikhoon.core

import com.twitter.util.Future

import scala.concurrent.ExecutionContext

case class FutureOption[+A](future: Future[Option[A]]) extends AnyVal {
  def flatMap[B](f: A => FutureOption[B])(implicit ec: ExecutionContext): FutureOption[B] =
    FutureOption(future.flatMap {
      case Some(a) => f(a).future
      case None    => Future.value(None)
    })

  def map[B](f: A => B)(implicit ec: ExecutionContext): FutureOption[B] = FutureOption(future.map(_.map(f)))

  def filter(p: A ⇒ Boolean)(implicit ec: ExecutionContext): FutureOption[A] = withFilter(p)(ec)

  def withFilter(p: A ⇒ Boolean)(implicit ec: ExecutionContext): FutureOption[A] =
    FutureOption(future.map {
      case Some(a) => if (p(a)) Some(a) else None
      case _       => None
    })
}

case class FutureSeq[+A](future: Future[Seq[A]]) extends AnyVal {
  def flatMap[B](f: A => FutureSeq[B])(implicit ec: ExecutionContext): FutureSeq[B] =
    FutureSeq(future.flatMap {
      case a => Future.collect(a.map(f andThen (_.future))).map(_.flatten)
    })

  def map[B](f: A => B)(implicit ec: ExecutionContext): FutureSeq[B] = FutureSeq(future.map(_.map(f)))

  def filter(p: A ⇒ Boolean)(implicit ec: ExecutionContext): FutureSeq[A] = withFilter(p)(ec)

  def withFilter(p: A ⇒ Boolean)(implicit ec: ExecutionContext): FutureSeq[A] =
    FutureSeq(future.map(_.filter(p)))
}