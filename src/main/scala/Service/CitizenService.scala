package Service

import Model.Citizen

import scala.util.Random

class CitizenService {
  val droneService = new DroneService;


  def generateRandomCitizen(n:Int, name: List[String]): List[Citizen] = n match{
    case 0 => List()
    case _ => List(Citizen(droneService.generateRandomWord(5),
      name(Random.nextInt(name.length)),
      Random.nextInt(100),
      Random.nextInt(100))) ++ generateRandomCitizen(n-1,name)
  }
}
