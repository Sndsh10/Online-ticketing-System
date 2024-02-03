package bcpa.backend;

import java.util.ArrayList;

import bcpa.database.DataRetrieval;

public class Seat {
	private int seatIndex;
	private int seatId;
	private int seatingChartId;
    private String seatType;
    private String status;
    private double price;
    
    public Seat() {
    	
    }

    public Seat(int seatingId, String type, String status, double price) {
    	this.setSeatingChartId(seatingId);
        this.setSeatType(type);
        this.setStatus(status);
        this.setPrice(price);
    }
    
    public Seat(int seat, int chartId, String type, String status, double cost) {
    	this.setSeatId(seat);
    	this.setSeatingChartId(chartId);
    	this.setSeatType(type);
    	this.setStatus(status);
    	this.setPrice(cost);
    }
    
    
    // Getters and setters

	

	public int getSeatIndex() {
		return seatIndex;
	}

	public void setSeatIndex(int seatIndex) {
		this.seatIndex = seatIndex;
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

	public int getSeatingChartId() {
		return seatingChartId;
	}

	public void setSeatingChartId(int seatingChartId) {
		this.seatingChartId = seatingChartId;
	}

	/**
	 * @return the seatType
	 */
	public String getSeatType() {
		return seatType;
	}

	/**
	 * @param seatType the seatType to set
	 */
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public static ArrayList<Seat> getSeats(int chartId){
		return DataRetrieval.getSeatsBySeatingChartId(chartId);
	}

}