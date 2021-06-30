import java.io.{BufferedWriter, FileWriter}
import java.io.{BufferedWriter, FileWriter}
import java.time.Duration

import Model.{Citizen, Location, Report}
import org.apache.kafka.clients.producer.ProducerRecord
import org.joda.time.DateTime
import com.google.gson.Gson
import org.apache.kafka.clients.consumer.{ConsumerRecord, KafkaConsumer}

import scala.collection.JavaConverters._


object TEST extends App{



  //Model.Citizen(Id:String, Name:String, Age:Int, Peacescore: Int)
  val c1 = new Citizen("TATA","toto",23,123)
  val c2 = new Citizen("T","oto",223,3)
  val c3 = new Citizen("TA","to",2223,13)

  val l1 = new Location(12.123f,34.0f)

  val r1 = new Report("Report1","Drone1", l1,  List(c1,c2,c3),  List("bonjour", "au revoir"))//,DateTime.now)
  val r2 = new Report("Report2","Drone2", l1,  List(c1,c2),  List("popopop", "bonsoir"))//,DateTime.now)

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
  //println(jsonKey)

  //println(jsonValue)




  //-------------------------------------RESTE A FAIRE-------------------------------------//
  //String => record
  //Envoyer les records dans le produce en faisan procude.producer.send (faire un foreach)
  //Lire le stream en implémentant un Consumer
  //-------------------------------------RESTE A FAIRE-------------------------------------//












}
