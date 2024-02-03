package bcpa.backend;

public class TicketAgent extends User {
    
    public TicketAgent(String username, String password) {
    	super(username, password);
    }

    public TicketAgent(String username, String password, int contract) {
        super(username, password);
    }
	
}