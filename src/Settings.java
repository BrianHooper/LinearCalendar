import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Settings {
    // Default date formatter for input/output
    public static final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");

    // Special date formatter's for day/month/year/time user input
    public static final SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
    public static final SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    public static final SimpleDateFormat yearFormat = new SimpleDateFormat("yy");
    public static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    // For assigning each event with a unique ID
    private static int id;

    private static final Date currentDate = new Date(System.currentTimeMillis());

    public static void update() {
        resetId();
        df.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
    }

    public static void resetId() {
        id = 0;
    }

    public static void setId(int identifier) {
        id = identifier;
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

    public Date getDate() {
        return currentDate;
    }

    public String getDateString() {
        return Settings.df.format(currentDate);
    }

    public static int getDay() {
        try {
            return Integer.parseInt(dayFormat.format(currentDate));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    public static int getMonth() {
        try {
            return Integer.parseInt(monthFormat.format(currentDate));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    public static int getYear() {
        try {
            return Integer.parseInt(yearFormat.format(currentDate));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

}
