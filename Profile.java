package bcpa.backend;

public class Profile {
	private int customerId;
    private String streetAddress;
    private String city;
    private String postalCode;
    
    public Profile() {
    	
    }

    public Profile(int customerId, String streetAddress, String city, String postalCode) {
        this.setCustomerId(customerId);
        this.setStreetAddress(streetAddress);
        this.setCity(city);
        this.setPostalCode(postalCode);
    }

    // Getters and setters
    
    public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}
