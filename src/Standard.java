import java.util.*;

class Standard {
	/*
	Main run method
	 */
	public void run() {
		Scanner scan = new Scanner(System.in);

		UserInput ui = new UserInput();
        EventList list = new EventList();
        int menuChoice;

        do {
            menuChoice = ui.getMenuOption(scan);
            switch (menuChoice) {
                case 0: list.print();
                    break;
                case 1: list.createEvent(ui.getName(scan), ui.getDate(scan), ui.getTime(scan), ui.getLocation(scan));
                    break;
                case 2: // ToDo
                    break;
                case 3: // Todo
                    break;
                default: menuChoice = -1;
                    break;
            }
        } while(menuChoice != -1);

		scan.close();
	}

	public static void main(String[] args) {
		Standard stan = new Standard();
		stan.run();
	}
	
}