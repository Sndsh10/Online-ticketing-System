package bcpa.frontend;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.JXDatePicker;

import bcpa.backend.VenueManager;
import bcpa.database.DataRetrieval;

public class MainManagerFrame extends JFrame {

    

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel rightPanel;

	public MainManagerFrame() {
        // Set frame properties
        setTitle("Main Manager Window");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        // Create a split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
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

    private JPanel createLeftPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 0, 10, 0); // Add margin between buttons

        // Create buttons for functionalities
        JButton addEventButton = new JButton("Add Event");
        JButton addShowButton = new JButton("Add Show");
        JButton cancelEventButton = new JButton("Cancel Event");
        JButton rescheduleEventButton = new JButton("Reschedule Event");
        JButton updateSeatsButton = new JButton("Update Seats");

        // Set preferred width for buttons
        addEventButton.setPreferredSize(new Dimension(250, addEventButton.getPreferredSize().height));
        addShowButton.setPreferredSize(new Dimension(250, addShowButton.getPreferredSize().height));
        cancelEventButton.setPreferredSize(new Dimension(250, cancelEventButton.getPreferredSize().height));
        rescheduleEventButton.setPreferredSize(new Dimension(250, rescheduleEventButton.getPreferredSize().height));
        updateSeatsButton.setPreferredSize(new Dimension(250, updateSeatsButton.getPreferredSize().height));
        
        // Add action listeners to the buttons
        addEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the button click, update the right panel accordingly
                updateRightPanel("Add Event");
            }
        });

        addShowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the button click, update the right panel accordingly
                updateRightPanel("Add Show");
            }
        });

        cancelEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the button click, update the right panel accordingly
                updateRightPanel("Cancel Event");
            }
        });

        rescheduleEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the button click, update the right panel accordingly
                updateRightPanel("Reschedule Event");
            }
        });
        
        updateSeatsButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		updateRightPanel("Update Seats");
        	}
        });


        // Add buttons to the left panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(addEventButton, constraints);

        constraints.gridy = 1;
        panel.add(addShowButton, constraints);

        constraints.gridy = 2;
        panel.add(cancelEventButton, constraints);

        constraints.gridy = 3;
        panel.add(rescheduleEventButton, constraints);
        
        constraints.gridy = 4;
        panel.add(updateSeatsButton, constraints);

        return panel;
    }

    private void updateRightPanel(String functionality) {
    	// Clear the right panel before updating
        rightPanel.removeAll();
        rightPanel.revalidate();
        rightPanel.repaint();

        // Implement the logic based on the selected functionality
        if ("Add Event".equals(functionality)) {
            // Display the form for adding an event
            displayAddEventForm();
        } else if ("Add Show".equals(functionality)) {
            // Display the form for adding a show
            displayAddShowForm();
        }
        else if ("Cancel Event".equals(functionality)) {
        	displayCancelEventForm();
        }
        else if ("Reschedule Event".equals(functionality)) {
        	displayRescheduleEventForm();
        }
        else if("Update Seats".equals(functionality)) {
        	displayUpdateSeatsForm();
        }
        // Add more conditions for other functionalities
    }
    
    private void displayUpdateSeatsForm() {
    	
    	JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel showNameLabel = new JLabel("Show Name:");
        JComboBox<String> showNameComboBox = new JComboBox<>(DataRetrieval.getShowNames().toArray(String[]::new));

        JLabel newSeatsLabel = new JLabel("Maximum Seats per Customer");
        JTextField newSeatsField = new JTextField(); // Add a JTextField for status

        JButton updateButton = new JButton("Update");

        formPanel.add(showNameLabel);
        formPanel.add(showNameComboBox);
        formPanel.add(newSeatsLabel);
        formPanel.add(newSeatsField);
        formPanel.add(new JLabel()); // Empty label for spacing
        formPanel.add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the "Cancel Event" button click
                // Retrieve the values from the form fields
                String showName = (String) showNameComboBox.getSelectedItem();
                int newSeats = Integer.valueOf(newSeatsField.getText());
                
                VenueManager.updateMaxSeatsPerCustomer(showName, newSeats);

                // For now, show a message indicating the event is cancelled
                JOptionPane.showMessageDialog(MainManagerFrame.this, "Show Updated successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                // Clear the form fields
                showNameComboBox.setSelectedIndex(0);
                newSeatsField.setText("");
            }
        });

        // Add the form panel to the right panel
        rightPanel.add(formPanel);
        rightPanel.revalidate();
        rightPanel.repaint();
	}

	private void displayAddEventForm() {
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Event Name:");
        JTextField nameField = new JTextField();

        JLabel startDateLabel = new JLabel("Starting Date:");
        JXDatePicker startDatePicker = new JXDatePicker(); // Use JXDatePicker for the start date

        JLabel endDateLabel = new JLabel("Ending Date:");
        JXDatePicker endDatePicker = new JXDatePicker(); // Use JXDatePicker for the end date
        
        JLabel statusLabel = new JLabel("Event Status: ");
        JComboBox<String> statusCombo = new JComboBox<>(new String[] {"Cancelled", "Scheduled", "Rescheduled"}) ;

        JButton addEventButton = new JButton("Add Event");

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(new JLabel());
        formPanel.add(startDateLabel);
        formPanel.add(startDatePicker);
        formPanel.add(new JLabel());
        formPanel.add(endDateLabel);
        formPanel.add(endDatePicker);
        formPanel.add(new JLabel());
        formPanel.add(statusLabel);
        formPanel.add(statusCombo);
        formPanel.add(new JLabel());
        formPanel.add(new JLabel());
        formPanel.add(addEventButton);

        addEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the "Add Event" button click
                // Retrieve the values from the text fields
                String eventName = nameField.getText();
                Date startDate = startDatePicker.getDate();
                Date endDate = endDatePicker.getDate();
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dateStart = dateFormat.format(startDate);
                String dateEnd = dateFormat.format(endDate);
                
                
                String status = (String) statusCombo.getSelectedItem();
                
                // DatabaseDAO.insertEvent(eventName, startDate, endDate);
                
                try {
                    VenueManager.addEvent(eventName, dateStart, dateEnd, status);
                    JOptionPane.showMessageDialog(MainManagerFrame.this, "Event added successfully to the events table in the database!",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                    // Clear the form fields
                    nameField.setText("");
                    startDatePicker.setDate(null);
                    endDatePicker.setDate(null);
                    statusCombo.setSelectedIndex(0); // Assuming "Scheduled" is the default option
                } catch (ParseException e1) {
                    JOptionPane.showMessageDialog(MainManagerFrame.this, e1.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add the form panel to the right panel
        rightPanel.add(formPanel);
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private void displayAddShowForm() {
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel eventNameLabel = new JLabel("Event Name:");
        JComboBox<String> eventNameComboBox = new JComboBox<>(DataRetrieval.getEventNames().toArray(String[]::new));

        JLabel showNameLabel = new JLabel("Show Name:");
        JTextField showNameField = new JTextField();

        JLabel showDateLabel = new JLabel("Show Date:");
        JXDatePicker showDatePicker = new JXDatePicker();

        JLabel maxSeatsLabel = new JLabel("Max Seats per Customer:");
        JTextField maxSeatsField = new JTextField();

        JButton addShowButton = new JButton("Add Show");

        formPanel.add(eventNameLabel);
        formPanel.add(eventNameComboBox);
        formPanel.add(showNameLabel);
        formPanel.add(showNameField);
        formPanel.add(showDateLabel);
        formPanel.add(showDatePicker);
        formPanel.add(maxSeatsLabel);
        formPanel.add(maxSeatsField);
        formPanel.add(new JLabel()); // Empty label for spacing
        formPanel.add(addShowButton);

        addShowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the "Add Show" button click
                // Retrieve the values from the form fields
                String eventName = (String) eventNameComboBox.getSelectedItem();
                String showName = showNameField.getText();
                System.out.println("Show name is: " + showName);
                // Use the JXDatePicker API to get the selected date
                Date selectedDate = showDatePicker.getDate();
                // Format the date as needed
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String showDate = dateFormat.format(selectedDate);
                int maxSeats = Integer.parseInt(maxSeatsField.getText());
                
                int eventId = DataRetrieval.getEventId(eventName);
                
                if(showName.isEmpty()) {
                	System.out.println("the field is empty");
                }
                else {
                	VenueManager.addShow(eventId, showName, showDate, maxSeats);
                	// For now, show a message indicating the show is added
                    JOptionPane.showMessageDialog(MainManagerFrame.this, "Show added successfully!",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                    eventNameComboBox.setSelectedIndex(0);
                    showNameField.setText("");
                    showDatePicker.setDate(null);
                    maxSeatsField.setText("");
                }

                
            }
        });

        // Add the form panel to the right panel
        rightPanel.add(formPanel);
        rightPanel.revalidate();
        rightPanel.repaint();
    }
    
    public void displayCancelEventForm() {
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel eventNameLabel = new JLabel("Event Name:");
        JComboBox<String> eventNameComboBox = new JComboBox<>(DataRetrieval.getEventNames().toArray(String[]::new));

        JLabel statusLabel = new JLabel("Event Status:");
        JTextField statusField = new JTextField(); // Add a JTextField for status

        JButton cancelEventButton = new JButton("Cancel Event");

        formPanel.add(eventNameLabel);
        formPanel.add(eventNameComboBox);
        formPanel.add(statusLabel);
        formPanel.add(statusField);
        formPanel.add(new JLabel()); // Empty label for spacing
        formPanel.add(cancelEventButton);

        cancelEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the "Cancel Event" button click
                // Retrieve the values from the form fields
                String eventName = (String) eventNameComboBox.getSelectedItem();
                String status = statusField.getText();
                
                VenueManager.cancelEvent(eventName, status);

                // For now, show a message indicating the event is cancelled
                JOptionPane.showMessageDialog(MainManagerFrame.this, "Event canceled successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                // Clear the form fields
                eventNameComboBox.setSelectedIndex(0);
                statusField.setText("");
            }
        });

        // Add the form panel to the right panel
        rightPanel.add(formPanel);
        rightPanel.revalidate();
        rightPanel.repaint();
    }
    
    public void displayRescheduleEventForm() {
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel eventNameLabel = new JLabel("Event Name:");
        JComboBox<String> eventNameComboBox = new JComboBox<>(DataRetrieval.getEventNames().toArray(String[]::new));

        JLabel startDateLabel = new JLabel("New Starting Date:");
        JXDatePicker newStartDatePicker = new JXDatePicker(); // Use JXDatePicker for the new start date

        JLabel endDateLabel = new JLabel("New Ending Date:");
        JXDatePicker newEndDatePicker = new JXDatePicker(); // Use JXDatePicker for the new end date

        JLabel statusLabel = new JLabel("New Event Status:");
        JComboBox<String> newStatusField = new JComboBox<>(new String[] {"Rescheduled", "Scheduled", "Cancelled"}); // Add a JTextField for the new status

        JButton rescheduleEventButton = new JButton("Reschedule Event");

        formPanel.add(eventNameLabel);
        formPanel.add(eventNameComboBox);
        formPanel.add(new JLabel());
        formPanel.add(startDateLabel);
        formPanel.add(newStartDatePicker);
        formPanel.add(new JLabel());
        formPanel.add(endDateLabel);
        formPanel.add(newEndDatePicker);
        formPanel.add(new JLabel());
        formPanel.add(statusLabel);
        formPanel.add(newStatusField);
        formPanel.add(new JLabel());
        formPanel.add(new JLabel());
        formPanel.add(rescheduleEventButton);

        rescheduleEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the "Reschedule Event" button click
                // Retrieve the values from the form fields
                String eventName = (String) eventNameComboBox.getSelectedItem();
                Date newStartDate = newStartDatePicker.getDate();
                Date newEndDate = newEndDatePicker.getDate();
                String newStatus = (String) newStatusField.getSelectedItem();
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String startDate = dateFormat.format(newStartDate);
                String endDate = dateFormat.format(newEndDate);

                VenueManager.rescheduleEvent(eventName, startDate, newStatus, endDate);

                // For now, show a message indicating the event is rescheduled
                JOptionPane.showMessageDialog(MainManagerFrame.this, "Event rescheduled successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                // Clear the form fields
                eventNameComboBox.setSelectedIndex(0);
                newStartDatePicker.setDate(null);
                newEndDatePicker.setDate(null);
                newStatusField.setSelectedIndex(0);
            }
        });

        // Add the form panel to the right panel
        rightPanel.add(formPanel);
        rightPanel.revalidate();
        rightPanel.repaint();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainManagerFrame().setVisible(true);
            }
        });
    }
}

