/**
 * 
 */
package bcpa.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import bcpa.backend.Seat;

/**
 * 
 *
 */
public class UpdateTablesDAO {
	
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bcpa_database";
    private static final String USER = System.getenv("MYSQL_USER");
    private static final String PASSWORD = System.getenv("MYSQL_PASS");
	
	public static void rescheduleEvent(String eventName, String newStartDate, String status, String newEndDate) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL , USER, PASSWORD)) {
            String rescheduleQuery = "UPDATE events SET start_date = ?, end_date = ?, event_status = ? WHERE event_name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(rescheduleQuery)) {
                preparedStatement.setString(1, newStartDate);
                preparedStatement.setString(2, newEndDate);
                preparedStatement.setString(3, status);
                preparedStatement.setString(4, eventName);
                preparedStatement.executeUpdate();
                System.out.println("Event rescheduled successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error rescheduling event: " + e.getMessage());
        }
    }

    public static void cancelEvent(String eventName, String status) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String cancelQuery = "UPDATE events SET event_status = ? WHERE event_name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(cancelQuery)) {
                preparedStatement.setString(1, status);
                preparedStatement.setString(2, eventName);
                preparedStatement.executeUpdate();
                System.out.println("Event cancelled successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error cancelling event: " + e.getMessage());
        }
    }
    
    // Function to update seat statuses in the database
    public static void updateSeatStatus(ArrayList<Seat> seats, String newStatus) {
        // make a database connection
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            // Update each seat status in the database
            for (Seat seat : seats) {
                String updateQuery = "UPDATE seats SET seat_status = ? WHERE id = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    preparedStatement.setString(1, newStatus);
                    preparedStatement.setInt(2, seat.getSeatId());  // Adjust based on your actual seat ID property
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database errors appropriately
        }
    }
    
    // Function to update shows maximum seats per customer
    public static void updateSeatsPerCustomer(String showName, int newMaxSeats) {
    	//make a database connection
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            // Create a query to update shows table in the database
        	String updateQuery = "UPDATE shows SET max_seats_per_customer = ? WHERE show_name = ?";    
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setInt(1, newMaxSeats);
                preparedStatement.setString(2, showName);  // Adjust based on your actual seat ID property
                preparedStatement.executeUpdate();
            }
            
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database errors appropriately
        }
    }

}
