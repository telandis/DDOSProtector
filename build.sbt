val ScalatraVersion = "2.7.0"

organization := "VictorPersonal"

name := "DDOSProtector"

version := "0.1.0-SNAPSHOT"

//scalaVersion := "2.13.1"
scalaVersion := "2.12.0"

resolvers += Classpaths.typesafeReleases
//resolvers += Resolver.bintrayIvyRepo("com.eed3si9n", "sbt-plugins")
resolvers += Resolver.url("bintray-sbt-plugins", url("http://dl.bintray.com/sbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)


libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
  "ch.qos.logback" % "logback-classic" % "1.2.3" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % "9.4.28.v20200408" % "container;compile",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided"

)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.6")
enablePlugins(SbtTwirl)
enablePlugins(ScalatraPlugin)