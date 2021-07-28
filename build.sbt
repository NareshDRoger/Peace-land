name := "Peace-land"

version := "0.1"

scalaVersion := "2.11.8"





/*libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.0"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.0"

 */
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.6.5"
libraryDependencies += "org.twitter4j" % "twitter4j-core" % "4.0.5"
libraryDependencies += "com.google.code.gson" % "gson" % "2.7"
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6"



//https://repo1.maven.org/maven2/org/scala-lang/scala-library/2.11.8/
// SCALA 2.12 ..................libraryDependencies += "org.scala-lang" % "scala-library" % "2.12.12"
libraryDependencies += "org.scala-lang" % "scala-library" % "2.11.8"
libraryDependencies += "com.typesafe.play" %% "play" % "2.4.11"

libraryDependencies += "org.apache.kafka" % "kafka-clients" % "2.7.0"
libraryDependencies += "org.apache.kafka" %% "kafka" % "2.1.0"
//libraryDependencies += "net.liftweb" %% "lift-json" % "2.5+"
//version imcompatible avec spark 2.4.0
libraryDependencies += "com.typesafe.play" %% "play" % "2.7.8"
fork in run := true


/*
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.1.2",
  "org.apache.spark" % "spark-streaming_2.12" % "3.1.2" % "provided",
  "org.apache.kafka" % "kafka_2.11" % "0.10.2.1",
  "org.apache.spark" % "spark-streaming-kafka-0-10_2.11" % "2.1.0"

)
*/

dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.6.7"
libraryDependencies ++= Seq(
  //"org.apache.spark" %% "spark-core" % "2.1.0",
  //"org.apache.spark" %% "spark-sql" % "2.1.0",
  "org.apache.spark" % "spark-streaming-kafka-0-10_2.11" % "2.1.0",
  "org.apache.spark" % "spark-streaming_2.11" % "2.1.0",




)



