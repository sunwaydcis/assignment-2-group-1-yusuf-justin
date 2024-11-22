// Written by Yusuf 
// And Justin Qiu

// Scala Build Version : 3.3.4
// SBT version : 1.9.9
// JavaSDK required : JDK 21

// import relevant libraries to process data
import scala.io.Source
import java.time.LocalDate
import scala.collection.mutable.Map

// define file path
final val _FilePath : String = "./res/hospital_assignment.csv"

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

// Streamlined version of readCSV, state_init, sort_records
def ProcessCSV(filePath : String) : List[Hospital] =
  // attempt to optimze by streamlining the readCSV, state_init, and sort_records
  // utilize map instead of list to store hospital data
  var hospitals : Map[String, Hospital] = Map()
  // read data from file path
  val dataFromFile : BufferedSource = Source.fromFile(filePath)
  try 
    dataFromFile.getLines().drop(1).foreach {
      line =>
      // sort 
      val cols = line.split(",").map(_.trim).toList
      val state = cols(1)
      // insert logic later to sort by state
      // if state doesn't exist, 
    }


// define a function for question 1
// return the state with the highest total beds::

// improved code for question 1
def Question1(records: List[Hospital]): Unit =
  // Get the latest record for each state
  val latestRecordsByState: Map[String, Records] = records.map { hospital =>
    hospital.State -> hospital.latestRecord
  }.toMap
  // Find the state with the highest total beds
  val (stateWithHighestBeds, highestRecord) = latestRecordsByState.maxBy:
    case (_, record) => record.TotalBed
  println(s"Question 1: $stateWithHighestBeds ||| ${highestRecord.TotalBed} total beds")// write the code/ pseudocode for question 2
end Question1

// define a function for question 2
// define substeps for question 2
// summarize all states beds, 
// compare ratio between covid beds to every other bed types

//finding common ratio requires common denominator, use GCD function
// citation : Function is generated by chatgpt
def GCD(int1 : Int, int2 :Int): Int =
  if (int2 == 0) int1 else GCD(int2, int1%int2 )
end GCD

// citation : function is generated by chatgpt
def CalcRatio(int1 : Int, int2 : Int): (Int,Int) =
  val Divisor : Int = GCD(int1, int2)
  return (int1 / Divisor, int2 / Divisor)
end CalcRatio

def Question2(records: List[Hospital]): Unit =
  // init placeholder to summarize
  // ::Total Beds available in all hospitals
  var TotalBedsAvailable : Int = 0
  var TotalBed4Covid : Int = 0

  for (hospital <- records)
    // traverse each record, access total bed of each hospital at each state
    TotalBedsAvailable += hospital.latestRecord.TotalBed
    TotalBed4Covid += hospital.latestRecord.CovidBed
  end for

  //println(s"the total amount of beds available : $TotalBedsAvailable")
  //println(s"the total amount of beds reserved for covid : $TotalBed4Covid")

  // print the results
  val Ratio : (Int,Int) = CalcRatio(int1 = TotalBedsAvailable, int2 = TotalBed4Covid)
  println(s"Question 2 : Ratio of total Beds available to beds reserved for covid is $Ratio")
end Question2  

// Question 3
// 
def Question3 (records : List[Hospital]): Unit =
  // create Map -> StateName -> Amount Of Records
  val RecordByStates : Map[String, List[Records]] = records.map{ hospital => hospital.State -> hospital.Record}.toMap
  println(RecordByStates)
  println("Question 3:\n")
  for ( (state , records) <- RecordByStates)
    // At each state
    var cSuspectedAdmissions : Int = 0
    var cCovidAdmissions : Int = 0
    var cNonCovidAdmissions : Int  =0
    for ( record <- records)
      // for each record in records collection
      // extract the PUi
      cSuspectedAdmissions += record.Admitted_Pui
      // extract the Covid admissions
      cCovidAdmissions += record.Admitted_Covid
      // extract the non-covid admissions : formula Pui - covid admissions
      cNonCovidAdmissions += (record.Admitted_Total - record.Admitted_Covid)
    end for
    // calculate the average
    println("======================\n")
    println(s"$state")
    println("Average Suspected admissions :" + (cSuspectedAdmissions.toDouble /records.length).ceil + " individuals")
    println("Average Covid Admissions :"+(cCovidAdmissions.toDouble / records.length).ceil + " individuals")
    println("Average Non Covid Admissions :"+(cNonCovidAdmissions.toDouble / records.length).ceil + " individuals")
  end for
end Question3


// optimization data : Before with readCSV, state_init & sort_records
// run #1 Execution time: 5.1172229 seconds
// run #2 Execution time: 4.2066958 seconds
// run #3 Execution time: 3.0017617 seconds
// run #4 Execution time: 3.8785137 seconds
// run #5 Execution time: 3.885426 seconds
// run $6 Execution time: 3.8028892 seconds
// run #7 Execution time: 3.4410411 seconds
// run #8 Execution time: 2.9825944 seconds
// run #9 Execution time: 3.0242066 seconds
// run #10 Execution time: 4.3136249 seconds
// run #11 Execution time: 3.1380113 seconds
// run #12 Execution time: 4.2288176 seconds

// Average : 3.75 seconds

// optimization data : changes to addRecord use prepend()
// 
// run #1 Execution time: 3.7557482 seconds
// run #2 Execution time: 3.1714426 seconds
// run #3 Execution time: 3.0196635 seconds
// run #4 Execution time: 3.6350455 seconds
// run #5 Execution time: 3.4143609 seconds
// run #6 Execution time: 3.4100239 seconds
// run #7 Execution time: 2.9582922 seconds
// run #8 Execution time: 3.3137803 seconds
// run #9 Execution time: 3.0063626 seconds
// run #10 Execution time: 3.5524468 seconds
// run #11 Execution time: 3.3130368 seconds
// run #12 Execution time: 2.9471243 seconds

// Average : 3.29 Seconds

// optimization data : With ProcessCSV
// run #1 
// run #2
// run #3
// run #4
// run #5
// run #6
// run #7
// run #8
// run #9
// run #10
// run #11
// run #12

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
  Question2(HospitalObjects)
  // Question 3
  Question3(HospitalObjects)