package ass1;
import java.util.ArrayList;
/**
 * This class maintains information on a Hotel
 * such as its name and list of rooms it has.
 * @author z5113067
 *
 * Aggregation relationship : Room
 *
 */
public class Hotel {
	private String hotelname;
	private ArrayList<Room> listOfRooms = new ArrayList<Room>();
	
	/**
	 * Constructor
	 * @param hotelname
	 */
	public Hotel(String hotelname) {
		setHotelname(hotelname);
	}
	
	public String getHotelname() {
		return hotelname;
	}
	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}

	public ArrayList<Room> getListOfRooms() {
		return listOfRooms;
	}

	/**
	 * Adds room to hotel
	 * @param room
	 */
	public void addRoom(Room room) {
		getListOfRooms().add(room);
	}
	
	/**
	 * Searches through rooms in order of declaration for availability
	 * @param user_input
	 * @param users_booking
	 * @param types
	 * @return list of rooms available for a given list of room requests
	 */
	public ArrayList<Room> checkRooms(String[] user_input, Booking users_booking, ArrayList<String> types) {
		ArrayList<Room> rooms = new ArrayList<Room>();
		int singles = getNumber(types, "single");
		int doubles = getNumber(types, "double");
		int triples = getNumber(types, "triple");
			for(Room room : getListOfRooms()) {
				if(room.getType() == 1 && types.contains("single") && singles!=0) {
						if(room.checkAvailability(user_input, users_booking)) {
							if(!rooms.contains(room)) {
								rooms.add(room);
								singles--;
							}
						}
				}else if(room.getType() == 2 && types.contains("double") && doubles!=0) {
							if(room.checkAvailability(user_input, users_booking)) {
								if(!rooms.contains(room)) {
									rooms.add(room);
									doubles--;
								}
							}
				}else if(room.getType() == 3 && types.contains("triple") && triples!=0) {
						if(room.checkAvailability(user_input, users_booking)) {
								if(!rooms.contains(room)) {
									rooms.add(room);
									triples--;
								}
						}
				}
			}
		if(singles != 0 || doubles != 0 || triples != 0) {
			return null;
		}
		return rooms;
	}

	/**
	 * Helper function for checkRooms.
	 * @param types
	 * @param roomtype
	 * @return returns the number of rooms requested of a given type
	 */
	public int getNumber(ArrayList<String> types, String roomtype) {
		int number = 0;
		for(int m = 0; m<types.size();m++) {
			String s = types.get(m);
			if(s.equals(roomtype)) {
				number = Integer.parseInt(types.get(m+1));
				break;
			}
		}
		return number;
	}
	
	/**
	 * 
	 * @return Returns list of bookings of a hotel.
	 */
	public String bookings()  {
		StringBuilder builder = new StringBuilder();
		for(int k = 0; k<getListOfRooms().size(); k++) {
			Room r = getListOfRooms().get(k);
			builder.append(getHotelname() + " " + r.getRoomnumber() + " ");
			if(!r.getListOfBookedDates().isEmpty()) {
				builder.append(r.bookings());
			}if(k!=getListOfRooms().size()-1)
				builder.append(System.lineSeparator());
		}
		return builder.toString();
	}

}
