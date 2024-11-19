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
def readCSV(fileName : String): Unit =
  // create a pointer to the filePath
  val filePath : BufferedSource = Source.fromFile(fileName)
  // initialize temporary placeholder to hold data