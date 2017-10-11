import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class UserInput {

    public int getIntGeneric(Scanner scan) {
        String input = scan.nextLine();
        if(input.length() != 0) {
            try {
                int result = Integer.parseInt(input);
                return result;
            } catch(NumberFormatException e) {
                return -1;
            }
        } else {
            return -1;
        }
    }

    public Date getDate(Scanner scan) {
        int day, month, year;

        System.out.print("Enter the day: ");
        day = getIntGeneric(scan);

        System.out.print("Enter the month: ");
        month = getIntGeneric(scan);

        System.out.print("Enter the year: ");
        year = getIntGeneric(scan);

        if(day == -1)  day = Settings.getDay();
        if(month == -1)  month = Settings.getMonth();
        if(year == -1) year = Settings.getYear();

        try {
            return Settings.df.parse(day + "/" + month + "/" + year);
        } catch (ParseException e) {
            return null;
        }
    }

    public Date getTime(Scanner scan) {
        int hour, minute;

        System.out.print("Enter the hour: ");
        hour = getIntGeneric(scan);

        System.out.print("Enter the minute: ");
        minute = getIntGeneric(scan);

        // Only set the time if the user entered data in both hour and minute fields
        if(hour != -1 && minute != -1) {
            try {
                Date time =  Settings.timeFormat.parse(hour + ":" + minute);
                return time;
            } catch (ParseException e) {
                return null;
            }
        } else {
            return null;
        }

    }

    public String getName(Scanner scan) {
        System.out.print("Enter the event name: ");
        String input = scan.nextLine();
        if(input.length() < 1) return "Default Event";
        return input;
    }

    public String getLocation(Scanner scan) {
        System.out.print("Enter the event location: ");
        String input = scan.nextLine();
        return input;
    }

    public int getMenuOption(Scanner scan) {
        System.out.println(menuMessage());
        String input = scan.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private String menuMessage() {
        String str = "What would you like to do?\n";

        str += "[0] View events\n";
        str += "[1] Create a new event\n";
        str += "[2] Modify an existing event\n";
        str += "[3] Delete an event\n";
        str += "[default] Exit\n";

        return str;
    }
}
