import sbt._

name := "developer-summit-kafka-streams"

version := "0.1.0"

scalaVersion := "2.11.12"

mainClass in assembly := Some("jp.co.istyle.ElementAppenderStreamApplication")

resolvers ++= Seq(
  "clojars" at "https://clojars.org/repo",
  "conjars" at "http://conjars.org/repo",
  "plugins" at "http://repo.spring.io/plugins-release",
  "sonatype" at "http://oss.sonatype.org/content/groups/public/",
  "mvn" at "https://mvnrepository.com/artifact"
)

libraryDependencies ++= Seq(
  "org.json4s" %% "json4s-jackson" % "3.2.11",
  "org.json4s" %% "json4s-core" % "3.2.11",
  "org.json4s" %% "json4s-ast" % "3.2.11",
  "org.apache.kafka" % "kafka-clients" % "1.0.0",
  "org.apache.kafka" % "kafka-streams" % "1.0.0",
  "commons-io" % "commons-io" % "2.6" % Test,
  "junit" % "junit" % "4.12" % Test,
  "org.assertj" % "assertj-core" % "3.9.0" % Test,
  "org.scalatest" %% "scalatest" % "3.0.4" % Test
)

assemblyMergeStrategy in assembly := {
  case PathList("org","aopalliance", xs @ _*) => MergeStrategy.last
  case PathList("javax", "inject", xs @ _*) => MergeStrategy.last
  case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
  case PathList("javax", "activation", xs @ _*) => MergeStrategy.last
  case PathList("org", "apache", xs @ _*) => MergeStrategy.last
  case PathList("com", "google", xs @ _*) => MergeStrategy.last
  case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
  case PathList("com", "codahale", xs @ _*) => MergeStrategy.last
  case PathList("com", "yammer", xs @ _*) => MergeStrategy.last
  case "about.html" => MergeStrategy.rename
  case "META-INF/ECLIPSEF.RSA" => MergeStrategy.last
  case "META-INF/mailcap" => MergeStrategy.last
  case "META-INF/mimetypes.default" => MergeStrategy.last
  case "plugin.properties" => MergeStrategy.last
  case "log4j.properties" => MergeStrategy.last
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

assemblyJarName in assembly := { s"${name.value}-${version.value}.jar" }
