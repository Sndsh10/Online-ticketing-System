package bcpa.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtility {
	
	public static void dropDatabase(String JDBC_URL, String databaseName, String username, String password) {
		try (Connection connection = DriverManager.getConnection(JDBC_URL, username, password)){
			Statement statement = connection.createStatement();
			
			String dropDatabaseQuery = "DROP DATABASE IF EXISTS " + databaseName;
			
			statement.execute(dropDatabaseQuery);
			System.out.println("Database 'bcpa_database' dropped successfully");
		}catch (SQLException ex) {
			System.out.println("__dropDatabase__.function raised an exception. " + ex.getMessage());
		}
	}

	public static void createDatabase(String JDBC_URL, String databaseName, String username, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, username, password)) {
            Statement statement = connection.createStatement();

            // Create the database
            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + databaseName;
            statement.executeUpdate(createDatabaseQuery);

            System.out.println("Database created successfully: " + databaseName);

        } catch (SQLException e) {
            System.out.println("__createDatabase__.function raised an exception. " + e.getMessage());
        }
    }
	
	public static void createManagersTable(String JDBC_URL, String databaseName, String username, String password) {
		try (Connection connection = DriverManager.getConnection(JDBC_URL + databaseName, username, password)) {
			Statement statement = connection.createStatement();
			
			String createManagersQuery = "CREATE TABLE IF NOT EXISTS venue_managers (" + 
					"manager_id INT AUTO_INCREMENT PRIMARY KEY, " + 
					"username VARCHAR(255) NOT NULL UNIQUE, " + 
					"password VARCHAR(255) NOT NULL)";
			statement.execute(createManagersQuery);
			System.out.println("Table 'venue_managers' created successfully");
					
		}catch (SQLException ex) {
			System.out.println("__createManagersTable__function raised an exception. " + ex.getMessage());
		}
	}

    public static void createCustomersTable(String JDBC_URL, String databaseName, String username, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL + databaseName, username, password)) {
            Statement statement = connection.createStatement();

            // Create the users table
            String createTableQuery = "CREATE TABLE IF NOT EXISTS customers (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "username VARCHAR(255) NOT NULL," +
                    "password VARCHAR(255) NOT NULL)";
            statement.executeUpdate(createTableQuery);

            System.out.println("Table 'customers' created successfully");

        } catch (SQLException e) {
            System.out.println("__createCustomersTable__.function raised and exception. " + e.getMessage());
        }
    }
    
    public static void createProfileTable(String JDBC_URL, String databaseName, String username, String password) {
    	try (Connection connection = DriverManager.getConnection(JDBC_URL + databaseName, username, password)){
    		Statement statement = connection.createStatement();
    		
    		String createProfileQuery = "CREATE TABLE IF NOT EXISTS profiles (" + 
    				"profile_id INT AUTO_INCREMENT PRIMARY KEY, " + 
    				"customer_id INT NOT NULL, " + 
    				"streetAddress VARCHAR(255) NOT NULL, " + 
    				"city VARCHAR(50) NOT NULL, " + 
    				"postalCode VARCHAR(20) NOT NULL, " + 
    				"CONSTRAINT profile_customers_fk FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE ON UPDATE CASCADE)";
    		statement.execute(createProfileQuery);
    		System.out.println("Table 'profiles' created successfully");
    	}catch(SQLException ex) {
    		System.out.println("__createProfileTable__.function raised an exception. " + ex.getMessage());
    	}
    }
    
    public static void createTicketAgentsTable(String JDBC_URL, String databaseName, String username, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL + databaseName, username, password)) {
            Statement statement = connection.createStatement();

            // Create the ticket_agents table
            String createTableQuery = "CREATE TABLE IF NOT EXISTS ticket_agents (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "username VARCHAR(255) UNIQUE NOT NULL," +
                    "password VARCHAR(255) NOT NULL)";
            statement.executeUpdate(createTableQuery);

            System.out.println("Table 'ticket_agents' created successfully");

        } catch (SQLException e) {
            System.out.println("__createTicketAgentsTable__.function raised an exception. " + e.getMessage());
        }
    }
    
    public static void createContractTable(String JDBC_URL, String databaseName, String username, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL + databaseName, username, password)) {
            Statement statement = connection.createStatement();
            
            String createContractQuery = "CREATE TABLE IF NOT EXISTS contracts (" + 
            		"contract_id INT AUTO_INCREMENT PRIMARY KEY, " + 
            		"agent_id INT NOT NULL, " + 
            		"commission_percentage DECIMAL(6,2) NOT NULL, " + 
            		"terms TEXT, " + 
            		"CONSTRAINT contract_agents_fk FOREIGN KEY (agent_id) REFERENCES ticket_agents(id) ON DELETE CASCADE ON UPDATE CASCADE)";
            statement.execute(createContractQuery);
            System.out.println("Table 'contracts' created successfully");
        }catch (SQLException ex) {
        	System.out.println("__createContractTable__.function raised an exception. " + ex.getMessage());
        }
    }
    
    public static void createEventTable(String JDBC_URL, String databaseName, String username, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL + databaseName, username, password)) {
            Statement statement = connection.createStatement();

            // Create the event table
            String createTableQuery = "CREATE TABLE IF NOT EXISTS events (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "event_name VARCHAR(255) NOT NULL," +
                    "start_date DATE NOT NULL," +
                    "end_date DATE NOT NULL," +  // CHECK constraint
                    "event_status VARCHAR(20) NOT NULL, " + 
                    "CHECK (end_date > start_date))";
            statement.executeUpdate(createTableQuery);
            System.out.println("Table 'events' created successfully");

        } catch (SQLException e) {
            System.out.println("__createEventTable__.function raised an exception. " + e.getLocalizedMessage());
        }
    }
    
   
    
    public static void createShowsTable(String JDBC_URL, String databaseName, String username, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL + databaseName, username, password)) {
            Statement statement = connection.createStatement();

            // Create the shows table with foreign key references to event and promotion tables
            String createTableQuery = "CREATE TABLE IF NOT EXISTS shows (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "event_id INT NOT NULL," +
                    "show_name VARCHAR(255) NOT NULL," +
                    "show_date DATE NOT NULL," +
                    "max_seats_per_customer INT NOT NULL," +
                    "CONSTRAINT shows_events_fk FOREIGN KEY (event_id) REFERENCES events(id)ON DELETE CASCADE ON UPDATE CASCADE)";
            statement.executeUpdate(createTableQuery);

            System.out.println("Table 'shows' created successfully");

        } catch (SQLException e) {
            System.out.println("__createShowsTable__.function raised an exception. " + e.getLocalizedMessage());
        }
    }
    
    public static void createPromotionTable(String JDBC_URL, String databaseName, String username, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL + databaseName, username, password)) {
            Statement statement = connection.createStatement();

            // Create the promotion table
            String createTableQuery = "CREATE TABLE IF NOT EXISTS promotions (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "adult_price DECIMAL(10, 2) NOT NULL," +
                    "student_price DECIMAL(10, 2) NOT NULL," +
                    "child_price DECIMAL(10, 2) NOT NULL," +
                    "senior_price DECIMAL(10, 2) NOT NULL," + 
                    "show_id INT NOT NULL, " + 
                    "CONSTRAINT promotions_shows_fk FOREIGN KEY (show_id) REFERENCES shows(id) ON DELETE CASCADE ON UPDATE CASCADE)";
            statement.executeUpdate(createTableQuery);

            System.out.println("Table 'promotions' created successfully");

        } catch (SQLException e) {
            System.out.println("__createPromotionTable__.function raised an exception. " + e.getLocalizedMessage());
        }
    }
    
    public static void createDiscountsTable(String JDBC_URL, String databaseName, String username, String password) {
    	try (Connection connection = DriverManager.getConnection(JDBC_URL + databaseName, username, password)) {
    		Statement statement = connection.createStatement();
    		
    		String createDiscountsQuery = "CREATE TABLE IF NOT EXISTS discounts (" + 
    				"discount_id INT AUTO_INCREMENT PRIMARY KEY, " + 
    				"promotion_id INT NOT NULL, " + 
    				"discount_percentage DECIMAL(4, 2) NOT NULL, " + 
    				"description TEXT, " + 
    				"CONSTRAINT discounts_promotions_fk FOREIGN KEY (promotion_id) REFERENCES promotions (id) ON DELETE CASCADE ON UPDATE CASCADE)";
    		statement.execute(createDiscountsQuery);
    		
    		System.out.println("Table 'discounts' created successfully");
    	}
    	catch (SQLException ex) {
    		System.out.println("__createDiscountsTable__.function raised an exception. " + ex.getMessage());
    	}
    }
    
    public static void createSeatingChartTable(String JDBC_URL, String databaseName, String username, String password) {
    	try (Connection connection = DriverManager.getConnection(JDBC_URL + databaseName, username, password)){
    		Statement statement = connection.createStatement();
    		
    		String createChartsQuery = "CREATE TABLE IF NOT EXISTS seating_charts (" + 
    				"chart_id INT AUTO_INCREMENT PRIMARY KEY, " + 
    				"show_id INT NOT NULL, " + 
    				"chart_rows INT NOT NULL, " + 
    				"chart_columns INT NOT NULL, " + 
    				"CONSTRAINT charts_shows_fk FOREIGN KEY (show_id) REFERENCES shows(id) ON DELETE CASCADE ON UPDATE CASCADE)";
    		
    		statement.execute(createChartsQuery);
    		System.out.println("Table 'seating_charts' created successfully");
    	}
    	catch (SQLException ex) {
    		System.out.println("__createSeatingChartTable__.function raised an exception. " + ex.getMessage());
    	}
    }
    
    public static void createSeatsTable(String JDBC_URL, String databaseName, String username, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL + databaseName, username, password)) {
            Statement statement = connection.createStatement();

            // Create the seats table
            String createTableQuery = "CREATE TABLE IF NOT EXISTS seats (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "seat_type VARCHAR(255) NOT NULL," +
                    "seat_status VARCHAR(255) NOT NULL," +
                    "seat_price DECIMAL(6, 2) NOT NULL, " + 
                    "chart_id INT NOT NULL, " + 
                    "CONSTRAINT seats_charts_fk FOREIGN KEY(chart_id) REFERENCES seating_charts (chart_id) ON DELETE CASCADE ON UPDATE CASCADE)";
            
            statement.executeUpdate(createTableQuery);

            System.out.println("Table 'seats' created successfully");

        } catch (SQLException e) {
            System.out.println("__createSeatsTable__.function raised an exception. " + e.getLocalizedMessage());
        }
    }
    
    public static void createTransactionSTable(String JDBC_URL, String databaseName, String username, String password) {
    	try (Connection connection = DriverManager.getConnection(JDBC_URL + databaseName, username, password)) {
    		Statement statement = connection.createStatement();
    		
    		String createTransactionQuery = "CREATE TABLE IF NOT EXISTS transactions (" + 
    				"transaction_id INT AUTO_INCREMENT PRIMARY KEY, " + 
    				"customer_id INT NOT NULL, " + 
    				"show_id INT NOT NULL, " + 
    				"purchasedSeats INT NOT NULL, " + 
    				"totalAmount DECIMAL(10, 2) NOT NULL, " + 
    				"transactionDate DATE NOT NULL, " + 
    				"CONSTRAINT transaction_customers_fk FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE ON UPDATE CASCADE, " + 
    				"CONSTRAINT transaction_shows_fk FOREIGN KEY (show_id) REFERENCES shows (id) ON DELETE CASCADE ON UPDATE CASCADE)";
    		statement.execute(createTransactionQuery);
    		System.out.println("Table 'transactions' created successfully");
    	}
    	catch(SQLException ex) {
    		System.out.println("__createTransactionTable__.function raised an exception. " + ex.getLocalizedMessage());
    	}
    }
    
    public static void createAssignedSeatsTable(String JDBC_URL, String databaseName, String username, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL + databaseName, username, password)) {
            Statement statement = connection.createStatement();

            // Create the assigned_shows_seats table
            String createTableQuery = "CREATE TABLE IF NOT EXISTS agents_assigned_seats (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "seat_id INT NOT NULL," +
                    "agent_id INT NOT NULL," +
                    "startDate DATE NOT NULL, " +
                    "endDate DATE NOT NULL, " +
                    "CHECK (endDate > startDate), " +
                    "CONSTRAINT assigned_fk FOREIGN KEY (seat_id) REFERENCES seats(id) ON DELETE CASCADE ON UPDATE CASCADE," +
                    "CONSTRAINT assigned_agent_fk FOREIGN KEY (agent_id) REFERENCES ticket_agents(id) ON DELETE CASCADE ON UPDATE CASCADE)";
            statement.executeUpdate(createTableQuery);

            System.out.println("Table 'assigned_shows_seats' created successfully");

        } catch (SQLException e) {
            System.out.println("__createAssignedSeatsTable__.function raised an exception. " + e.getLocalizedMessage());
        }
    }
        
        public static void createAgentTransactionsTable(String JDBC_URL, String databaseName, String username, String password) {
        	try (Connection connection = DriverManager.getConnection(JDBC_URL + databaseName, username, password)) {
        		Statement statement = connection.createStatement();
        		
        		// Create agents_transactions table
                String createAgentTransactionQuery = "CREATE TABLE IF NOT EXISTS agents_transactions (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "assigned_seat_id INT NOT NULL, " +
                    "soldDate DATE NOT NULL, " +
                    "seatsSold INT NOT NULL, " +
                    "amount DECIMAL(10, 2), " +
                    "CONSTRAINT agentTransaction_assigned_seats_fk FOREIGN KEY (assigned_seat_id) REFERENCES agents_assigned_seats(id) ON DELETE CASCADE ON UPDATE CASCADE)";
                statement.execute(createAgentTransactionQuery);

                // Create trigger to check date range
                String createTriggerQuery = "CREATE TRIGGER check_date_range BEFORE INSERT ON agents_transactions " +
                    "FOR EACH ROW BEGIN " +
                    "  DECLARE start_date DATE; " +
                    "  DECLARE end_date DATE; " +
                    "  SELECT startDate, endDate INTO start_date, end_date FROM agents_assigned_seats WHERE id = NEW.assigned_seat_id; " +
                    "  IF NEW.soldDate NOT BETWEEN start_date AND end_date THEN " +
                    "    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Date range check failed'; " +
                    "  END IF; " +
                    "END";
                statement.execute(createTriggerQuery);

                System.out.println("Table 'agents_transactions' and trigger created successfully");
        	}catch (SQLException ex) {
        		System.out.println("__createAgentTransactionsTable__.function raised an exception. " +  ex.getLocalizedMessage());
        	}
        }
}

