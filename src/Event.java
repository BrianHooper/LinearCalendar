import java.util.Date;
import java.text.ParseException;

@SuppressWarnings("CanBeFinal")

class Event implements Comparable<Event> {
	private Date date, time;
	private String name, location;
	private int id;

	
	public Event() {
		this.id = Standard.getNewId();
	}

	/*
	Formats and sets the date
	 */
	public void setDate(int day, int month, int year) throws ParseException{
		String dateStr = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);
		this.date = Standard.df.parse(dateStr);
	}

	/*
	Returns a string representing the date, or returns "" if date is null
	 */
	public String getDate() {
		if(this.date == null) return "";
		else return Standard.df.format(date);
	}

	/*
	Returns the date object
	 */
	public Date getDateObj() {
		return this.date;
	}

	/*
	Sets the name field
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	Returns the name field formatted to the number of characters specified in nameLength
	 */
	public String getShortName() {
        int nameLength = 30;
		String str = name;
		
		if(str.length() < nameLength) {
            str = String.format("%1$-" + nameLength + "s", str);
		} else {
			str = str.substring(0,nameLength - 4) + "...";
		}
		
		return str;
	}

	/*
	Formats and sets the time object
	 */
	public void setTime(int hour, int minute) throws ParseException{
	    time = Standard.timeFormat.parse(hour + ":" + minute);
	}

	/*
	Returns a string representing the event time, or "" if time is null
	 */
	public String getTime() {
		if(time == null) return "";
		else return Standard.timeFormat.format(time);
	}

	/*
	Returns the time object
	 */
	public Date getTimeObj() {
		return this.time;
	}

	/*
	Returns the event ID as a String with leading zeros
	 */
	public String getIdString() {
		return String.format("%04d", id);
	}

	/*
	Sets the location field
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/*
	Returns the location field
	 */
	public String getLocation() {
		return this.location;
	}

	@Override
	public String toString() {
		String str = "[" + getIdString() + "]   " + getDate() + "   " + getShortName() + "   ";
		if(time != null) {
			str += getTime() + "   ";
		} else {
			str += "\t";
		}
		if(location != null) str += getLocation();
		return str;
	}
	
	@Override
	public int compareTo(Event e) {
		int dateCompare = date.compareTo(e.getDateObj());
		if(dateCompare == 0) {
			if(this.time != null) {
				if(e.getTimeObj() != null) {
					return this.time.compareTo(e.getTimeObj());
				} else return 1;
			} else if(e.getTimeObj() != null) {
				return -1;
			}
		} 
		return dateCompare;
	}
}