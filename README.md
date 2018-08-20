# HotelBookingSystem
## About Project
Implemented a prototype system that could serve as the "back-end" of a hotel reservation system. Java and Object-Oriented Design experience.

Customers can make, change and cancel bookings. A hotel consists of a set of numbered rooms: each room is either a single, double or triple room (so has capacity 1, 2 or 3). A booking is made under a name and consists of one or more room requests for a number of rooms of a certain type (e.g. two single rooms and one double room) for a period of time given by an arrival date and a number of nights (assume all bookings are for 2018). A request is either granted in full or is completely rejected by the system (a request cannot be partially fulfilled).

## User Commands in text files
  **Hotel \<hotelname\> <\roomnumber\> <\capacity\>**
  
          # specify that hotel with <hotelname> has a room with number <roomnumber> that has the size <capacity>
          
  **Booking <\name\> <\month\> <\date\> <\numdays\> <\type1\> <\number1\> <\type2\> <\number2\> . . .**
  
          # booking under <name> starting on <month> and <date> for <numdays> of <number1> rooms of <type1>, etc.
          
  **Change <\name\> <\month\> <\date\> <\numdays\> <\type1\> <\number1\> <\type2\> <\number2\> . . .**
  
          # change booking under <name> to start on <month> and <date> for <numdays> of <number1> rooms of <type1>, etc.
          
  **Cancel <\name\>**
  
          # cancel booking under <name> (if it exists) and free up rooms
          
 **Print <\hotelname\>**
  
          # print occupancy of each room in the hotel <hotelname>

## Implementation detailed
Booking requests and changes are fufilled as follows: <br>
Each hotel is checked (in order of definition in the input file) to determine whether every room request can be satisfied, and if so, the first available rooms (again in order of definition in the input) are assigned to the booking request. 

The output should list rooms assigned to the booking in order of the declarations at the start of the input file. It is assumed that each booking request (and similarly for change requests) has at most one room request for a room of a given type (e.g. does not ask for double rooms more than once). Does not try to fulfil requests by allocating double rooms when a single is requested, or by reassigning rooms to different bookings to create vacancies, etc. For printing, output the occupancy of all rooms at the specified hotel in order of room declarations, then date.

#### The output corresponding to input.txt is as follows:<br>
Booking Aarthi Surfers 101 103 </br>
Booking Rob Surfers 102</br>
Booking Stephanie Burleigh 101</br>
Change Aarthi Surfers 103</br>
Booking Gary Surfers 103</br>
Cancel Stephanie</br>
Booking rejected</br>
Surfers 101</br>
Surfers 102 Jan 24 4 </br>
Surfers 103 Jan 25 2 Jan 27 3 </br>

### Notes: 
Implementation does not print booked rooms of each room in order of dates, but of order of when the date was booked.
**UML Diagram is included.**
