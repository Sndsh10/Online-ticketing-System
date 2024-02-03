package bcpa.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class MainFrame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton loginButton;
    private JComboBox<String> userTypeComboBox;

    public MainFrame() {
        setTitle("Bucks Centre for the Performing Arts");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeComponents();
        addComponentsToFrame();

        setVisible(true);
    }

    private void initializeComponents() {
        userTypeComboBox = new JComboBox<>(new String[]{"Venue Manager", "Ticket Agent", "Customer"});
        loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedUserType = (String) userTypeComboBox.getSelectedItem();
                openUserInterface(selectedUserType);
            }
        });
    }

    private void addComponentsToFrame() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(new JLabel("Select User Type:"));
        panel.add(userTypeComboBox);
        panel.add(loginButton);
        
        // Add an empty border with a top margin of 100
        panel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));

        getContentPane().add(panel);
    }

    private void openUserInterface(String userType) {
        // Depending on the userType, open the corresponding UI
        if ("Venue Manager".equals(userType)) {
            openVenueManagerUI(this); // Pass the reference to MainFrame
        } else if ("Ticket Agent".equals(userType)) {
             openTicketAgentUI(this);
        } else if ("Customer".equals(userType)) {
             openCustomerUI(this);
        }
    }

    private void openTicketAgentUI(MainFrame mainFrame) {
    	TicketAgentFrame ticketAgentFrame = new TicketAgentFrame(mainFrame);
    	ticketAgentFrame.setVisible(true);
        setVisible(false); // Hide the MainFrame
	}

	private void openCustomerUI(MainFrame mainFrame) {
		CustomerFrame customerFrame = new CustomerFrame();
		customerFrame.setVisible(true);
        setVisible(false); // Hide the MainFrame
		
	}

	private void openVenueManagerUI(MainFrame mainFrame) {
        VenueManagerFrame venueManagerFrame = new VenueManagerFrame(mainFrame);
        venueManagerFrame.setVisible(true);
        setVisible(false); // Hide the MainFrame
    }
    
 // New method to get the image panel dimensions
    public int getImagePanelWidth() {
        // Replace this with the actual width of your image panel
        return 400;
    }

    // New method to get the image panel dimensions
    public int getImagePanelHeight() {
        // Replace this with the actual height of your image panel
        return 600;
    }

    public static void main(String[] args) throws ParseException {
    	
    	// run the GUI for the online ticket system
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}
