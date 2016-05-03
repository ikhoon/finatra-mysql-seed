import sbt.Keys._

name := """finatra-mysql-seed"""
organization := "com.github.ikhoon"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.11.8"

//fork in run := true

javaOptions ++= Seq(
  "-Dlog.service.output=/dev/stderr",
  "-Dlog.access.output=/dev/stderr")

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.jcenterRepo,
  "Twitter Maven" at "https://maven.twttr.com",
  "Finatra Repo" at "http://twitter.github.com/finatra",
  "jitpack" at "https://jitpack.io",
  "Sonatype OSS Releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2"
)

// assembly for packaging as single jar
assemblyMergeStrategy in assembly := {
  case "BUILD" => MergeStrategy.discard
  case other => MergeStrategy.defaultMergeStrategy(other)
}

assemblyJarName in assembly := s"${name.value}.jar"


lazy val versions = new {
  val finatra = "2.1.4"
  val logback = "1.1.6"
  val guice = "4.0"
  val getquill = "0.5.0"
  val slick = "3.1.1"
  val hikaricp = "2.4.5"
  val slickJoda = "2.1.0"
  val mysqljdbc = "5.1.37"
  val jodaTime = "2.9.3"
  val jodaConvert = "1.8"
  val twitterUtil = "6.33.0"
  var twitterAsync = "516e77a"
  val typesafeConfig = "1.3.0"
  val mockito = "1.9.5"
  val scalatest = "2.2.6"
  val specs2 = "2.3.12"
  val swagger = "0.5.0"
  val ficus = "1.2.3" // for scala friendly typesafe config
  val async = "0.9.5"
}

libraryDependencies ++= Seq(

  // finatra
  "com.twitter.finatra" %% "finatra-http" % versions.finatra,
  "com.twitter.finatra" %% "finatra-slf4j" % versions.finatra,
  "com.twitter.finatra" %% "finatra-httpclient" % versions.finatra,

  // quill
  "io.getquill" %% "quill-finagle-mysql" % versions.getquill,

  // slick
  "com.typesafe.slick" %% "slick" % versions.slick,
  "com.typesafe.slick" %% "slick-hikaricp" % versions.slick excludeAll ExclusionRule(organization = "com.zaxxer"),
  "com.github.tototoshi" %% "slick-joda-mapper" % versions.slickJoda,
  "com.zaxxer" % "HikariCP" % versions.hikaricp,
  "mysql" % "mysql-connector-java" % versions.mysqljdbc,
  "joda-time" % "joda-time" % versions.jodaTime,
  "org.joda" % "joda-convert" % versions.jodaConvert,

  // twitter async
  "com.github.foursquare" % "twitter-util-async" % versions.twitterAsync,

  // scala async
  "org.scala-lang.modules" %% "scala-async" %  versions.async,

  // swagger
  "com.github.xiaodongw" %% "swagger-finatra2" % versions.swagger,

  // typesafe config
  "com.typesafe" % "config" % versions.typesafeConfig,
  "com.iheart" %% "ficus" % versions.ficus, // for scala friendly typesafe config

  // reflect
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  // others
  "ch.qos.logback" % "logback-classic" % versions.logback,

  // test
  "com.twitter.finatra" %% "finatra-http" % versions.finatra % "test",
  "com.twitter.finatra" %% "finatra-jackson" % versions.finatra % "test",
  "com.twitter.inject" %% "inject-server" % versions.finatra % "test",
  "com.twitter.inject" %% "inject-app" % versions.finatra % "test",
  "com.twitter.inject" %% "inject-core" % versions.finatra % "test",
  "com.twitter.inject" %% "inject-modules" % versions.finatra % "test",
  "com.google.inject.extensions" % "guice-testlib" % versions.guice % "test",

  "com.twitter.finatra" %% "finatra-http" % versions.finatra % "test" classifier "tests",
  "com.twitter.finatra" %% "finatra-jackson" % versions.finatra % "test" classifier "tests",
  "com.twitter.inject" %% "inject-server" % versions.finatra % "test" classifier "tests",
  "com.twitter.inject" %% "inject-app" % versions.finatra % "test" classifier "tests",
  "com.twitter.inject" %% "inject-core" % versions.finatra % "test" classifier "tests",
  "com.twitter.inject" %% "inject-modules" % versions.finatra % "test" classifier "tests",

  "org.mockito" % "mockito-core" % versions.mockito % "test",
  "org.scalatest" %% "scalatest" % versions.scalatest % "test",
  "org.specs2" %% "specs2" % versions.specs2 % "test"
)


Revolver.settings
