import com.google.gson.Gson
import play.api.libs.json.{JsObject, JsString, JsValue}
import org.joda.time.DateTime

case class Report(Id:String, DroneReporterId: String, Location:Location, SurroundingCitizen:List[Citizen], WordsHeard:List[String], Timestamp: DateTime){


  def parseFromJson(lines:Iterator[String]):Iterator[Report] = {
    val gson = new Gson
    lines.map(line => gson.fromJson(line, classOf[Report]))
  }

  def serializeJson(): JsValue = {
    val reportSeq = Seq(
      "Id" -> JsString(Id),
      "DroneReporterId" -> JsString(DroneReporterId),
      "Location" -> JsString(Location.Latitude.toString+ " " + Location.Longitude.toString),
      "SurroundingCitizen" -> JsString(SurroundingCitizen(0).Name),
      "WordsHeard" -> JsString(WordsHeard.mkString(" ")),
      "Timestamp" -> JsString(Timestamp.toString)
    )
    JsObject(reportSeq)
  }



}

