package bcpa.backend;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import bcpa.database.DatabaseDAO;
import bcpa.database.UpdateTablesDAO;


public class VenueManager extends User {
	
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
    public VenueManager(String username, String password) {
        super(username, password);
    }


    // Additional methods specific to Venue Manager
    
    public static void addEvent(String eventName, String startDate, String endDate, String status) throws ParseException {
        Event newEvent = new Event(eventName, startDate, endDate, status);
        DatabaseDAO.insertEvent(newEvent.getName(), dateFormat.format(newEvent.getStartDate()), dateFormat.format(newEvent.getEndingDate()),status);
        //System.out.println("Event '" + eventName + "' added successfully.");
    }

    public static void addShow(int eventId, String showName, String showDate, int maxSeatsPerCustomer){
    	Show newShow = new Show(eventId, showName, showDate, maxSeatsPerCustomer);
    	DatabaseDAO.insertShows(newShow.getEventId(), newShow.getShowName(), newShow.getDate(), newShow.getMaxSeatsPerCustomer());
            
        
    }
    
    public static void cancelEvent(String eventName, String status) {
    	UpdateTablesDAO.cancelEvent(eventName, status);
    }

    public static void rescheduleEvent(String eventName, String newStartDate, String newStatus, String newEndDate){
        UpdateTablesDAO.rescheduleEvent(eventName, newStartDate, newStatus, newEndDate);
    }

    public static void updateMaxSeatsPerCustomer(String show, int newMaxSeats) {
        UpdateTablesDAO.updateSeatsPerCustomer(show, newMaxSeats);
    }  
}
