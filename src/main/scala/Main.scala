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
    "W.P. Kuala Lumpur", "W.P. Labuan", "Perlis"
  )
  val Hospitals = State.map(state => Hospital(_state = state))
  // program returns Hospitals as List of Hospital objects
  return Hospitals
end state_init


@main def main(): Unit =
  // 1-test readCSV method
  hospitalData = readCSV()
  // 2-Test hopsital Data
  // println(hospitalData)
  
  // create holder for state objects
  var HospitalObjects: List[Hospital] = state_init() 
  // println(HospitalObjects) -- works well


