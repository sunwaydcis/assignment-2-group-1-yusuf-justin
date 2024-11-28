// Information : file holds class information to structure the record data
// Created by Yusuf & Justin - November 20th 2024

// add imports for LocalDate object class
import java.time.LocalDate
// redefine constructor
class Hospital(_state : String ):
  // hospitals record object fields
  val state : String = _state
  // 14 states, starting with an empty list at first
  var record : List[Records] = List()

  // add_record method
  def AddRecord(_list : List[String]): Unit =
    // check for state
    if _list(1) == this.state then
      // put pointer for record
      val _record = Records(
        _date= LocalDate.parse(_list.head),
        total_Bed = _list(2).toInt,
        covid_Bed = _list(3).toInt,
        ncritical_Bed = _list(4).toInt,
        admittedPui =  _list(5).toInt,
        admittedCovid = _list(6).toInt,
        dPui = _list(8).toInt,
        dCovid = _list(9).toInt,
        hospCovid = _list(11).toInt,
        hospPui = _list(12).toInt,
        hospNonCovid = _list(13).toInt
      )
      this.record = this.record.prepended(_record) 
      //println(s"Record for date ${record.date} added successfully to hospital in state ${this.State}.")
  end AddRecord

  // Subs to string method
  override def toString: String =
    "\n" + this._state +  "\nThis state has "+this.record.length + " hospital records"
  end toString

  def LatestRecord: Records =
    // load records for hospital
    val totalHospitalRecords : List[Records] = this.record
    // set first record as latestRecord & its date as latest date
    var latestRecord : Records = totalHospitalRecords(0)
    var latestDate : LocalDate = latestRecord.date
    // process the total records
    for ( records <- totalHospitalRecords)
      if( records.date.isAfter(latestDate)) then
        latestRecord = records
        latestDate = records.date
      end if
    end for
    latestRecord
  end LatestRecord
end Hospital

// hosp_covid,hosp_pui,hosp_noncovid
case class Records(_date : LocalDate , total_Bed : Int, covid_Bed : Int, ncritical_Bed : Int, admittedPui : Int , admittedCovid : Int, dPui : Int, dCovid : Int, hospCovid : Int, hospPui : Int, hospNonCovid: Int) :
  var date: LocalDate = _date// Date will be identifying value for each record -- primary key
  // number of beds for all hospital
  var totalBed : Int = total_Bed
  // number of Beds reserved for covid-19 patients
  var covidBed : Int = covid_Bed
  // number of beds for non-critical patients
  var nonCritBed : Int = ncritical_Bed
  //
  var admitted_Pui : Int = admittedPui
  var admitted_Covid : Int = admittedCovid
  // derived val from admitted PUI + admitted Covid -- drop columns [7]
  def Admitted_Total : Int = admitted_Pui + admitted_Covid
  var discharged_Pui : Int = dPui 
  var discharged_Covid : Int = dCovid
  // derived val from discharged PUI + Discharged Covid -- drop columns [10]
  def Discharged_Total : Int = discharged_Pui + discharged_Covid
  var hospital_Covid : Int = hospCovid
  var hospital_Pui : Int = hospPui
  var hospital_NonCovid : Int = hospNonCovid
end Records
