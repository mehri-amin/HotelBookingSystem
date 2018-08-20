package ass1;

import java.util.ArrayList;


/**
 * Class that maintains details of a room
 * such as its roomnumber, type: single or double,
 * and the list of bookings it has.
 * 
 * << uses >> : Booking
 * Composition relationship : Hotel
 * @author z5113067
 *
 */
public class Room{
	private int roomnumber;
	private int type;
	private ArrayList<String> listOfBookedDates = new ArrayList<String>();
	
	/**
	 * Constructor
	 * @param roomnumber
	 * @param type
	 */
	public Room(int roomnumber, int type) {
		setRoomnumber(roomnumber);
		setType(type);
	}
	
	public int getRoomnumber() {
		return roomnumber;
	}
	public void setRoomnumber(int roomnumber) {
		this.roomnumber = roomnumber;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	public ArrayList<String> getListOfBookedDates() {
		return listOfBookedDates;
	}

	/**
	 * Adds a date to room's list of booked dates
	 * @param date
	 */
	public void addDate(String date) {
		getListOfBookedDates().add(date);
	}

	/**
	 * To print out just the room's number.
	 */
	@Override
	public String toString() {
		return String.valueOf(getRoomnumber());
	}
	
	/**
	 * Checks if a room is available for a certain date.
	 * @param user_input
	 * @param users_booking
	 * @return true if date is available else false
	 */
	public boolean checkAvailability(String[] user_input, Booking users_booking) {
		String month = user_input[1];
		int date = Integer.parseInt(user_input[2]);
		int numdays = Integer.parseInt(user_input[3]);
		String getDate = month + " " + date;
		
		if(!getListOfBookedDates().contains(getDate) || users_booking.getDates().contains(getDate)) {
			for(int i = 0; i < numdays; i++) {
				int newDate = date + i;
				getDate = month + " " + newDate;
				if(getListOfBookedDates().contains(getDate) && !users_booking.getDates().contains(getDate)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Adds all dates to room for a booking.
	 * @param booking
	 */
	public void addAllDates(Booking booking) {
		
		String month = booking.getMonth(); 
		
		int date = booking.getStart_date(); 
		int numdays = booking.getNumdays();
		String getDate = month + " " + date;
		
		for(int i =0; i < numdays; i++) {
			int newDate = date + i;
			getDate = month + " " + newDate;
			addDate(getDate);
		}
		addDate(String.valueOf(numdays));
	}
	
	/**
	 * Returns list of bookings a room has
	 * Format: Date + number of days of booking
	 * @return
	 */
	public String bookings() {
		StringBuilder builder = new StringBuilder();

		for(int i = getListOfBookedDates().size()-1; i>0; i--) {
			
			int numdays = Integer.parseInt(getListOfBookedDates().get(i));
			String b = getListOfBookedDates().get(i-numdays) + " " + numdays + " ";
			builder.append(b);
			i-=numdays;
		}
		return builder.toString();
	}
	
}
