import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

public class EventList {


    // Sorted list to hold event objects
    private TreeSet<Event> list;

    public EventList() {
        Settings.update();
        list = new TreeSet<>();
    }

    /*
    Prints the events to the console, sorted by date in ascending order
     */
    public void print() {
        if(list.size() == 0) {
            System.out.println("There are no events.");
            return;
        }

        Iterator<Event> iter = list.iterator();

        StringBuilder str = new StringBuilder();

        while(iter.hasNext()) {
            str.append(iter.next().toString());
            str.append("\n");
        }
        System.out.println(str);
    }

    public void createEvent(String name, Date date, Date time, String location) {
        Event e = new Event();
        e.setName(name);
        e.setDate(date);
        e.setTime(time);
        e.setLocation(location);
        list.add(e);
    }




}
