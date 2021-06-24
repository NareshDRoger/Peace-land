package stream_Kafka
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord, KafkaConsumer, OffsetAndMetadata}
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.{StringDeserializer, StringSerializer}

import java.util.Properties
import scala.collection.JavaConverters.asJavaIterableConverter
import scala.concurrent.duration.Duration
import java.time.Duration



class Consume {

  val props: Properties = new Properties()
  props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")//"127.0.0.1:9092")
  props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
  props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
  props.put(ConsumerConfig.GROUP_ID_CONFIG, "myconsumergroup")


  val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props)
  //consumer.subscribe(List("topicTest").asJava)

}