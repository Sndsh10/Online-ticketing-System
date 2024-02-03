package bcpa.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import bcpa.backend.Event;
import bcpa.backend.SeatingChart;
import bcpa.backend.Show;
import bcpa.backend.Seat;

public class DataRetrieval {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bcpa_database";
    private static final String USER = System.getenv("MYSQL_USER");
    private static final String PASSWORD = System.getenv("MYSQL_PASS");

    public static ArrayList<Event> getUpcomingEvents() {
        ArrayList<Event> upcomingEvents = new ArrayList<>();

        String query = "SELECT * FROM events WHERE start_date > CURRENT_DATE AND event_status = 'Scheduled'";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String eventName = resultSet.getString("event_name");
                String startingDateStr = resultSet.getString("start_date");
                String endingDateStr = resultSet.getString("end_date");

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date startingDate = dateFormat.parse(startingDateStr);
                Date endingDate = dateFormat.parse(endingDateStr);

                Event event = new Event(eventName, startingDate, endingDate);
                upcomingEvents.add(event);
            }

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        return upcomingEvents;
    }
    
    // Function to retrieve shows based on the event name
    public static ArrayList<Show> getShowsForEvent(int eventId) throws ParseException {
        ArrayList<Show> shows = new ArrayList<>();

        // Use try-with-resources to automatically close resources (Connection, PreparedStatement, ResultSet)
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM shows WHERE event_id = ?");
        ) {
            // Set the parameter for the prepared statement
            preparedStatement.setInt(1, eventId);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Retrieve show details from the result set
                    String showName = resultSet.getString("show_name");
                    int event = Integer.parseInt(resultSet.getString("event_id"));
                    String showDate = resultSet.getString("show_date");
                    String maxCustomers = resultSet.getString("max_seats_per_customer");
                    
                    int customers = Integer.valueOf(maxCustomers);

                    // Create a Show object and add it to the list
                    Show show = new Show();
                    show.setShowName(showName);
                    show.setEventId(event);
                    show.setDate(showDate);
                    show.setMaxSeatsPerCustomer(customers);
                    
                    shows.add(show);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shows;
    }
    
    // Function to check if an event name exists in the database
    public static boolean doesEventExist(int eventId) {
        boolean eventExists = false;

        // Use try-with-resources to automatically close resources (Connection, PreparedStatement, ResultSet)
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM events WHERE event_name = ?");
        ) {
            // Set the parameter for the prepared statement
            preparedStatement.setString(1, String.valueOf(eventId));

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Check if there is at least one row in the result set
                if (resultSet.next()) {
                    int rowCount = resultSet.getInt(1);
                    eventExists = rowCount > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventExists;
    }
    
    public static ArrayList<String> getEventNames() {
        ArrayList<String> eventNames = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String query = "SELECT event_name FROM events";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String eventName = resultSet.getString("event_name");
                    eventNames.add(eventName);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventNames;
    }
    
    public static ArrayList<String> getShowNames() {
        ArrayList<String> showNames = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String query = "SELECT show_name FROM shows";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String showName = resultSet.getString("show_name");
                    showNames.add(showName);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return showNames;
    }
    
    public static int getCustomerId(String username) {
        int customerId = -1;

        String query = "SELECT id FROM customers WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                	customerId = resultSet.getInt("id");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerId;
    }
    
    public static int getEventId(String eventName) {
        int eventId = -1; // Default value if event is not found or an error occurs

        String query = "SELECT id FROM events WHERE event_name = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, eventName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    eventId = resultSet.getInt("id");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventId;
    }
    
    public static int getShowId(String showName) {
        int showId = -1; // Default value if not found

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String query = "SELECT id FROM shows WHERE show_name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, showName);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        showId = resultSet.getInt("id");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting show ID: " + e.getMessage());
        }

        return showId;
    }
    
    public static SeatingChart getSeatingChartByShowId(int showId) {
        SeatingChart seatingChart = null;

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String query = "SELECT * FROM seating_charts WHERE show_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, showId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int chartId = resultSet.getInt("chart_id");
                        int rows = resultSet.getInt("chart_rows");
                        int columns = resultSet.getInt("chart_columns");
                        int show = resultSet.getInt("show_id");

                        seatingChart = new SeatingChart(chartId, show, rows, columns);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting seating chart info: " + e.getMessage());
        }

        return seatingChart;
    }
    
    public static ArrayList<Seat> getSeatsBySeatingChartId(int seatingChartId) {
        ArrayList<Seat> seats = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String query = "SELECT * FROM seats WHERE chart_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, seatingChartId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                    	int chartId = resultSet.getInt("chart_id");
                        int seatId = resultSet.getInt("id");
                        // You might have other properties for the Seat class
                        String seatType = resultSet.getString("seat_type");
                        String status = resultSet.getString("seat_status");
                        double price = resultSet.getDouble("seat_price");
                      
                        // Create Seat object and add it to the list
                        Seat seat = new Seat(seatId, chartId, seatType, status, price);
                        seats.add(seat);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return seats;
    }
    

}

