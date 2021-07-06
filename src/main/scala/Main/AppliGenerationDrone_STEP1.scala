package Main

import java.io.{BufferedWriter, FileWriter}

import Service.{CitizenService, DroneService}
import Stream.stream_kafka_final


object AppliGenerationDrone_STEP1 extends App{

  lazy val droneService = new DroneService();
  lazy val citizenService = new CitizenService();
  val stream = new stream_kafka_final()


  def generate()= {

    lazy val randomDrone = droneService.generateRandomDrone(3)

    //Generation random de report pour chaque drones
    lazy val report = randomDrone.map(_.generateReport(5)).flatten

    //Serialization des reports de chaque drones
    lazy val jsonReport = report.map(_.serializeJson)

    //Ecriture des reports dans un fichier .json
    val file = new BufferedWriter(new FileWriter("resources/report.json", true))
    jsonReport.map(x => {
      file.write(x.toString)
      //stream.writeToKafka("quick-start",x.toString)
      //stream.consumeFromKafka("quick-start")
      file.newLine()
    }
    )
    file.close()
  }


  generate()

}
