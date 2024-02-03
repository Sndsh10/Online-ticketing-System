package bcpa.database;

public class TestDatabase {

	public static void main(String[] args) {
		final String JDBC_URL = "jdbc:mysql://localhost:3306/";
		final String DATABASE_NAME = "bcpa_database";
	    final String USER = System.getenv("MYSQL_USER");
	    final String PASSWORD = System.getenv("MYSQL_PASS");
	    
//	    System.out.println(USER);
//	    System.out.println(PASSWORD);
		
		
		// create database and tables using the utility object
	    DatabaseUtility.dropDatabase(JDBC_URL, DATABASE_NAME, USER, PASSWORD);
	    
		DatabaseUtility.createDatabase(JDBC_URL, DATABASE_NAME, USER, PASSWORD);
		DatabaseUtility.createManagersTable(JDBC_URL, DATABASE_NAME, USER, PASSWORD);
		DatabaseUtility.createCustomersTable(JDBC_URL, DATABASE_NAME, USER, PASSWORD);
		DatabaseUtility.createProfileTable(JDBC_URL, DATABASE_NAME, USER, PASSWORD);
		DatabaseUtility.createTicketAgentsTable(JDBC_URL, DATABASE_NAME, USER, PASSWORD);
		DatabaseUtility.createContractTable(JDBC_URL, DATABASE_NAME, USER, PASSWORD);
		DatabaseUtility.createEventTable(JDBC_URL, DATABASE_NAME, USER, PASSWORD);
		DatabaseUtility.createShowsTable(JDBC_URL, DATABASE_NAME, USER, PASSWORD);
		DatabaseUtility.createPromotionTable(JDBC_URL, DATABASE_NAME, USER, PASSWORD);
		DatabaseUtility.createDiscountsTable(JDBC_URL, DATABASE_NAME, USER, PASSWORD);
		DatabaseUtility.createSeatingChartTable(JDBC_URL, DATABASE_NAME, USER, PASSWORD);
		DatabaseUtility.createSeatsTable(JDBC_URL, DATABASE_NAME, USER, PASSWORD);
		DatabaseUtility.createTransactionSTable(JDBC_URL, DATABASE_NAME, USER, PASSWORD);
		DatabaseUtility.createAssignedSeatsTable(JDBC_URL, DATABASE_NAME, USER, PASSWORD);
		DatabaseUtility.createAgentTransactionsTable(JDBC_URL, DATABASE_NAME, USER, PASSWORD);
		
		
		// add data to the users table
		
		DatabaseDAO.insertManager("admin", "admin");
		
		//DatabaseDAO.insertCustomer("admin", "admin");
		DatabaseDAO.insertCustomer("moses", "123456");
		DatabaseDAO.insertCustomer("rose", "123456");
		DatabaseDAO.insertCustomer("mark", "123456");
		DatabaseDAO.insertCustomer("patricia", "1234567");
		
		// add data to the agents table
		DatabaseDAO.insertAgents("agent1", "1234567890");
		DatabaseDAO.insertAgents("agent2", "1234567890");
		DatabaseDAO.insertAgents("agent3", "1234567890");
		DatabaseDAO.insertAgents("agent4", "1234567890");
		
		// add data to the events table
		DatabaseDAO.insertEvent("Concert", "2023-12-29", "2024-01-10", "Upcoming");
		DatabaseDAO.insertEvent("Dance Performances", "2024-01-10", "2024-01-20", "Cancelled");
		DatabaseDAO.insertEvent("Musicals", "2024-02-15", "2024-02-25", "Upcoming");
		DatabaseDAO.insertEvent("Opera", "2024-03-01", "2024-03-14", "Cancelled");
		DatabaseDAO.insertEvent("Comedy Shows", "2024-03-15", "2024-03-25", "Upcoming");
		
		// add data to the shows table
		DatabaseDAO.insertShows(1, "Rock Concert", "2023-12-31", 3);
		DatabaseDAO.insertShows(1, "Classical Music Concert", "2023-12-31", 3);
		DatabaseDAO.insertShows(1, "Jazz Night", "2023-12-31", 3);
		DatabaseDAO.insertShows(1, "Contemporary Music Showcase", "2023-12-31", 3);
		DatabaseDAO.insertShows(2, "Hamilton", "2024-01-12", 4);
		DatabaseDAO.insertShows(2, "Wicked", "2024-01-12", 4);
		DatabaseDAO.insertShows(4, "Les Misérables", "2024-01-12", 4);
		
//		// add data to the promotion table
		DatabaseDAO.insertPromotion(20, 15, 8, 12, 1);
		DatabaseDAO.insertPromotion(30, 20, 12, 16, 2);
		
		DatabaseDAO.insertSeatingChart(1, 6, 6);
		DatabaseDAO.insertSeatingChart(2, 6, 6);
		DatabaseDAO.insertSeatingChart(3, 6, 6);
		DatabaseDAO.insertSeatingChart(4, 6, 6);
		DatabaseDAO.insertSeatingChart(5, 6, 6);
		DatabaseDAO.insertSeatingChart(7, 6, 6);
		DatabaseDAO.insertSeatingChart(6, 6, 6);
		
		DatabaseDAO.insertSeats("Premium", "Available", 15.0, 1);
		DatabaseDAO.insertSeats("VIP", "Available", 30, 1);
		DatabaseDAO.insertSeats("Standard", "Available", 10, 1);
		DatabaseDAO.insertSeats("VIP", "Available", 40, 1);
		DatabaseDAO.insertSeats("Premium", "Sold", 20, 1);
		DatabaseDAO.insertSeats("Premium", "Available", 20, 1);
		DatabaseDAO.insertSeats("VIP", "Sold", 30, 1);
		DatabaseDAO.insertSeats("Premium", "Available", 20, 1);
		DatabaseDAO.insertSeats("Standard", "Available", 10, 1);
		DatabaseDAO.insertSeats("VIP", "Available", 40, 1);
		DatabaseDAO.insertSeats("Premium", "Available", 30, 1);
		DatabaseDAO.insertSeats("Standard", "Available", 10, 1);
		DatabaseDAO.insertSeats("Premium", "Sold", 20, 1);
		DatabaseDAO.insertSeats("Standard", "Available", 10, 1);
		
		
		
		
		
		
		
		
	}

}
