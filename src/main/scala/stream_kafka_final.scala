import org.apache.kafka.clients.producer._
import java.util

import org.apache.kafka.clients.consumer.KafkaConsumer
import java.util.Properties

import TEST.r1
import com.google.gson.Gson
import org.joda.time.DateTime

import scala.collection.JavaConverters._

object stream_kafka_final {

  def main(args: Array[String]): Unit = {


    val c1 = new Citizen("TATA","toto",23,123)
    val c2 = new Citizen("T","oto",223,3)
    val c3 = new Citizen("TA","to",2223,13)

    val l1 = new Location(12.123f,34.0f)

    val r1 = new Report("Report1","Drone1", l1,  List(c1,c2,c3),  List("bonjour", "au revoir"),DateTime.now)


    val jsonKey = "keyTest"
    val gson = new Gson
    val jsonValue = gson.toJson(r1)


    writeToKafka("quick-start",jsonValue)
    consumeFromKafka("quick-start")
    print("Done")
  }



  def writeToKafka(topic: String, value: String): Unit = {
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    val producer = new KafkaProducer[String, String](props)
    val record = new ProducerRecord[String, String](topic, "key", value)
    producer.send(record)
    producer.close()
  }


  def consumeFromKafka(topic: String) = {
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("auto.offset.reset", "latest")
    props.put("group.id", "consumer-group")
    val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props)
    consumer.subscribe(util.Arrays.asList(topic))
    var i = 0
    while (i<10) {
      val record = consumer.poll(1000).asScala
      for (data <- record.iterator) {
        println(data.value())
        //LE RESTE
      }
      i = i + 1;
    }
  }


}