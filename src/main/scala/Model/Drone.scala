package Model

import scala.util.Random
import Service.{CitizenService, DroneService}

case class Drone(Id:String){
  lazy val droneService = new DroneService();
  lazy val citizenService = new CitizenService();

  def generateReport(n:Int):List[Report] = n match{
    case 0 => List()
    case _ => List(Report(
      "Report."+droneService.generateRandomWord(5),
      Id,
      droneService.generateRandomLocation,
      citizenService.generateRandomCitizen(1, droneService.listOfName),
      droneService.generateRandomListOfWords(Random.nextInt(10)+1, droneService.listOfWord)))++ generateReport(n-1)
  }
}


