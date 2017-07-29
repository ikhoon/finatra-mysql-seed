package com.github

package object ikhoon {

  import com.twitter.{ util => twitter }

  import language.implicitConversions
  import scala.concurrent.{ ExecutionContext, Future, Promise }
  import scala.util.{ Failure, Success, Try }
  import TwitterConverters._

  object TwitterFutureOps {

    implicit class ScalaToTwitterFuture[T](f: Future[T])(implicit ec: ExecutionContext) {
      def toTwitterFuture: twitter.Future[T] = scalaToTwitterFuture(f)
    }

    implicit class TwitterToScalaFuture[T](f: twitter.Future[T]) {
      def toScalaFuture: Future[T] = twitterToScalaFuture(f)
    }

    implicit class TwitterFutureFlatten[T](f: twitter.Future[twitter.Future[T]]) {
      def flatten(): twitter.Future[T] = f.flatMap(x => x)
    }

  }

  object TwitterConverters {
    implicit def scalaToTwitterTry[T](t: Try[T]): twitter.Try[T] = t match {
      case Success(r)  => twitter.Return(r)
      case Failure(ex) => twitter.Throw(ex)
    }

    implicit def twitterToScalaTry[T](t: twitter.Try[T]): Try[T] = t match {
      case twitter.Return(r) => Success(r)
      case twitter.Throw(ex) => Failure(ex)
    }

    implicit def scalaToTwitterFuture[T](f: Future[T])(implicit ec: ExecutionContext): twitter.Future[T] = {
      val promise = twitter.Promise[T]()
      f.onComplete(promise update _)
      promise
    }

    implicit def twitterToScalaFuture[T](f: twitter.Future[T]): Future[T] = {
      val promise = Promise[T]()
      f.respond(promise complete _)
      promise.future
    }
  }

}
