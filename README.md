# Finatra Seed Project
[Finatra](https://twitter.github.io/finatra/) +
[TypesafeConfig](https://github.com/typesafehub/config) +
[Swagger](https://github.com/xiaodongw/swagger-finatra) +
[Slick](http://slick.typesafe.com/) +
[Quill](http://getquill.io/) with
[Mysql](https://www.mysql.com/) seed Project

## Development Environments
### Requirements
* [Scala 2.11.x](http://www.scala-lang.org/)
* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - [Typesafe Config(1.3.0) only supports Java 8](https://github.com/typesafehub/config#binary-releases)
* [Mysql](https://www.mysql.com/)

### Dependencies
* [Finatra](https://twitter.github.io/finatra/) - Fast, testable, Scala services built on [TwitterServer](http://twitter.github.io/twitter-server/) and [Finagle](https://twitter.github.io/finagle).
* [Guice](https://github.com/google/guice/wiki/Motivation) - a lightweight dependency injection framework for Java 6 and above by Google.
* [Slick](http://slick.typesafe.com/) - Functional Relational Mapping for Scala.
* [HikariCP](http://brettwooldridge.github.io/HikariCP/) - A high-performance JDBC connection pool.
* [Quill](http://getquill.io/) - Alternative to **Slick**, [finagle-mysql](http://twitter.github.io/finagle/guide/Protocols.html#mysql)
* [ScalaTest](http://www.scalatest.org/) - A testing tool for Scala and Java developers.
* [TypesafeConfig](https://github.com/typesafehub/config) - Configuration library for JVM languages.
* [Logback](http://logback.qos.ch/) - The Generic, Reliable, Fast & Flexible Logging Framework.
* [Swagger](https://github.com/xiaodongw/swagger-finatra) - Add Swagger support for Finatra web framework.

## Development Resource Links
* [Scala School](http://twitter.github.io/scala_school/index.html)
* [Twitter Future](https://github.com/twitter/util#futures)
* [Finatra User Guide](http://twitter.github.io/finatra/user-guide/)
* [Finatra Presentations](http://twitter.github.io/finatra/presentations/)
* [Finatra Examples Projects](https://github.com/twitter/finatra/tree/master/examples)
* [Guice Wiki](https://github.com/google/guice/wiki/Motivation)


## Getting Started

### Install MySQL on Mac OSX using Homebrew
```bash
brew install mysql
# start mysql-server
mysqld
```

### Start Server with activator

```bash
# clone repostitory
git clone git@github.com:ikhoon/finatra-quill-seed.git

cd finatra-quill-seed/

# initialize mysql table & data
mysql -u root < sql/1.sql

# Start finatra server
./activator run
...
[info] Loading project definition from finatra-quill-seed/project
[info] Set current project to finatra-quill-seed(in build file:finatra-quill-seed/)
[info] Running com.github.ikhoon.FinatraServerMain
...
```

### Auto Restart FinatraServer using [spray/sbt-resolver](https://github.com/spray/sbt-revolver)
```bash
./activator "~re-start"
...
[info] Loading project definition from finatra-quill-seed/project
[info] Set current project to finatra-quill-seed(in build file:finatra-quill-seed/)
...
```

### Create a fat JAR with compact all of its dependencies
```bash
# build single jar
./activator assembly
...
[info] Assembly up to date: finatra-quill-seed/target/scala-2.11/finatra-quill-seed-0.0.1-SNAPSHOT.jar
[success] Total time: 10 s, completed Dec 3, 2015 5:08:01 PM
```

### Start Standalone Server
```bash
cd target/scala-2.11/
# Development mode with default logback.xml
java -jar somc-1.0.0-SNAPSHOT.jar -mode dev
# Production mode with specified logback config file
java -jar -Dlogback.configurationFile=conf/real-logback.xml somc-1.0.0-SNAPSHOT.jar -mode real
```

### Run modes
#### dev(default)
Run with [`resources/conf/dev.conf`](./src/main/resources/conf/dev.conf) & [`resources/logback.xml`](./src/main/resources/logback.xml)

#### Custom run mode needs two files
* typesafe config : src/main/resources/conf/xxx.conf
* logback.xml : src/main/resources/conf/xxx-logback.xml
* Run `java -jar -Dlogback.configurationFile=conf/xxx-logback.xml finatra-quill-seed-1.0.0-SNAPSHOT.jar -mode xxx`

### Show [Twitter Server flags](http://twitter.github.io/finatra/user-guide/getting-started/#flags)
```bash
java -jar finatra-seed-project-1.0.0-SNAPSHOT.jar -help
...
flags:
  -help='false': Show this help
  -http.announce='java.lang.String': Address to announce HTTP server to
  -http.name='http': Http server name
  -http.port=':9999': External HTTP server port
  -https.announce='java.lang.String': Address to announce HTTPS server to
  -https.name='https': Https server name
  -https.port='': HTTPs Port
  -key.path='': path to SSL key
  -local.doc.root='': File serving directory for local development
  -log.append='true': If true, appends to existing logfile. Otherwise, file is truncated.
  -log.async='true': Log asynchronously
  -log.async.maxsize='4096': Max queue size for async logging
  -log.level='INFO': Log level
  -log.output='/dev/stderr': Output file
  -maxRequestSize='5242880.bytes': HTTP(s) Max Request Size
# set run mode with `-mode`
  -mode='dev': application run mode [dev:default, alpha, sandbox, beta, real]
  -mustache.templates.dir='templates': templates resource directory
  -shutdown.time='1.minutes': Maximum amount of time to wait for pending requests to complete on shutdown
  -tracingEnabled='true': Tracing enabled
```


## Example Codes

### Add Sample Controller

```scala
// add new file SampleController.scala
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

class SampleController extends Controller {

  // GET register /ping uri
  get("/ping") { request: Request =>
    // Define Response
    "pong"
  }
}
```

### Register Controller and Routing

Edit [FinatraServer.scala](./src/main/scala/com/github/ikhoon/FinatraServer.scala)
```scala
object ServerMain extends SearchServer

class SearchServer extends HttpServer {

  override def configureHttp(router: HttpRouter) {
    router
      .add[SampleController]  // Register your Controller
  }
}
```

### Mysql Access with Quill
```scala
// Define model
case class Users( id: Int, name: String, createdAt: Date)

// Define repository
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

}
```

### Mysql Access with Slick
```scala
// Define model
import org.joda.time.DateTime
case class Users(id: Long, name: String, createdAt: DateTime)

// Define slick table & repository
import javax.inject.Inject

import com.github.ikhoon.modules.SlickDatabaseModule.SlickDataBaseSource
import com.github.tototoshi.slick.MySQLJodaSupport._
import org.joda.time.DateTime

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class SlickUserRepository @Inject() (db: SlickDataBaseSource) {

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
```

### Register Finagle Http Client
```scala
// Create Http Client Module
object FakeHttpClientModule {
  def apply() = new BasicHttpClientModule {
    @Named("fake") @Provides @Singleton
    def provideHttpClient(mapper: FinatraObjectMapper, config: Config) =
      super.provideHttpClient(mapper, config.as[String]("fake.host"), config.as[Int]("fake.port"))
  }
}

// Register fake client on FinatraServer
class FinatraServer extends HttpServer {
  override def modules = Seq(FakeHttpClientModule())
  ...
}

// Use Http Client
// file : FackService.scala
import javax.inject.{ Inject, Named }

import com.fasterxml.jackson.databind.JsonNode
import com.twitter.finatra.httpclient.{ HttpClient, RequestBuilder }
import com.twitter.util.Future
import com.typesafe.config.Config
import net.ceedubs.ficus.Ficus._

// inject httpClient using @Named("fake") annotation
class FakeService @Inject() (@Named("fake") httpClient: HttpClient, config: Config) {
  def withSleep(sec: Int): Future[JsonNode] = {
    val url = config.as[String]("fake.host") + s"?sleep=$sec"
    httpClient.executeJson[JsonNode](RequestBuilder.get(url))
  }
}
```

## RESTful API Document using Swagger
* Write your swagger document using [swagger-finatra](https://github.com/xiaodongw/swagger-finatra) - [Add document information for you controller](https://github.com/xiaodongw/swagger-finatra#add-document-information-for-you-controller)
* After start server, open document url [`http://localhost:9999/api-docs/ui`](http://localhost:9999/api-docs/ui)


## Auto Restart
[Restart server](https://github.com/spray/sbt-revolver) if scala file has changed.
```bash
./activator "~re-start"
```

## Remote Debugging
```bash
./activator -jvm-debug <port> run
```
Reference : http://stackoverflow.com/questions/19473941/how-to-debug-play-application-using-activator
## Troubleshooting
If `sbt run` or `activator run` raise dependencies error then clear ivy's cache files and retry.
```bash
rm -rf ~/.ivy2/cache/
```
