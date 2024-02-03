package bcpa.backend;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Event {
    private String name;
    private Date startDate;
    private Date endingDate;
    private String eventStatus;

    public Event(String event, String startDate, String endDate, String status)  throws ParseException {
        this.name = event;
        this.setStartDate(startDate);
        this.setEndingDate(endDate);
        this.setEventStatus(status);
    }
    
    public Event(String event, Date startDate, Date endDate) {
    	this.name = event;
    	this.startDate = startDate;
    	this.endingDate = endDate;
    }
    
    // Getters and setters

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 * @throws ParseException 
	 */
	public void setStartDate(String startDate) throws ParseException {
		this.startDate = parseDate(startDate);
	}

	/**
	 * @return the endingDate
	 */
	public Date getEndingDate() {
		return endingDate;
	}

	/**
	 * @param endingDate the endingDate to set
	 * @throws ParseException 
	 */
	public void setEndingDate(String endingDate) throws ParseException {
		this.endingDate = parseDate(endingDate);
	}
	
	public String getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}

	private Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(dateString);
    }

}
