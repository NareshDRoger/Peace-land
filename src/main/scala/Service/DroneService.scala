package Service

//Pour l ecriture de fichier csv
import Model.Drone

import scala.io.{BufferedSource, Source}

//import au.com.bytecode.opencsv.CSVWriter
import Model.Location

import scala.util.Random

class DroneService {
  lazy val citizenService = new CitizenService()
  lazy val caractere = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

  lazy val lastNameFile: BufferedSource = Source.fromFile("resources/randomOnlyLastName.csv")
  lazy val listOfName=lastNameFile.getLines().drop(1).toList

  lazy val loremIpsumFile: BufferedSource = Source.fromFile("resources/randomWordLorem.csv")
  lazy val listOfWord=loremIpsumFile.getLines().drop(1).toList

  def generateRandomLocation(): Location = Location (Random.nextFloat(), Random.nextFloat())

  def generateRandomWord(n:Int) = (1 to n).map(_ => caractere(Random.nextInt(caractere.length))).mkString

  def generateRandomListOfWords(n:Int, word:List[String]): List[String] = n match {
    case 0 => List()
    case _ => List(word(Random.nextInt(word.length))) ++ generateRandomListOfWords(n-1,word)
  }

  def generateRandomDrone(n:Int):List[Drone]= n match {
      case 0 => List()
      case _ => List(Drone("Drone."+generateRandomWord(5))) ++ generateRandomDrone(n-1)
    }
}
