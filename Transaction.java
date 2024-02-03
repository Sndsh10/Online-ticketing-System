package bcpa.backend;

import java.util.Date;


public class Transaction {
    private int customerId;
    private int showId;
    private int purchasedSeats;
    private double totalPrice;
    private Date transactionDate;

    public Transaction(int customer, int show, int seats, double cost, Date date) {
        this.setCustomerId(customer);
        this.setShowId(show);
        this.setPurchasedSeats(seats);
        this.setTotalPrice(cost);
        this.setTransactionDate(date);
    }

	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the showId
	 */
	public int getShowId() {
		return showId;
	}

	/**
	 * @param showId the showId to set
	 */
	public void setShowId(int showId) {
		this.showId = showId;
	}

	/**
	 * @return the purchasedSeats
	 */
	public int getPurchasedSeats() {
		return purchasedSeats;
	}

	/**
	 * @param purchasedSeats the purchasedSeats to set
	 */
	public void setPurchasedSeats(int purchasedSeats) {
		this.purchasedSeats = purchasedSeats;
	}

	/**
	 * @return the totalPrice
	 */
	public double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * @return the transactionDate
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}

	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

    
}

