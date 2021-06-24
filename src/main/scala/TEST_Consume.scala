import java.io.{BufferedWriter, FileWriter}
import java.io.{BufferedWriter, FileWriter}

import org.apache.kafka.clients.producer.ProducerRecord
import stream_Kafka.{Consume, Produce}
import com.google.gson.Gson
import org.apache.kafka.clients.consumer.{ConsumerRecord, KafkaConsumer}
import java.time.Duration

import scala.collection.JavaConverters._

object TEST_Consume extends App {

  //READ MESSAGE
  val consume = new Consume()
  consume.consumer.subscribe(List("topicTest").asJava)

  //val records :ConsumerRecord[String, String] = consume.consumer.poll(Duration.ofMillis(100))
  val records  = consume.consumer.poll(Duration.ofMillis(100))

  records.asScala.foreach{ record =>
    println(s"offset = ${record.offset()}, key = ${record.key()}, value = ${record.value()}")
  }
  consume.consumer.commitSync()

  print("FINISH")
}
