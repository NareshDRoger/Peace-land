package Main
import org.apache.spark.sql.SparkSession

object AppliGestionAlerte_STEP3 extends App{
  //APPLICATION QUI LIT LA STREAM ET QUI GERE LES ALERTES (écrit avec un composant distribué)
  //afficher en terminal à tel endroit il y a une personne qui s'énerève

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
  /*
  val df = spark
    .readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", "localhost:9092")
    .option("subscribe", "quick-start")
    .option("includeHeaders", "true")
    .load()

  print("SECOND STEP")
  print(df.head())
  */

  /*
  val df = lines.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
    .as[(String, String)]

  print(df.head())
   */


  //APPLICATION QUI LIT LA STREAM ET QUI DEVERSE DANS LE DATALAKE (écrit avec un composant distribué)





}
