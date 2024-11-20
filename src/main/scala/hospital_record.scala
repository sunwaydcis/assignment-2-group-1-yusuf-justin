// Information : file holds class information to structure the record data
// Created by Yusuf & Justin - November 20th 2024


class Hospital(_state : String):
  // constructor only requires State as all hospital data is summed into state
  val State : String = _state
  // have one collection data structure to store all records
  // to be fixed later
  var Record : List[] = List()

  // Implement an addRecordByState method
  // def AddRecord(_unfilteredData : List[String]) : Unit =
       // Assess the list items
       if _unfilteredData(1) == this.State then
          //if the record belongs to the current state/ similar state
          val record = Records(
            date = _unfilteredData(0)
            total_Bed = _unfilteredData(2).toInt
            covid_Bed = _unfilteredData(3).toInt
            Ncritical_Bed = _unfilteredData(4).toInt
            admitted_pui = _unfilteredData(5).toInt
            admitted_covid = _unfilteredData(6).toInt
            dPui = _unfilteredData(7).toInt
            dCovid =  _unfilteredData(8).toInt
            hospCovid = _unfilteredData(9).toInt
            hospPui = _unfilteredData(10).toInt
            hospNonCovid = _unfilteredData(11).toInt
          ) 
       // to ensure the Record is added
       println(s"$record has been recorded into the program")   

end Hospital

// record class to hold each row data
class Records(date : String , total_Bed : Int, covid_Bed : Int, Ncritical_Bed : Int, admittedPui : Int , admittedCovid : Int, dPui : Int, dCovid : Int, hospCovid : Int, hospPui : Int, hospNonCovid: Int):
  // added constructor into methods
  var Date : String = date
  var TotalBed : Int = total_Bed
  var CovidBed : Int = covid_Bed
  var NonCritBed : Int = Ncritical_Bed
  var Admitted_Pui : Int = admittedPui
  var Admitted_Covid : Int = admittedCovid
  def Admitted_Total : Int = Admitted_Pui + Admitted_Covid
  var Discharged_Pui : Int = dPui
  var Discharged_Covid : Int = dCovid
  def Discharged_Total : Int = Discharged_Pui + Discharged_Covid
  var Hospital_Covid : Int = hospCovid
  var Hospital_Pui : Int = hospPui
  var Hospital_NonCovid : Int = hospNonCovid
end Records