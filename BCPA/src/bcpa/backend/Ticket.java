package bcpa.backend;

import java.util.List;

public class Ticket {
    private Seat seat;
    private double price;
    private double discount;

    public Ticket(Event event, Show show, Seat seat, double price, double discount) {
        this.setSeat(seat);
        this.setPrice(price);
        this.setDiscount(discount);
    }

	public double getTotalPrice() {
        return price - (price * discount);
    }

	/**
	 * @return the seat
	 */
	public Seat getSeat() {
		return seat;
	}

	/**
	 * @param seat the seat to set
	 */
	public void setSeat(Seat seat) {
		this.seat = seat;
	}

    public Double getPrice() {
    	return this.price;
    }
    
    public void setPrice(double amount) {
    	this.price = amount;
    }
    
    public Double getDiscount() {
    	return this.discount;
    }
    
    public void setDiscount(double discount) {
    	this.discount = discount;
    }
}