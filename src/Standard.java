import java.util.*;
import java.text.*;

class Standard {
    // Default date formatter for input/output
	public static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");

    // Special date formatters for day/month/year/time user input
	public static SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
	public static SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
	public static SimpleDateFormat yearFormat = new SimpleDateFormat("yy");
	public static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

	// For assigning each event with a unique ID
	private static int id;

	// Sorted list to hold event objects
	private TreeSet<Event> list;

	// Today's date
	private Date currentDate;
	
	/*
	Prints the events to the console, sorted by date in ascending order
	 */
	public void print() {
		Iterator<Event> iter = list.iterator();
		
		StringBuilder str = new StringBuilder();
		
		while(iter.hasNext()) {
			str.append(iter.next().toString() + "\n");
		}
		System.out.println(str);
	}

	/*
	For assigning new events with a unique identifier
	 */
	public static int getNewId() {
		return ++id;
	}

	/*
	Decrements the last id issued, if an event was cancelled before it was added to the list
	 */
	public static void cancelId() {
	    id--;
	}

	/*
	Main run method
	 */
	public void run() {
        currentDate = new Date(System.currentTimeMillis());


		Scanner scan = new Scanner(System.in);
		list = new TreeSet<Event>();
		
		createEvent(scan);
		//createEvent(scan);
		
		print();
		
		scan.close();
	}

    /*
    Get user input from the console to create a new event
     */
	public boolean createEvent(Scanner scan) {
        System.out.println("Default date: " + df.format(currentDate));
	    Event event = new Event();

	    try {
	        // Get the event description from the user
	        System.out.print("Enter the event description: ");
            String name = scan.nextLine();

            // Get the date from the user
            SetEventDate(event, scan);

            // Get the location from the user
            System.out.print("Enter the location: ");
            String location = scan.nextLine();

            // Get the time from the user
            SetEventTime(event, scan);

            // Finalize the event object and add it to the list
            event.setName(name);
            event.setLocation(location);
            System.out.print("Please confirm the input is correct:\n" + event.toString() + "\nY/n: ");
            String input = scan.nextLine();

            // Only add the event to the list if the user confirms it is correct
            if(!input.equals("n")) {
                list.add(event);
            } else {
                System.out.println("Canceled adding event.");
                Standard.cancelId();
                return false;
            }
        } catch (NumberFormatException | ParseException g) {
            System.out.println("Error setting date/time");
            Standard.cancelId();
            return false;
        }

	    return true; // Event was added to the list
    }

    /*
    Gets the day, month, and year input from the user and
    sends it to the event. Uses currentDate for defaults if the
    user does not enter a field.
     */
    public void SetEventDate(Event event, Scanner scan) throws NumberFormatException, ParseException{
        int day = 0;
        int month = 0;
        int year = 0;
        String input;

        // Get day from user, or use currrentDate if user input was blank
        System.out.print("Enter the day: ");
        input = scan.nextLine();
        if(input.length() != 0) day = Integer.parseInt(input);
        else day = Integer.parseInt(dayFormat.format(currentDate));

        // Get month from user, or use currentDate if user input was blank
        System.out.print("Enter the month: ");
        input = scan.nextLine();
        if(input.length() != 0) month = Integer.parseInt(input);
        else month = Integer.parseInt(monthFormat.format(currentDate));

        // Get year from user, or use currentDate if user input was blank
        System.out.print("Enter the year: ");
        input = scan.nextLine();
        if(input.length() != 0) year = Integer.parseInt(input);
        else year = Integer.parseInt(yearFormat.format(currentDate));

        // Send the date to the event object
        event.setDate(day, month, year);
    }

    /*
    Gets the hour and minute from the user and sends it to the event.
    Sets event time to null if the user skips a field.
     */
    public void SetEventTime(Event event, Scanner scan) throws NumberFormatException, ParseException{
        int hour = 0;
        int minute = 0;
        String input;

        System.out.print("Enter the hour: ");
        input = scan.nextLine();
        if(input.length() != 0) hour = Integer.parseInt(input);
        else hour = -1;

        System.out.print("Enter the minute: ");
        input = scan.nextLine();
        if(input.length() != 0) minute = Integer.parseInt(input);
        else minute = -1;

        // Only set the time if the user entered data in both hour and minute fields
        if(hour != -1 && minute != -1) {
            event.setTime(hour, minute);
        }
    }

	
	public static void main(String[] args) {
		Standard stan = new Standard();
		id = 0;
		df.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));

		stan.run();
	}
	
}