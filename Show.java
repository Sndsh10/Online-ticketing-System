package bcpa.backend;

import bcpa.database.DataRetrieval;


public class Show {
	private int eventId;
	private String showName;
    private String showDate;
    private int maxSeatsPerCustomer;
    
    public Show() {
    	
    }
    

    public Show(int event, String showName, String showDate, int maxSeatsPerCustomer) {
        this.setEventId(event);
        this.setShowName(showName);
        this.setDate(showDate);
        this.setMaxSeatsPerCustomer(maxSeatsPerCustomer);
    }

    // Getters and setters
    
    

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return this.showDate;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date){
		this.showDate = date;
	}

	public int getMaxSeatsPerCustomer() {
        return maxSeatsPerCustomer;
    }

    public void setMaxSeatsPerCustomer(int maxSeatsPerCustomer) {
        this.maxSeatsPerCustomer = maxSeatsPerCustomer;
        //System.out.println("Maximum seats per customer set to " + maxSeatsPerCustomer + ".");
    }
    
    public static int getShowId(String show) {
    	return DataRetrieval.getShowId(show);
    }
}
