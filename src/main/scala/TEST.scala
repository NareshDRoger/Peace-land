import java.io.{BufferedWriter, FileWriter}
import java.io.{BufferedWriter, FileWriter}

import org.apache.kafka.clients.producer.ProducerRecord



import org.joda.time.DateTime
import stream_Kafka.Produce
import com.google.gson.Gson
import org.apache.kafka.clients.consumer.{ConsumerRecord, KafkaConsumer}

import scala.collection.JavaConverters._

object TEST extends App{


  //Citizen(Id:String, Name:String, Age:Int, Peacescore: Int)
  val c1 = new Citizen("TATA","toto",23,123)
  val c2 = new Citizen("T","oto",223,3)
  val c3 = new Citizen("TA","to",2223,13)

  val l1 = new Location(12.123f,34.0f)

  val r1 = new Report("Report1","Drone1", l1,  List(c1,c2,c3),  List("bonjour", "au revoir"),DateTime.now)
  val r2 = new Report("Report2","Drone2", l1,  List(c1,c2),  List("popopop", "bonsoir"),DateTime.now)

  val JsonTest1 = r1.serializeJson()
  val JsonTest2 = r2.serializeJson()


  val listReport = List(r1,r2)


  //Ecritrue dans le fichier report.json
  lazy val jsonReport = listReport.map(_.serializeJson)
  val file = new BufferedWriter(new FileWriter("ressources/report.json", true))
  jsonReport.map(x => {
    file.write(x.toString)
    file.newLine()

  }
  )



  file.close()



  //DANS LE STREAM
  //ensemble de rapport => ensemble de json => ensemble record
  //foreach producer.send()


  //LE PRINCIPE :
  //case clase => json value => String => dans le record




  val jsonKey = "keyTest"
  val gson = new Gson
  val jsonValue = gson.toJson(r1)
  //println(jsonValue)

  //SEND MESSAGE
  //PROBLEME DU produce.producer.send
  val record = new ProducerRecord[String, String]("topicTest",jsonKey,jsonValue)
  println("RECORD")
  val produce = new Produce()
  println("INITIALISE")
  produce.producer.send(record)
  println("SENDDDD")
  produce.producer.close()

  print("OKEY")



  /*
  //READ MESSAGE
  val consume = new Consume()
  consume.consumer.subscribe(List("topicTest").asJava)

  //val records :ConsumerRecord[String, String] = consume.consumer.poll(Duration.ofMillis(100))
  val records  = consume.consumer.poll(Duration.ofMillis(100))


  records.asScala.foreach{ record =>
    println(s"offset = ${record.offset()}, key = ${record.key()}, value = ${record.value()}")
  }
  consume.consumer.commitSync()
  */



  //-------------------------------------RESTE A FAIRE-------------------------------------//
  //String => record
  //Envoyer les records dans le produce en faisan procude.producer.send (faire un foreach)
  //Lire le stream en implémentant un Consumer
  //-------------------------------------RESTE A FAIRE-------------------------------------//













}
