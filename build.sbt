import sbt.Keys._

name := """finatra-mysql-seed"""
organization := "com.github.ikhoon"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.12.2"

//fork in run := true

javaOptions ++= Seq(
  "-Dlog.service.output=/dev/stderr",
  "-Dlog.access.output=/dev/stderr")

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.jcenterRepo,
  "Twitter Maven" at "https://maven.twttr.com",
  "Finatra Repo" at "http://twitter.github.com/finatra",
//  "jitpack" at "https://jitpack.io",
  "Sonatype OSS Releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2"
)

// assembly for packaging as single jar
assemblyMergeStrategy in assembly := {
  case "BUILD" => MergeStrategy.discard
  case other => MergeStrategy.defaultMergeStrategy(other)
}

assemblyJarName in assembly := s"${name.value}.jar"


lazy val versions = new {
  val finatra = "2.10.0"
  val logback = "1.1.7"
  val guice = "4.0"
  val getquill = "1.3.0"
  val slick = "3.2.0"
  val hikaricp = "2.6.3"
  val slickJoda = "2.3.0"
  val mysqljdbc = "5.1.37"
  val jodaTime = "2.9.4"
  val jodaConvert = "1.8"
  val typesafeConfig = "1.3.0"
  val scalatest = "3.0.2"
//  val swagger = "0.6.0"
  val ficus = "1.4.0" // for scala friendly typesafe config
  val async = "0.9.6"
  val mockito = "1.10.19"
}

libraryDependencies ++= Seq(

  // finatra
  "com.twitter" %% "finatra-http" % versions.finatra,
  "com.twitter" %% "finatra-slf4j" % versions.finatra,
  "com.twitter" %% "finatra-httpclient" % versions.finatra,

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

  // scala async
  "org.scala-lang.modules" %% "scala-async" %  versions.async,

  // swagger
  "com.jakehschwartz" %% "finatra-swagger" % versions.finatra,
//  "com.github.xiaodongw" %% "swagger-finatra" % versions.swagger,

  // typesafe config
  "com.typesafe" % "config" % versions.typesafeConfig,
  "com.iheart" %% "ficus" % versions.ficus, // for scala friendly typesafe config

  // reflect
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  // others
  "ch.qos.logback" % "logback-classic" % versions.logback,

  // test
  "com.twitter" %% "finatra-http" % versions.finatra % "test",
  "com.twitter" %% "finatra-jackson" % versions.finatra % "test",
  "com.twitter" %% "inject-server" % versions.finatra % "test",
  "com.twitter" %% "inject-app" % versions.finatra % "test",
  "com.twitter" %% "inject-core" % versions.finatra % "test",
  "com.twitter" %% "inject-modules" % versions.finatra % "test",
  "com.google.inject.extensions" % "guice-testlib" % versions.guice % "test",

  "com.twitter" %% "finatra-http" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "finatra-jackson" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "inject-server" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "inject-app" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "inject-core" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "inject-modules" % versions.finatra % "test" classifier "tests",

  "org.scalactic" %% "scalactic" % versions.scalatest,
  "org.scalatest" %% "scalatest" % versions.scalatest % "test",

  "org.mockito" % "mockito-core" % versions.mockito % "test"
)


Revolver.settings
