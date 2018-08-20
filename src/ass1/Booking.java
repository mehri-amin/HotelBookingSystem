package ass1;
import java.util.ArrayList;

/**
 * This class maintains information on a booking.
 * @author z5113067
 * 
 * Aggregation relationship : Room
 * Association relationship : Hotel
 *
 */

public class Booking {
	private String name;
	private String month;
	private int start_date;
	private int numdays;
	private Hotel hotel;
	private ArrayList<Room> listOfRooms = new ArrayList<Room>();
	private ArrayList<String> prev = new ArrayList<String>();
	
	/**
	 * Constructor
	 * @param name
	 * @param month
	 * @param start_date
	 * @param numdays
	 */
	public Booking(String name, String month, int start_date, int numdays) {
		setName(name);
		setMonth(month);
		setStart_date(start_date);
		setNumdays(numdays);
	}
	
	/**
	 * 
	 * @return booking's month
	 */
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * 
	 * @return booking's start date
	 */
	public int getStart_date() {
		return start_date;
	}
	
	public void setStart_date(int start_date) {
		this.start_date = start_date;
	}
	/**
	 * 
	 * @return booking holder's name
	 */
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @return number of days of booking
	 */
	public int getNumdays() {
		return numdays;
	}
	
	public void setNumdays(int numdays) {
		this.numdays = numdays;
	}

	/**
	 * 
	 * @return list of rooms in booking
	 */
	public ArrayList<Room> getListOfRooms() {
		return listOfRooms;
	}

	
	/**
	 * Adds room to list of rooms in booking
	 * @param room
	 */
	public void addRoom(Room room) {
		getListOfRooms().add(room);
	}

	/**
	 * 
	 * @return name of hotel belonging to booking
	 */
	public String getHotelname() {
		return hotel.getHotelname();
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	/**
	 * Prints every room in booking
	 * @return string of rooms
	 */
	public String printRooms() {
		StringBuilder builder = new StringBuilder();
		for(Room room : getListOfRooms()) {
			builder.append(room.getRoomnumber() +" ");
		}
		return builder.toString();
	}
	
	/**
	 * Stores booked date of each room in booking
	 * @param prev
	 */
	public void storeDates(ArrayList<String> prev){
		for(Room room : getListOfRooms()) {
			for(String dates : room.getListOfBookedDates()) {
				prev.add(dates);
			}
		}
		
		this.prev = prev;
	}
	
	/**
	 * Gets the dates in booking for change method
	 * @return
	 */
	public ArrayList<String> getDates() {
		return prev;
	}
	
	/**
	 * Removes booked dates of each room in booking
	 * @param prev
	 */
	public void removeDates(ArrayList<String> prev) {
		for(int m = getListOfRooms().size()-1; m>=0; m--) {
			Room room = getListOfRooms().get(m);
			for(int i = room.getListOfBookedDates().size()-1; i >= 0; i--) {
				String dates = room.getListOfBookedDates().get(i);
				if(prev.contains(dates)) {
					room.getListOfBookedDates().remove(i);
				}
			} 
			if(room.getListOfBookedDates().isEmpty()) {
				getListOfRooms().remove(m);
			}
		}
	}
	

}
