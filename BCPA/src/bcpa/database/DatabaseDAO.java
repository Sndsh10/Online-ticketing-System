package bcpa.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class DatabaseDAO {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bcpa_database";
    private static final String USER = System.getenv("MYSQL_USER");
    private static final String PASSWORD = System.getenv("MYSQL_PASS");
    
    public static void insertManager(String username, String password) {
    	String insertQuery = "INSERT INTO venue_managers (username, password) VALUES (?, ?)";
    	executeInsert(insertQuery, username, password);
    }

    public static void insertCustomer(String username, String password) {
        String insertQuery = "INSERT INTO customers (username, password) VALUES (?, ?)";
        executeInsert(insertQuery, username, password);
    }
    
    public static void insertProfile(int customerId, String streetAddress, String city, String postalCode) {
    	String insertQuery = "INSERT INTO profiles (customer_id, streetAddress, city, postalCode) VALUES (?, ?, ?, ?)";
    	executeInsert(insertQuery, String.valueOf(customerId), streetAddress, city, postalCode);
    }
    
    public static void insertAgents(String username, String password) {
        String insertQuery = "INSERT INTO ticket_agents (username, password) VALUES (?, ?)";
        executeInsert(insertQuery, username, password);
    }
    
    public static void insertContract(int agentId, double commissionPercentage, String terms) {
    	String insertQuery = "INSERT INTO contracts (agent_id, commission_percentage, terms) VALUES (?, ?, ?)";
    	executeInsert(insertQuery, String.valueOf(agentId), String.valueOf(commissionPercentage), terms);
    }

    public static void insertEvent(String eventName, String startDate, String endDate, String eventStatus) {
        String insertQuery = "INSERT INTO events (event_name, start_date, end_date, event_status) VALUES (?, ?, ?, ?)";
        executeInsert(insertQuery, eventName, startDate, endDate, eventStatus);
    }
    
    public static void insertShows(int eventId, String showName, String showDate, int maxSeatsPerCustomer) {
		String insertQuery = "INSERT INTO shows (event_id, show_name, show_date, max_seats_per_customer) VALUES (?, ?, ?, ?)";
		executeInsert(insertQuery, String.valueOf(eventId), showName, showDate, String.valueOf(maxSeatsPerCustomer));
	}
    
    public static void insertPromotion(double adultPrice, double studentPrice,
            double childPrice, double seniorPrice, int showId) {
		String insertQuery = "INSERT INTO promotions (adult_price, student_price, child_price, senior_price, show_id) " +
		"VALUES (?, ?, ?, ?, ?)";
		executeInsert(insertQuery, String.valueOf(adultPrice), String.valueOf(studentPrice),
		String.valueOf(childPrice), String.valueOf(seniorPrice), String.valueOf(showId));
	}
    
    public static void insertDiscounts(int promotionId, double discountPercentage, String description) {
    	String insertQuery = "INSERT INTO discounts (promotion_id, discount_percentage, description) VALUES (?, ?, ?)";
    	executeInsert(insertQuery, String.valueOf(promotionId), String.valueOf(discountPercentage), description);
    }
    
    public static void insertSeatingChart(int showId, int rows, int columns) {
    	String insertQuery = "INSERT INTO seating_charts (show_id, chart_rows, chart_columns) VALUES (?, ?, ?)";
    	executeInsert(insertQuery, String.valueOf(showId), String.valueOf(rows), String.valueOf(columns));
    }
    
    public static void insertSeats(String seatType, String seatStatus, double seatPrice, int chartId) {
        String insertQuery = "INSERT INTO seats (seat_type, seat_status, seat_price, chart_id) " +
                "VALUES (?, ?, ?, ?)";
        executeInsert(insertQuery, seatType, seatStatus, String.valueOf(seatPrice), String.valueOf(chartId));
    }
    
    public static void insertTransactions(int customerId, int showId, int purchasedSeats, double totalAmount, String transactionDate) {
    	String insertQuery = "INSERT INTO transactions (customer_id, show_id, purchasedSeats, totalAmount, transactionDate) " + 
    			"VALUES (?, ?, ?, ?, ?)";
    	executeInsert(insertQuery, String.valueOf(customerId), String.valueOf(showId), String.valueOf(purchasedSeats), 
    			String.valueOf(totalAmount), transactionDate);
    }
    
    public static void insertAssignedSeats(int seatId, int agentId, String startDate, String endDate) {
    	String insertQuery = "INSERT INTO agents_assigned_seats (seat_id, agent_id, startDate, endDate) VALUES (?, ?, ?, ?)";
    	executeInsert(insertQuery, String.valueOf(seatId), String.valueOf(agentId), startDate, endDate);
    }
    
    public static void insertAgentTransactions(int assignedSeatId, String soldDate, int seatsSold, double amount) {
    	String insertQuery = "INSERT INTO agents_transactions (assigned_seat_id, soldDate, seatsSold, amount) VALUES (?, ?, ?, ?)";
    	executeInsert(insertQuery, String.valueOf(assignedSeatId), soldDate, String.valueOf(seatsSold), String.valueOf(amount));
    }

    // Add more methods for other tables as needed

    private static void executeInsert(String query, String... values) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (int i = 0; i < values.length; i++) {
                preparedStatement.setString(i + 1, values[i]);
            }

            preparedStatement.executeUpdate();
            System.out.println("Data inserted successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

