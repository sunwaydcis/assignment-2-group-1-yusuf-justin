// Information : file holds class information to structure the record data
// Created by Yusuf & Justin - November 20th 2024

// add imports for LocalDate object class
import java.time.LocalDate

class Hospital(_state : String):
  // constructor only requires State as all hospital data is summed into state
  val State : String = _state
  // have one collection data structure to store all records
  // to be fixed later
  var Record : List[Records] = List()

  // Implement an addRecordByState method
  def AddRecord(_unfilteredData : List[String]) : Unit =
       // Assess the list items
       if _unfilteredData(1) == this.State then
          //if the record belongs to the current state/ similar state
          val record = Records(
            date = LocalDate.parse(_unfilteredData.head),
            total_Bed = _unfilteredData(2).toInt,
            covid_Bed = _unfilteredData(3).toInt,
            Ncritical_Bed = _unfilteredData(4).toInt,
            admittedPui = _unfilteredData(5).toInt,
            admittedCovid = _unfilteredData(6).toInt,
            dPui = _unfilteredData(8).toInt,
            dCovid =  _unfilteredData(9).toInt,
            hospCovid = _unfilteredData(11).toInt,
            hospPui = _unfilteredData(12).toInt,
            hospNonCovid = _unfilteredData(13).toInt
          ) 
          this.Record = this.Record.prepended(record) // -- change to optimize #1
        //println(s"$record has been recorded into the program")   
    end AddRecord   

  // add a toString Method for Hospital Object
  override def toString: String =
    // overrides manual to String
    this._state + " has " + this.Record.length + " Hosptial Records"
  end toString

  // Implemented a new latestRecord function 
  // improved & more efficient
  // define a method to track latest record
  def latestRecord: Records =
    // load records for hospital
    val TotalHospitalRecords : List[Records] = this.Record
    // set first record as latestRecord & its date as latest date
    var LatestRecord : Records = TotalHospitalRecords(0)
    var LatestDate : LocalDate = LatestRecord.Date
    // process the total records
    for ( records <- TotalHospitalRecords)
      if( records.Date.isAfter(LatestDate)) then
        LatestRecord = records
        LatestDate = records.Date
      end if
    end for
    LatestRecord
  end latestRecord

end Hospital

// record class to hold each row data
class Records(date : LocalDate , total_Bed : Int, covid_Bed : Int, Ncritical_Bed : Int, admittedPui : Int , admittedCovid : Int, dPui : Int, dCovid : Int, hospCovid : Int, hospPui : Int, hospNonCovid: Int):
  // added constructor into methods
  val Date : LocalDate = date
  val TotalBed : Int = total_Bed
  val CovidBed : Int = covid_Bed
  val NonCritBed : Int = Ncritical_Bed
  val Admitted_Pui : Int = admittedPui
  val Admitted_Covid : Int = admittedCovid
  def Admitted_Total : Int = Admitted_Pui + Admitted_Covid
  val Discharged_Pui : Int = dPui
  val Discharged_Covid : Int = dCovid
  def Discharged_Total : Int = Discharged_Pui + Discharged_Covid
  val Hospital_Covid : Int = hospCovid
  val Hospital_Pui : Int = hospPui
  val Hospital_NonCovid : Int = hospNonCovid
end Records