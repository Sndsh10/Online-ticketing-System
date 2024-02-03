package bcpa.frontend;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import bcpa.backend.*;
import bcpa.backend.Event;
import bcpa.database.DataRetrieval;
import bcpa.database.DatabaseDAO;
import bcpa.database.UpdateTablesDAO;

public class CustomerMainFrame extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSplitPane splitPane; // Declare splitPane at the class level
	private JPanel rightPanel;
	private ArrayList<Seat> selectedSeats = new ArrayList<>();

	
	Event selectedEvent;
	Show selectedShow;
	private String customerUsername;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");


	public CustomerMainFrame(String customerUsername) {
		
		this.setCustomerName(customerUsername);
        // Set frame properties
        setTitle("Welcome, " + this.getCustomerUsername().substring(0, 1).toUpperCase() + 
        		this.getCustomerUsername().substring(1) + ". Customer Main Window");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        // Create a split pane
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(250); // Set initial divider location

        // Create left panel for functionalities
        JPanel leftPanel = createLeftPanel();
        splitPane.setLeftComponent(leftPanel);

        // Create right panel for displaying results
        rightPanel = new JPanel(); // You'll need to update this based on the selected functionality
        splitPane.setRightComponent(rightPanel);

        // Set layout and add the split pane
        setLayout(new GridLayout(1, 1));
        add(splitPane);
    }
	
	public String getCustomerUsername() {
		return this.customerUsername;
	}

	public void setCustomerName(String username) {
		this.customerUsername = username;
	}
    private JPanel createLeftPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 0, 10, 0); // Add margin between buttons

        // Create buttons for functionalities
        JButton viewUpcomingEventsButton = new JButton("View Upcoming Events");
        JButton viewScheduledShowsButton = new JButton("View Scheduled Shows by Date");
        JButton selectSeatsButton = new JButton("Select Seats from Seating Chart");

        // Set preferred width for buttons
        viewUpcomingEventsButton.setPreferredSize(new Dimension(250, viewUpcomingEventsButton.getPreferredSize().height));
        viewScheduledShowsButton.setPreferredSize(new Dimension(250, viewScheduledShowsButton.getPreferredSize().height));
        selectSeatsButton.setPreferredSize(new Dimension(250, selectSeatsButton.getPreferredSize().height));

        // Add action listeners to the buttons
        viewUpcomingEventsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the button click, update the right panel accordingly
                try {
					updateRightPanel("View Upcoming Events");
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });

        viewScheduledShowsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the button click, update the right panel accordingly
                try {
					updateRightPanel("View Scheduled Shows by Date");
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });

        selectSeatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the button click, update the right panel accordingly
                try {
					updateRightPanel("Select Seats from Seating Chart");
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });

        // Add buttons to the left panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(viewUpcomingEventsButton, constraints);

        constraints.gridy = 1;
        panel.add(viewScheduledShowsButton, constraints);

        constraints.gridy = 2;
        panel.add(selectSeatsButton, constraints);

        return panel;
    }

    private void updateRightPanel(String functionality) throws ParseException {
    	// Clear the right panel before updating
        JPanel rightPanel = (JPanel) splitPane.getRightComponent();
        rightPanel.removeAll();
        rightPanel.revalidate();
        rightPanel.repaint();

        // Implement the logic based on the selected functionality
        if ("View Upcoming Events".equals(functionality)) {
            // Display a table with upcoming events
            displayUpcomingEventsTable(rightPanel);
        } else if ("View Scheduled Shows by Date".equals(functionality)) {
            // Display shows based on the selected event
            displayScheduledShowsTable(rightPanel);
        } else if ("Select Seats from Seating Chart".equals(functionality)) {
            // Display the seating chart
        	displaySeatingChart(rightPanel);
        }
    }
    
    private void displayUpcomingEventsTable(JPanel panel) {
    	// Fetch the list of upcoming events from your ArrayList
        ArrayList<Event> upcomingEvents = Customer.viewUpcomingEvents(); // Replace with your logic

        // Create a table model
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Event Name");
        model.addColumn("Starting Date");
        model.addColumn("Ending Date");

        // Populate the table model with data
        //SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        for (Event event : upcomingEvents) {
            Object[] rowData = {event.getName(), dateFormat.format(event.getStartDate()), dateFormat.format(event.getEndingDate())};
            model.addRow(rowData);
        }

        // Create the table with the model
        JTable table = new JTable(model);
        
        // Set auto-resize mode for columns
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        // Add a selection listener to the table to capture the selected event
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Get the selected row
                    int selectedRow = table.getSelectedRow();

                    if (selectedRow != -1) {
                        // Get the selected event name based on the row
                        selectedEvent = upcomingEvents.get(selectedRow);

                        // Do something with the selected event name (e.g., store it for later use)
                        System.out.println("Selected Event Name: " + selectedEvent.getName());
                    }
                }
            }
        });

        // Add the table to a scroll pane and then to the right panel
        JScrollPane scrollPane = new JScrollPane(table);
        rightPanel.add(scrollPane);
    }


	private void displayScheduledShowsTable(JPanel panel) throws ParseException {
		
		if(this.selectedEvent == null) {
			System.out.println("Selected Event is null");
			return;
		}
		
		int eventId = DataRetrieval.getEventId(selectedEvent.getName());
        // Fetch the list of scheduled shows from your ArrayList based on the selected event
        ArrayList<Show> scheduledShows = Customer.viewShowsByDate(eventId); // Replace with your logic

        // Create a table model
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Show Name");
        model.addColumn("Date");
        model.addColumn("Maximum seats");

        // Populate the table model with data
        for (Show show : scheduledShows) {
            Object[] rowData = {show.getShowName(), show.getDate(), show.getMaxSeatsPerCustomer()};
            model.addRow(rowData);
        }

        // Create the table with the model
        JTable table = new JTable(model);

        // Add a selection listener to the table to capture the selected show
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Get the selected row
                    int selectedRow = table.getSelectedRow();

                    if (selectedRow != -1) {
                        // Get the selected show based on the row
                        selectedShow = scheduledShows.get(selectedRow);
                        
                        System.out.println("the selected show is: " + selectedShow.getShowName());

                        // Update the right panel to display seats for the selected show
                        //displaySeatsForShow(panel, selectedShow);
                    }
                }
            }
        });

        // Add the table to a scroll pane and then to the right panel
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
    }

	private void displaySeatingChart(JPanel panel) {
		
		if (this.selectedShow == null) {
	        // Handle the case when selectedShow is null
	        System.out.println("Selected show is null");
	        return; // or perform some other action
	    }
		
	    int showId = Show.getShowId(selectedShow.getShowName());
	    SeatingChart seatingChart = SeatingChart.getSeatingChart(showId);
	    ArrayList<Seat> seats = Seat.getSeats(seatingChart.getChartId());
	    
	    seats.sort(Comparator.comparingDouble(Seat::getPrice).reversed());

	    int columns = seatingChart.getChartColumns();

	    // Create upper and lower panels
	    JPanel upperPanel = new JPanel(new GridBagLayout());
	    JPanel lowerPanel = new JPanel();
	    JPanel buyTicketPanel = new JPanel(); // Panel for "Buy Ticket" button
	    buyTicketPanel.setLayout(new BoxLayout(buyTicketPanel, BoxLayout.Y_AXIS));

	    
	    // Set borders for upper and lower panels
	    upperPanel.setBorder(BorderFactory.createTitledBorder("Seating Chart Panel"));
	    lowerPanel.setBorder(BorderFactory.createTitledBorder("Buy Ticket Panel"));
	    
	    lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.Y_AXIS));

	    // Use GridBagLayout to arrange buttons in the upper panel
	    upperPanel.setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(2, 2, 2, 2);
	    
	    upperPanel.setPreferredSize(new Dimension(500, 250));
	    
	    // Wrap the lowerPanel in a JScrollPane
	    JScrollPane scrollPane = new JScrollPane(lowerPanel);
	    scrollPane.setPreferredSize(new Dimension(500, 50));

	    for (int i = 0; i < seats.size(); i++) {
	        Seat seat = seats.get(i);

	        JToggleButton seatButton = new JToggleButton(seat.getSeatType());
	        // Set color based on seat type
	        switch (seat.getStatus()) {
	            case "Available":
	                seatButton.setBackground(Color.GREEN);
	                break;
	            case "Reserved":
	                seatButton.setBackground(Color.YELLOW);
	                break;
	            case "Hold":
	                seatButton.setBackground(Color.RED);
	                break;
	            default:
	                seatButton.setBackground(Color.WHITE);
	                break;
	        }

	        // Set other properties of the button
	        seatButton.setToolTipText("Seat Status: " + seat.getStatus() + ", Seat Price: £" + seat.getPrice());

	        // Calculate row and column based on the index and number of columns
	        int row = i / columns;
	        int col = i % columns;

	        seatButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                if ("Hold".equals(seat.getStatus())) {
	                    // Display a message or take appropriate action
	                    JOptionPane.showMessageDialog(panel, "This seat is on HOLD and cannot be selected.", "Seat Hold", JOptionPane.INFORMATION_MESSAGE);
	                    seatButton.setSelected(false); // Deselect the button
	                } else if ("Reserved".equals(seat.getStatus())) {
	                    // Display a message or take appropriate action
	                    JOptionPane.showMessageDialog(panel, "This seat is already RESERVED and cannot be selected.", "Seat Reserved", JOptionPane.INFORMATION_MESSAGE);
	                    seatButton.setSelected(false); // Deselect the button
	                } else {
	                	if (seatButton.isSelected()) {
	                        if (selectedSeats.size() < selectedShow.getMaxSeatsPerCustomer()) {
	                            selectedSeats.add(seat);
	                            // Add seat label to lowerPanel
	                            lowerPanel.add(new JLabel("Type: " + seat.getSeatType() + ", Price: £" + seat.getPrice()));
	                            lowerPanel.revalidate(); // Refresh the layout
	                            lowerPanel.repaint(); // Repaint the panel
	                            UpdateTablesDAO.updateSeatStatus(selectedSeats, "Hold");
	                        } else {
	                            JOptionPane.showMessageDialog(panel, "You can't select another seat", "Error", JOptionPane.ERROR_MESSAGE);
	                            seatButton.setSelected(false); // Deselect the button
	                        }
	                    } else {
	                        selectedSeats.remove(seat);
	                        // Remove corresponding label from lowerPanel
	                        removeSeatLabel(lowerPanel, seat);
	                    }
	                    lowerPanel.revalidate(); // Refresh the layout
	                    lowerPanel.repaint(); // Repaint the panel
	                }
	            }

	        });
	        

	        // Add the button to the upper panel with GridBagLayout constraints
	        gbc.gridx = col;
	        gbc.gridy = row;
	        upperPanel.add(seatButton, gbc);
	    }
	    
	 // Add the "Buy Ticket" button to the buyTicketPanel
	    JButton buyTicketButton = new JButton("Buy Ticket");
	    buyTicketButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    buyTicketButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            double totalCost = calculateCost(selectedSeats);
	            int option = showConfirmationDialog(totalCost,lowerPanel);

	            if (option == JOptionPane.YES_OPTION) {
	                // User clicked "Yes," update seat statuses to "Reserved"
	                UpdateTablesDAO.updateSeatStatus(selectedSeats, "Reserved");
	                LocalDate currentDate = LocalDate.now();

	                // Define the date formatter
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	                // Format the current date using the formatter
	                String formattedDate = currentDate.format(formatter);
	                
	                // insert the new customer transaction to the database
	                int customerId = DataRetrieval.getCustomerId(customerUsername);
	                DatabaseDAO.insertTransactions(customerId, showId, selectedSeats.size(), totalCost, formattedDate);

	                // Ensure that lowerPanel is not null before attempting to use it
	                if (lowerPanel != null) {
	                    // Clear labels in the lowerPanel
	                    lowerPanel.removeAll();
	                    lowerPanel.revalidate();
	                    lowerPanel.repaint();
	                }
	            } else {
	                // User clicked "No" or closed the dialog, update seat statuses to "Available"
	                UpdateTablesDAO.updateSeatStatus(selectedSeats, "Available");
	            }

	            // Reset selected seats and update the label
	            selectedSeats.clear();
	        }
	    });
	    // Add glue above the button to center it vertically
	    buyTicketPanel.add(Box.createVerticalGlue());

	    buyTicketPanel.add(buyTicketButton);

	    // Add glue below the button to center it vertically
	    buyTicketPanel.add(Box.createVerticalGlue());
	    // Add the upper and lower panels to the main panel
	    panel.setLayout(new BorderLayout());
	    
	    panel.add(upperPanel, BorderLayout.NORTH);
	    panel.add(scrollPane, BorderLayout.CENTER);
	    panel.add(buyTicketPanel, BorderLayout.SOUTH);

	    // Repaint the panel to reflect the changes
	    panel.revalidate();
	    panel.repaint();
	}
	
	

	private void removeSeatLabel(JPanel panel, Seat seat) {
	    // Find the JLabel with text matching the deselected seat and remove it
	    Component[] components = panel.getComponents();
	    for (Component component : components) {
	        if (component instanceof JLabel) {
	            JLabel label = (JLabel) component;
	            if (label.getText().contains("Type: " + seat.getSeatType() + ", Price: £" + seat.getPrice())) {
	                panel.remove(label);
	                break; // Stop after removing the first matching label
	            }
	        }
	    }
	}

	private double calculateCost(ArrayList<Seat> seats) {
		double totalCost = seats.stream().mapToDouble(Seat::getPrice).sum();	
		return totalCost;
	}

	private void buyTicket(ArrayList<Seat> selectedSeats) {
	    // Implement the logic to finalize the purchase, update seat status to "Reserved," etc.
	    // Calculate total cost, update database, etc.
	}
	
	// Function to display a confirmation dialog with the total cost
	private int showConfirmationDialog(double totalCost, JPanel panel) {
	    String message = String.format("Total Cost: £%.2f\n\nProceed with the purchase?", totalCost);
	    int option = JOptionPane.showConfirmDialog(
	            this,
	            message,
	            "Confirm Purchase",
	            JOptionPane.YES_NO_OPTION
	    );

	    if (option == JOptionPane.YES_OPTION) {
	        // User clicked "Yes," update seat statuses to "Reserved"
	        UpdateTablesDAO.updateSeatStatus(selectedSeats, "Reserved");
	        
	        if (panel != null) {
	        	// Clear labels in the lowerPanel
	        	panel.removeAll();
	        	panel.revalidate();
	        	panel.repaint();
	        }
	    } else {
	        // User clicked "No" or closed the dialog, update seat statuses to "Available"
	        UpdateTablesDAO.updateSeatStatus(selectedSeats, "Available");
	    }

	    return option;
	}



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CustomerMainFrame("").setVisible(true);
            }
        });
    }
}
