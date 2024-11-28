// Written by Yusuf 
// And Justin Qiu

// Scala Build Version : 3.3.4
// SBT version : 1.9.9
// JavaSDK required : JDK 21

import scala.io.Source
import java.time.LocalDate

// Define global variables
final val _filePath : String = "./res/hospital_assignment.csv"


// write the code / pseudocode for question 1
// written by Justin Qiu
def Question1(records: List[Hospital]): Unit =
  // step 1: Get the latest record for each state
  // step 1.5 : store record in collection API Map[K,V] apply K =
  // step 2: Aggregate the value for total beds at each state hospitals
  // step 3: print results
  var latestRecordByStates : Map[ String, Records]= Map()
  // utilize collection API to sort data
  latestRecordByStates = records.map { hospital =>
    hospital.state -> hospital.LatestRecord
  }.toMap
  // Find the state with the highest total beds
  val (stateWithHighestBeds, highestRecord) = latestRecordByStates.maxBy:
    case (_, record) => record.totalBed
  println(s"Question 1: $stateWithHighestBeds ||| ${highestRecord.totalBed} total beds")// write the code/ pseudocode for question 2
end Question1

// write code for GCD
// reference : Generated via Chatgpt to simplify Euclidean algorithm
def GCD(int1 : Int, int2 :Int): Int =
  if (int2 == 0) int1 else GCD(int2, int1%int2 )
end GCD

// Write code to calculate Ratio
// reference : Generated via Chatgpt to simplify ratio calculation
def CalcRatio(int1 : Int, int2 : Int): (Int,Int) =
  val Divisor : Int = GCD(int1, int2)
  return (int1 / Divisor, int2 / Divisor)
end CalcRatio

// for question 2
// written by Yusuf
// uses List[] parametric polymorphism collection api
// Justification : Traversal of list to collect beds information from each state, List if more efficient
def Question2(records: List[Hospital]): Unit =
  // init placeholder to summarize
  // ::Total Beds available in all hospitals
  var TotalBedsAvailable : Int = 0
  var TotalBed4Covid : Int = 0

  // fx name is small letters
  for (hospital <- records)
    // traverse each record, access total bed of each hospital at each state
    // from the collection API - list[] - the sequence collection API
    TotalBedsAvailable += hospital.LatestRecord.totalBed
    TotalBed4Covid += hospital.LatestRecord.covidBed
  end for
  // print the results
  // calculate the ratio
  val Ratio : (Int,Int) = CalcRatio(int1 = TotalBedsAvailable, int2 = TotalBed4Covid)
  // display the results
  println(s"Question 2 : Ratio of total Beds available to beds reserved for covid is $Ratio")
end Question2

// Question 3
// written by Yusuf
// uses parametric polymorphism Map[] collection API,
// 
def Question3 (records : List[Hospital]): Unit =
  // call parametric polymorphism collection API for MAP[T,Z] apply
  // init a variable for collection API
  var RecordByStates : Map[ String, List[Records]] = Map()
  // process data required into MAP[state <- Key, Record <- Value]
  RecordByStates = records.map{ hospital => hospital.state -> hospital.record}.toMap
  //println(RecordByStates)
  println("Question 3:\n")
  for ( (state , records) <- RecordByStates)
    // At each state
    var cSuspectedAdmissions : Int = 0
    var cCovidAdmissions : Int = 0
    var cNonCovidAdmissions : Int  =0
    for ( record <- records)
      // for each record in records collection
      // extract the PUi
      cSuspectedAdmissions += record.admitted_Pui
      // extract the Covid admissions
      cCovidAdmissions += record.admitted_Covid
      // extract the non-covid admissions : formula Pui - covid admissions
      cNonCovidAdmissions += (record.Admitted_Total - record.admitted_Covid)
    end for
    // calculate the average
    println("======================\n")
    println(s"$state")
    println("Average Suspected admissions :" + (cSuspectedAdmissions.toDouble /records.length).ceil + " individuals")
    println("Average Covid Admissions :"+(cCovidAdmissions.toDouble / records.length).ceil + " individuals")
    println("Average Non Covid Admissions :"+(cNonCovidAdmissions.toDouble / records.length).ceil + " individuals")
  end for
end Question3


// written by Yusuf
def ProcessCSV(filePath: String): List[Hospital] =
  // Create a mutable map to store hospitals dynamically
  // uses parametric polymorphism collection API Map[K,V]
  var hospitals = scala.collection.mutable.Map[String, Hospital]()
  // Read and process the CSV file
  val bufferedSource = Source.fromFile(filePath)
  try
    // uses error exception where issues can be present
    // drop the header
    // process each line
    bufferedSource.getLines().drop(1).foreach : line =>
        // converts
        val cols = line.split(",").map(_.trim).toList
        val state = cols(1)

        // Check if the hospital for the state exists; if not, initialize it
        val hospital = hospitals.getOrElseUpdate(state, Hospital(_state = state))
        // Add the record to the corresponding hospital via AddRecord Function of Hospital class
        hospital.AddRecord(cols)
  finally
    // closes file pointer
    bufferedSource.close()
  // Convert the mutable map values to a list of hospitals
  hospitals.values.toList
end ProcessCSV

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
// run #1 Execution time: 0.4580554 seconds 
// run #2 Execution time: 0.4504188 seconds
// run #3 Execution time: 0.4892105 seconds
// run #4 Execution time: 0.4140576 seconds
// run #5 Execution time: 0.4618636 seconds 
// run #6 Execution time: 0.3834009 seconds
// run #7 Execution time: 0.3295342 seconds
// run #8 Execution time: 0.3670538 seconds
// run #9 Execution time: 0.339628 seconds
// run #10 Execution time: 0.3926234 seconds
// run #11 Execution time: 0.3366705 seconds

// Average : 0.4 Seconds


// Main function to run the program
@main def main(): Unit =
  // Load data from CSV into global hospitalData
  //  val startTime = System.nanoTime()
  // read data
  val data = ProcessCSV(filePath = _filePath)
  // process it
  Question1(data)
  Question2(data)
  Question3(data)
  
  //  val endTime = System.nanoTime()
  //  val duration = (endTime-startTime)/1e9d
  //  println(s"Execution time: ${duration} seconds")
end main