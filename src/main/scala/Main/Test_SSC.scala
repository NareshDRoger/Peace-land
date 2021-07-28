package Main

object Test_SSC extends App {
  import org.apache.spark._
  import org.apache.spark.streaming._
  //CREER UN STREAMINGCONTEXT LOCAL AVEC DEUX THREAD ET UN INVERVALLE DE TRAITEMENT PAR LOT DUNE SECONDE
  //CRATION DU MASTER 2 COEURS





//  val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount").set("spark.driver.allowMultipleContexts","true")
//
//
//  // UN DSTREAM CONTIENT 1 SECONDE DE DONNEE
//  val ssc= new StreamingContext(conf,Seconds(10))
//
//  //SPARK STREAMING SE MET EN ATTENTE DE DONNEE
//  val lines = ssc.socketTextStream("localhost",5555)
//
//  //worcounts
//
//  val words = lines.flatMap(_.split(" "))
//  val pairs = words.map(word => (word,1))
//  val wordCounts = pairs.reduceByKey(_ + _)
//
//  //affiche les dix premiers éléments de chaque RDD généré dans ce DSTREAM
//  wordCounts.print()

  //lancer le calcul
  //ssc.start()

  //ATTEND LA FIN DU CALCUL
  //ssc.awaitTermination()
}
