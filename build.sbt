// update the scala sbt file 
libraryDependencies ++= Seq(
  "org.apache.poi" % "poi-ooxml" % "5.2.3",
  "org.apache.poi" % "poi" % "5.2.3"
)

ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.3.4"


lazy val root = (project in file("."))
  .settings(
    name := "assignment-2-group-1-yusuf-justin"
  )

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.18" % Test
)

