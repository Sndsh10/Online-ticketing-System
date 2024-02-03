package bcpa.backend;

/**
 * 
 */

public class Contract {
	private int agentId;
    private double commissionPercentage;
    private String terms;

    public Contract(int agentId, double commissionPercentage, String terms) {
        this.setCommissions(commissionPercentage);
        this.setTerms(terms);
    }
    
    // Getters and setters

	public int getAgentId() {
		return agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public double getCommissionPercentage() {
		return commissionPercentage;
	}

	public void setCommissionPercentage(double commissionPercentage) {
		this.commissionPercentage = commissionPercentage;
	}

	/**
	 * @return the commissions
	 */
	public double getCommissions() {
		return commissionPercentage;
	}

	/**
	 * @param commissions the commissions to set
	 */
	public void setCommissions(double commissions) {
		this.commissionPercentage = commissions;
	}

	/**
	 * @return the terms
	 */
	public String getTerms() {
		return terms;
	}

	/**
	 * @param terms the terms to set
	 */
	public void setTerms(String terms) {
		this.terms = terms;
	}

}
