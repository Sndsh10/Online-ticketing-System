package bcpa.backend;

public class Promotion {
    private double adultPrice;
    private double studentPrice;
    private double childPrice;
    private double seniorPrice;
    private int showId;

    public Promotion(double adultPrice, double studentPrice, double childPrice, double seniorPrice, int show) {
        this.setAdultPrice(adultPrice);
        this.setStudentPrice(studentPrice);
        this.setChildPrice(childPrice);
        this.setSeniorPrice(seniorPrice);
        this.setShowId(show);
    }
    
    // Getters and setters

	/**
	 * @return the adultPrice
	 */
	public double getAdultPrice() {
		return adultPrice;
	}

	/**
	 * @param adultPrice the adultPrice to set
	 */
	public void setAdultPrice(double adultPrice) {
		this.adultPrice = adultPrice;
	}

	/**
	 * @return the studentPrice
	 */
	public double getStudentPrice() {
		return studentPrice;
	}

	/**
	 * @param studentPrice the studentPrice to set
	 */
	public void setStudentPrice(double studentPrice) {
		this.studentPrice = studentPrice;
	}

	/**
	 * @return the childPrice
	 */
	public double getChildPrice() {
		return childPrice;
	}

	/**
	 * @param childPrice the childPrice to set
	 */
	public void setChildPrice(double childPrice) {
		this.childPrice = childPrice;
	}

	/**
	 * @return the seniorPrice
	 */
	public double getSeniorPrice() {
		return seniorPrice;
	}

	/**
	 * @param seniorPrice the seniorPrice to set
	 */
	public void setSeniorPrice(double seniorPrice) {
		this.seniorPrice = seniorPrice;
	}

	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	} 
    
}