package Main


import Stream.stream_kafka_final

import scala.io.Source


object AppliStreaming_STEP2 extends App{
  val stream = new stream_kafka_final()

  print("LET'S BEGGIN STREAMING")
  val filename = "resources/report.json"
  val file = Source.fromFile(filename).getLines()
  file.foreach{
    line => stream.writeToKafka("quick-start",line)

  }
  //stream.consumeFromKafka("quick-start")
}
