/**
 * 
 */
package bcpa.backend;

import java.util.Date;

/**
 * 
 *
 */
public class AgentTransaction {
	
	private int assignedSeat;
	private Date soldDate;
	private int seatsSold;
	private double amount;

	/**
	 * 
	 */
	public AgentTransaction(int seat, Date date, int seatsSold, double cost) {
		this.setAssignedSeat(seat);
		this.setSoldDate(date);
		this.setSeatsSold(seatsSold);
		this.setAmount(cost);
	}

	/**
	 * @return the assignedSeat
	 */
	public int getAssignedSeat() {
		return assignedSeat;
	}

	/**
	 * @param assignedSeat the assignedSeat to set
	 */
	public void setAssignedSeat(int assignedSeat) {
		this.assignedSeat = assignedSeat;
	}

	/**
	 * @return the sold
	 */
	public Date getSoldDate() {
		return soldDate;
	}

	/**
	 * @param sold the sold to set
	 */
	public void setSoldDate(Date sold) {
		this.soldDate = sold;
	}

	/**
	 * @return the seatsSold
	 */
	public int getSeatsSold() {
		return seatsSold;
	}

	/**
	 * @param seatsSold the seatsSold to set
	 */
	public void setSeatsSold(int seatsSold) {
		this.seatsSold = seatsSold;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

}
