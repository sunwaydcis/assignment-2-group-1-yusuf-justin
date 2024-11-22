// Written by Yusuf 
// And Justin Qiu

// Scala Build Version : 3.3.4
// SBT version : 1.9.9
// JavaSDK required : JDK 21

// import relevant libraries to process data
import scala.io.Source
import java.time.LocalDate

// define file path
final val filePath : String = "./res/hospital_assignment.csv"

// define placeholder variable to store data loaded from hospital_assignment.csv
final val hospitalData : List[List[String]] 

// define method to read data from file path, and parse it into hospitalData
def readCSV(fileName : String): List[List[String]] =
  // create a pointer to the filePath
  val filePath : BufferedSource = Source.fromFile(fileName)
  // initialize temporary placeholder to hold data
  var fileData : List[List[String]] = List()
  // to count number of records in the file
  var counter : Int = 0
  // drop the header row, unnecessary for record processing
  for (line <- filePath.getLines().drop(1)) 
    // for each line
    // split the information by each column// comma
    val cols : List = line.split(",").map(_.trim).toList
    fileData = fileData :+ cols
    //  increase counter - to count number of records
    count += 1 
  end for
  println(s"Amount of hospital records in csv file: {$counter} records")
  filePath.close()
  fileData
end readCSV

// defines a method to init State objects 
def state_init(): List[Hospital] =
  // there are 15 states altogather so init 15 state objects
  val State = List(
    "Selangor", "Johor", "Kedah", "Kelantan", "Melaka", "Negeri Sembilan",
    "Pahang", "Perak", "Pulau Pinang", "Sabah", "Sarawak", "Terengganu",
    "W.P. Kuala Lumpur", "W.P. Labuan", "Perlis","W.P. Putrajaya"
  )
  val Hospitals = State.map(state => Hospital(_state = state))
  // program returns Hospitals as List of Hospital objects
  return Hospitals
end state_init

// create a function to sort records into their respective state objects
def sort_records(_unprocessed_records : List[List[String]], hospitalList : List[Hospital]): List[Hospital] =
  // function takes in 2 inputs
  // _unprocessed_records - List of List of Strings that is data parsed from csv file
  // hospitalList - List of Hospital class objects that will hold the record for their respective states Hospitals

  for (record <- _unprocessed_records)
    // extract state information from the record
    val state = record(1).trim
    // match the record information to its corresponding Hospital object
    val hospital = hospitalList.find(_.State == state)

    // for a record, when a hospital object match is found, attach the record to it
    hopsital match
      case Some(hospital) => hospital.addRecord(record)
      case None =>println(s"No Corresponding hospital found for $state")
  end for
  hospitalList
end sort_records      

// define a function for question 1
Question 1
// return the state with the highest total beds::

def Question1(records: List[Hospitals]): Unit =
  // get a list of state
  val StateList : List[String] = records.map(records -> records.state)
  val StateRecentRecord : List[Record] = StateList.map(StateList -> StateList.latestRecord)

  // compare data between each state
  var StateWithHighestBed : String = StateList(0)
  var HighestBedCount : Int = StateRecentRecord(0).TotalBed
  // iterate through the rest of the records
  counter = 1
  for ( counter < StateList.length ) do
    // compare if current state has more bed than pointer
    if (StateRecentRecord(counter).TotalBed > HighestBedCount) then
      // reassign state name
      StateWithHighestBed = StateList(counter)
      HighestBedCount = StateRecentRecord(counter).TotalBed
    end if
  end for
  
  println(s"The state with the highest number of beds : $HighestBedCount")
end Question1

@main def main(): Unit =
  // 1-test readCSV method
  hospitalData = readCSV()
  // 2-Test hopsital Data
  // println(hospitalData)
  
  // create holder for state objects
  var HospitalObjects: List[Hospital] = state_init() 
  // println(HospitalObjects) -- works well
  HospitalObjects = sort_records(_unprocessed_records = hospitalData, hospitalList = HospitalObjects)

  // Test hospital Objects latestRecord method

  // Question 1
  Question1(HospitalObjects)
  // Question 2

  // Question 3


