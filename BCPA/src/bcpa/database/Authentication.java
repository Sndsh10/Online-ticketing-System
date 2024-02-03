package bcpa.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authentication {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bcpa_database";
    private static final String USER = System.getenv("MYSQL_USER");
    private static final String PASSWORD = System.getenv("MYSQL_PASS");
    
    public static boolean verifyManager(String username, String password) {
    	String query = "SELECT * FROM venue_managers WHERE username = ? and password = ?";
    	return verifyCredentials(query, username, password);
    }

    // Method to verify a customer's credentials
    public static boolean verifyUser(String username, String password) {
        String query = "SELECT * FROM customers WHERE username = ? AND password = ?";
        return verifyCredentials(query, username, password);
    }

    // Method to verify a ticket agent's credentials
    public static boolean verifyTicketAgent(String username, String password) {
        String query = "SELECT * FROM ticket_agents WHERE username = ? AND password = ?";
        return verifyCredentials(query, username, password);
    }
    
    // Method to check if a username already exists
    public static boolean usernameExists(String username) {
        String query = "SELECT COUNT(*) FROM customers WHERE username = ?";
        return checkIfExists(query, username);
    }

    // Method to check if a ticket agent username already exists
    public static boolean ticketAgentUsernameExists(String username) {
        String query = "SELECT COUNT(*) FROM ticket_agents WHERE username = ?";
        return checkIfExists(query, username);
    }

    // Generic method to verify credentials
    private static boolean verifyCredentials(String query, String username, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Returns true if there is at least one matching record
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
 // Generic method to check if a record with the given username already exists
    private static boolean checkIfExists(String query, String username) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // Returns true if the count is greater than 0 (record exists)
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

}
