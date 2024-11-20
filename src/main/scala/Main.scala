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
  for (line <- filePath.getLines()) 
    // for each line
    // split the information by each column// comma
    val cols : List = line.split(",").map(_.trim).toList
    fileData = fileData :+ cols
    //  increase counter - to count number of records
    count += 1 
  end for
  filePath.close()
end readCSV