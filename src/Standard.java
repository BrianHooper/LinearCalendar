import java.util.*;
import java.text.*;

class Standard {
    // Default date formatter for input/output
	public static final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");

    // Special date formatter's for day/month/year/time user input
	public static final SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
	public static final SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
	public static final SimpleDateFormat yearFormat = new SimpleDateFormat("yy");
	public static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

	// For assigning each event with a unique ID
	private static int id;

	// Sorted list to hold event objects
	private TreeSet<Event> list;

	// Today's date
	private Date currentDate;
	
	/*
	Prints the events to the console, sorted by date in ascending order
	 */
	private void print() {
		Iterator<Event> iter = list.iterator();
		
		StringBuilder str = new StringBuilder();
		
		while(iter.hasNext()) {
			str.append(iter.next().toString());
			str.append("\n");
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
		list = new TreeSet<>();

		boolean loopMenu = true;
		while(loopMenu) {
            System.out.println(generateMenu());
            int userInput = -1;

            try {
                String input = scan.nextLine();
                if(input.length() == 0) loopMenu = false;
                else {
                    userInput = Integer.parseInt(input);
                }

                switch (userInput) {
                    case 0:
                        print();
                        break;
                    case 1:
                        createEvent(scan);
                        break;
                    case 2:
                        // ToDo
                        break;
                    case 3:
                        // Todo
                        break;
                    default:
                        break;
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Error reading user input, exiting.");
            }
        }
		
		scan.close();
	}

	public String generateMenu() {
	    String str = "What would you like to do?\n";

	    str += "[0] View events\n";
	    str += "[1] Create a new event\n";
	    str += "[2] Modify an existing event\n";
	    str += "[3] Delete an event\n";
	    str += "[default] Exit\n";

	    return str;
    }

    /*
    Get user input from the console to create a new event
     */
	public void createEvent(Scanner scan) {
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
            }
        } catch (NumberFormatException | ParseException g) {
            System.out.println("Error setting date/time");
            Standard.cancelId();
        }
    }

    /*
    Gets the day, month, and year input from the user and
    sends it to the event. Uses currentDate for defaults if the
    user does not enter a field.
     */
    public void SetEventDate(Event event, Scanner scan) throws NumberFormatException, ParseException{
        int day, month, year;
        String input;

        // Get day from user, or use currentDate if user input was blank
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
        int hour, minute;
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