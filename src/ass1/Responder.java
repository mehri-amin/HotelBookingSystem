package ass1;
import java.util.ArrayList;

/**
 * Responder is the class that maintains
 * a list of hotels and bookings which helps to check 
 * validity of user requests so it can then proceed 
 * to either add, change, cancel or print bookings and hotels.
 * 
 * Aggregation relationships : Hotel, Booking
 * 
 * (Would've preferred to name this Hotel Booking System
 * but system would not accept my submission if main method was not
 * in the class called Hotel Booking System)
 * 
 * @author z5113067
 *
 */

public class Responder {
	
	private ArrayList<Hotel> listOfHotels = new ArrayList<Hotel>();
	private ArrayList<Booking> listOfBookings = new ArrayList<Booking>();
	
	/**
	 * Constructor used in HotelBookingSystem class.
	 */
	public Responder() {
		
	}

	/**
	 * Get list of Hotels from System
	 * @return listOfHotels
	 */
	public ArrayList<Hotel> getListOfHotels() {
		return listOfHotels;
	}
	
	/**
	 * Get list of Bookings from System
	 * @return listOfBookings
	 */
	public ArrayList<Booking> getListOfBookings() {
		return listOfBookings;
	}

	/**
	 * Checks if hotel already exists, 
	 * if so, it just adds new room to hotel, 
	 * else it creates a new hotel and adds to the system.
	 * @param user_input
	 * @return empty string
	 */
	public String createHotel(String[] user_input) {
		String hotelname = user_input[0];
		int roomnumber = Integer.parseInt(user_input[1]);
		int capacity = Integer.parseInt(user_input[2]);

		Hotel hotel = getHotel(hotelname);
		if(hotel != null) {
				Room newRoom = new Room(roomnumber, capacity);
				hotel.addRoom(newRoom);
				return "";
		}

		Hotel newHotel = new Hotel(hotelname);
		Room newRoom = new Room(roomnumber, capacity);
		newHotel.addRoom(newRoom);
		getListOfHotels().add(newHotel);
		return "";		
	}
	
	/**
	 * Checks whether there is availability for user booking request
	 * if there is, proceeds to book.
	 * @param user_input
	 * @param users_booking
	 * @param types
	 * @return whether rooms are available
	 */
	public boolean check(String[] user_input, Booking users_booking, ArrayList<String> types) {
		for(Hotel hotel : getListOfHotels()) {
					ArrayList<Room> availableRooms = new ArrayList<Room>();
					availableRooms = hotel.checkRooms(user_input, users_booking, types);
					if(availableRooms != null) {
						book(users_booking, hotel, availableRooms);
						return true;
					}	
		}
		return false;
	}

	/**
	 * Proceeds to book valid user booking requests.
	 * @param users_booking
	 * @param hotel
	 * @param room
	 * @param room2
	 */
	public void book(Booking users_booking, Hotel hotel, ArrayList<Room> rooms) {
		users_booking.setHotel(hotel);
		for(int i = 0; i<rooms.size(); i++) {
			Room room = rooms.get(i);
			if(room != null) {
				room.addAllDates(users_booking);
				if(!users_booking.getListOfRooms().contains(room))
				users_booking.addRoom(room);
			}
		}
	}
	
	/**
	 * If valid user request then booking to system
	 * else tells user booking is rejected.
	 * @param users_input
	 * @param types
	 * @return string about booking
	 */
	public String booking(String[] users_input, ArrayList<String> types) {
		String name = users_input[0];
		String month = users_input[1];
		int start_date = Integer.parseInt(users_input[2]);
		int numdays = Integer.parseInt(users_input[3]);
	
		Booking newBook = new Booking(name, month, start_date, numdays);
		
		if(check(users_input, newBook, types)) {
			getListOfBookings().add(newBook);
			return "Booking " + newBook.getName() + " " + newBook.getHotelname() + " " + newBook.printRooms();
		}
		return "Booking rejected";
	}
	
	/**
	 * Attempts to find booking,
	 * if booking found, checks if new request is valid.
	 * If valid, creates new booking and cancels previous booking.
	 * @param users_input
	 * @param types
	 * @return string about change booking
	 */
	public String change(String[] users_input, ArrayList<String> types) {
		ArrayList<String> previous_dates = new ArrayList<String>();
		String name = users_input[0];
		Booking users_booking = findBooking(name);
		if(users_booking != null) {
			users_booking.storeDates(previous_dates);
			boolean validRequest = check(users_input, users_booking, types);
			if(validRequest) {
				users_booking.removeDates(previous_dates);
				cancel(users_input);
				String resp = booking(users_input, types);
				return resp.replaceAll("Booking", "Change");
			}
		}
		return "Change rejected";
	}

	/**
	 * Finds booking in system
	 * if returns null, means no booking.
	 * @param name
	 * @return booking
	 */
	public Booking findBooking(String name) {
		Booking booking = null; 
		for(Booking b : getListOfBookings()) {
			if(b.getName().equals(name)) {
				booking = b;
			}
		}
		return booking;
	}
	
	/**
	 * Cancels booking if valid request, else rejects request.
	 * Did not use findBooking method since I also want to remove
	 * it from systems list of bookings.
	 * @param users_input
	 * @return string about cancel request
	 */
	public String cancel(String[] users_input) {
		String name = users_input[0];
		for(int x = 0; x<getListOfBookings().size(); x++) {
			Booking b = getListOfBookings().get(x);
			if(b.getName().equals(name)) {
				for(int i =0; i<b.getListOfRooms().size(); i++) {
					b.getListOfRooms().remove(i);
				}
				getListOfBookings().remove(x);
				return "Cancel "+ name;
			}
			}
		return "Cancel rejected";
	}
	
	/**
	 * Prints all bookings from requested hotel
	 * @param users_input
	 * @return string
	 * 
	 */
	public String print(String[] users_input){
		String hotelname = users_input[0];
		Hotel hotel = getHotel(hotelname);
		if(hotel != null) {
				return hotel.bookings();
				
		}
		return "";
	}
	
	public Hotel getHotel(String hotelname) {
		for(Hotel hotel : getListOfHotels()) {
			if(hotel.getHotelname().equals(hotelname)) {
				return hotel;
			}
		}
		return null;
	}
}
