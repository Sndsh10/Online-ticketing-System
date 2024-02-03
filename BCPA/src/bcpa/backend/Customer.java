package bcpa.backend;

import java.text.ParseException;
import java.util.ArrayList;

import bcpa.database.DataRetrieval;


public class Customer extends User {
	
	
    public Customer(String username, String password) {
        super(username, password);
    }

    
    public static ArrayList<Event> viewUpcomingEvents() {
    	return DataRetrieval.getUpcomingEvents();
    }
    
    public static ArrayList<Show> viewShowsByDate(int event) throws ParseException {
    	return DataRetrieval.getShowsForEvent(event);
    }
    
    public static void viewSeatingChart() {
    	
    }
    
    public static void updateProfile() {
    	
    }
}