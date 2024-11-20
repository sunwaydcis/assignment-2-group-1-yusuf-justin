// Information : file holds class information to structure the record data
// Created by Yusuf & Justin - November 20th 2024


class Hospital(_state : String):
  // constructor only requires State as all hospital data is summed into state
  val State : String = _state
  // have one collection data structure to store all records
  // to be fixed later
  var Record : List[] = List()

  // Implement an addRecordByState method
  // def addRecord(_unfilteredData : List[List[String]]) : List[] =

end Hospital

// record class to hold each row data
// class Record():