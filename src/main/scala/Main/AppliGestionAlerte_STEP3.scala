package Main
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark._

object AppliGestionAlerte_STEP3 extends App{
  //APPLICATION QUI LIT LA STREAM ET QUI GERE LES ALERTES (écrit avec un composant distribué)
  //afficher en terminal à tel endroit il y a une personne qui s'énerève

  /*
  val spark = SparkSession
    .builder()
    .appName("Simple Spark App IABD1")
    .master("local[*]")
    .getOrCreate()

  print("FIRST STEP")
  import spark.implicits._


  val kafkaDF = spark.read.format("kafka")
    .option("kafka.bootstrap.servers", "localhost:9092")
    .option("subscribe", "quick-start")
    .option("startingOffsets", "earliest")
    .load()

  print("SECOND STEP")
  kafkaDF.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)", "headers")
    .as[(String, String, Array[(String, Array[Byte])])]
  print("THIRD STEP")


  */


  //LIEN
  //https://spark.apache.org/docs/2.2.0/streaming-programming-guide.html#a-quick-example
  //https://spark.apache.org/docs/2.2.0/streaming-kafka-0-10-integration.html


  val conf = new SparkConf().setMaster("local[*]").setAppName("ProjetIABD")
  val ssc = new StreamingContext(conf, Seconds(1))


  //Au lieu du val lines = ssc.socketTextStream("localhost", 9999)
  val kafkaParams = Map[String, Object](
    "bootstrap.servers" -> "localhost:9092,anotherhost:9092",
    "key.deserializer" -> classOf[StringDeserializer],
    "value.deserializer" -> classOf[StringDeserializer],
    "group.id" -> "use_a_separate_group_id_for_each_stream",
    "auto.offset.reset" -> "latest",
    "enable.auto.commit" -> (false: java.lang.Boolean)
  )

  val topics = Array("topicA", "topicB")
  val stream = KafkaUtils.createDirectStream[String, String](
    streamingContext,
    PreferConsistent,
    Subscribe[String, String](topics, kafkaParams)
  )

  stream.map(record => (record.key, record.value))


  // Split each line into words
  val words = lines.flatMap(_.split(" "))

  ssc.start()             // Start the computation
  ssc.awaitTermination()





}
