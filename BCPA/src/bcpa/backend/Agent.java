package bcpa.backend;

public class Agent extends User {
    private Contract contract;

    public Agent(String username, String password, Contract contract) {
        super(username, password);
        this.setContract(contract);
    }

	/**
	 * @return the contract
	 */
	public Contract getContract() {
		return contract;
	}

	/**
	 * @param contract the contract to set
	 */
	public void setContract(Contract contract) {
		this.contract = contract;
	}

    // Additional methods specific to Ticket Agent
	
	public void sellTickets(Customer customer, Show show) {
        // Assume that selectedSeats is a valid list of available seats
        Transaction transaction = new Transaction(show, customer);

        transaction.completeTransaction();
    }
	
}