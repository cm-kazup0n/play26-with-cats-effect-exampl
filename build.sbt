name := "play26-with-cats-effect-example"

version := "0.1"

scalaVersion := "2.12.8"
lazy val hammockVersion = "0.9.0"

enablePlugins(PlayScala)
libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-effect" % "1.2.0",
  "com.pepegar" %% "hammock-core" % hammockVersion,
  "com.pepegar" %% "hammock-akka-http" % hammockVersion,
  "com.pepegar" %% "hammock-circe" % hammockVersion,
  "com.dripower" %% "play-circe" % "2610.0",
  "io.circe" %% "circe-generic-extras" % "0.11.1",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
)

scalacOptions ++= Seq("-encoding",
  "UTF-8",
  "-target:jvm-1.8",
  "-deprecation",
  "-feature",
  "-unchecked",
  "-language:implicitConversions",
  "-language:postfixOps")