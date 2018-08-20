package ass1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import static java.util.Arrays.asList;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The hotel booking system class reads input text files 
 * from user and and processes them through the Responder class
 * to return a response for user.
 * 
 * In the specs it did not clarify whether the booked dates printed
 * is in order of dates. I took the assumption that I could just
 * printed in order of when they were booked. 
 * 
 * @author z5113067
 *
 * Composition relationship : Responder
 *
 */
public class HotelBookingSystem {
	
	/**
	 * Hotel Booking System Commands that must be recognised as they are user requests.
	 */
	private static String COMMAND_HOTEL = "Hotel";
	private static String COMMAND_BOOKING = "Booking"; 
	private static String COMMAND_CHANGE = "Change";
	private static String COMMAND_CANCEL = "Cancel";
	private static String COMMAND_PRINT = "Print";
	
	/**
	 * 
	 * Stores stardard three letter months
	 * Can add an exception error in future if user
	 * inputs incorrect month input but took the assumption
	 * inputs will not be invalid in testing.
	 */
	private static ArrayList<String> months = new ArrayList<String>(asList("Jan", "Feb", "Mar", "Apr", "Jun", "Jul",
			   "Aug", "Sep", "Oct", "Nov", "Dec"));
	
	
	public static void main(String[] args) 
	{
		Responder response = new Responder();
		try
		{
			Scanner userInput = new Scanner(new FileReader(args[0]));
			
			while(userInput.hasNextLine())
			{
				String currentLine = userInput.nextLine();
				int firstSpace = currentLine.indexOf(" ");
		
				if(firstSpace != -1) 
				{
					String user_request = currentLine.substring(0, firstSpace);
					String user_inputs[] = currentLine.substring(firstSpace + 1).split(" ");
					String systemResponse = new String();
					
					if (user_request.equals(COMMAND_HOTEL))
					{
						systemResponse = response.createHotel(user_inputs);
					}
					else if (user_request.equals(COMMAND_BOOKING))
					{
						if(months.contains(user_inputs[1])) {
							ArrayList<String> types = getTypeRequests(user_inputs);
							systemResponse = response.booking(user_inputs, types);
						}else {
							systemResponse = "Invalid month input";
						}
					}
					else if (user_request.equals(COMMAND_CHANGE))
					{
						if(months.contains(user_inputs[1])) {
							ArrayList<String> types = getTypeRequests(user_inputs);
							systemResponse = response.change(user_inputs, types);
						}else {
							systemResponse = "Invalid month input";
						}
					}
					else if (user_request.equals(COMMAND_CANCEL))
					{
						systemResponse = response.cancel(user_inputs);
					}
					else if (user_request.equals(COMMAND_PRINT))
					{
						systemResponse = response.print(user_inputs);
					}

					if(!systemResponse.isEmpty())
					{
						printToSystem(systemResponse);
					}
				}
			}
			userInput.close();
		}
		catch(FileNotFoundException e)
		{
			System.err.println("FileNotFoundException: " + e.getMessage());
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.err.println("Please add a single parameter that is an input file");
		}
		
		
	}
	/**
	 * Prints a given non-empty string to System output. 
	 * @param printout String value of output
	 */
	private static void printToSystem(String output)
	{
		if(!output.equals(""))
		{
			System.out.println(output);
		}
	}
	
	/**
	 * Reads the types {single, double, triple} requested and
	 * how many of each.
	 * @param users_input
	 * @return array of types + number of rooms of type
	 */
	public static ArrayList<String> getTypeRequests(String[] users_input) {
		ArrayList<String> types = new ArrayList<String>();
		if(users_input.length >= 6) {
			types.add(users_input[4]);
			types.add(users_input[5]);
		}
		if(users_input.length >= 8) {
			types.add(users_input[6]);
			types.add(users_input[7]);
		}
		if(users_input.length == 10) {
			types.add(users_input[8]);
			types.add(users_input[9]);
		}
		return types;
	}
}
