
package Main
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.{SparkConf, SparkContext, TaskContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

// METHODE SPARK STREAMING A NE PAS UTILISER

object AppliGestionAlerte_STEP3 extends App{
  //APPLICATION QUI LIT LA STREAM ET QUI GERE LES ALERTES (écrit avec un composant distribué)
  //afficher en terminal à tel endroit il y a une personne qui s'énerève

  val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
    .set("spark.driver.allowMultipleContexts","true")
  val ssc= new StreamingContext(conf,Seconds(10))
  val confspark = new SparkConf().setAppName("NetworkWordCount").setMaster("local[2]")
  val sparkcontext = new SparkContext(confspark)



  val kafkaParams = Map[String, Object](
    "bootstrap.servers" -> "localhost:9092,anotherhost:9092",
    "key.deserializer" -> classOf[StringDeserializer],
    "value.deserializer" -> classOf[StringDeserializer],
    "group.id" -> "use_a_separate_group_id_for_each_stream",
    "auto.offset.reset" -> "latest",
    "enable.auto.commit" -> (false: java.lang.Boolean)
  )

  val topics = Array("quick-start")
  val stream = KafkaUtils.createDirectStream[String, String](
    ssc,
    PreferConsistent,
    Subscribe[String, String](topics, kafkaParams))
  stream.map(record => (record.key, record.value))

  import org.apache.spark.streaming.kafka010.OffsetRange

  val offsetRanges = Array( // topic, partition, inclusive starting offset, exclusive ending offset
    OffsetRange
      .create("quick-start", 0, 0, 100), OffsetRange.
      create("quick-start", 1, 0, 100))
  //val rdd = KafkaUtils.createRDD[String,String](sparkcontext, kafkaParams, offsetRanges, PreferConsistent)

  stream.foreachRDD { rdd =>
    val offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
    rdd.foreachPartition { iter =>
      val o: OffsetRange = offsetRanges(TaskContext.get.partitionId)
      println(s"${o.topic} ${o.partition} ${o.fromOffset} ${o.untilOffset}")
    }
  }

  ssc.start()

  //ATTEND LA FIN DU CALCUL
  ssc.awaitTermination()


}
