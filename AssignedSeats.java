/**
 * 
 */
package bcpa.backend;

import java.util.Date;

/**
 * 
 *
 */
public class AssignedSeats {
	
	private int seatId;
	private int agentId;
	private Date startDate;
	private Date endDate;
	

	/**
	 * 
	 */
	public AssignedSeats(int seat,int agent, Date start, Date end) {
		this.setSeatId(seat);
		this.setAgentId(agent);
		this.setStartDate(start);
		this.setEndDate(end);
	}


	/**
	 * @return the seatId
	 */
	public int getSeatId() {
		return seatId;
	}


	/**
	 * @param seatId the seatId to set
	 */
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}


	/**
	 * @return the agentId
	 */
	public int getAgentId() {
		return agentId;
	}


	/**
	 * @param agentId the agentId to set
	 */
	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}


	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}


	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}


	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
