lazy val root = (project in file(".")).
  settings(
    name := "parilis",
    organization := "de.geekonaut",
    version := "1.0.0",
    scalaVersion := "2.11.4"
  )

resolvers += "spray repo" at "http://repo.spray.io"

libraryDependencies ++= Seq(
  "io.spray" %% "spray-can" % "1.3.2",
  "io.spray" %% "spray-http" % "1.3.2",
  "io.spray" %% "spray-httpx" % "1.3.2",
  "io.spray" %% "spray-json" % "1.3.1",
  "io.spray" %% "spray-util" % "1.3.2",
  "io.spray" %% "spray-client" % "1.3.2",
  "com.typesafe.akka" %% "akka-actor" % "2.3.3",
  "com.typesafe" % "config" % "1.2.1"
)