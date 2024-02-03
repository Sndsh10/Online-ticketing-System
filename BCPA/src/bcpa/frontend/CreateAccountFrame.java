package bcpa.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bcpa.backend.Customer;
import bcpa.backend.Profile;
import bcpa.backend.User;
import bcpa.database.Authentication;
import bcpa.database.DatabaseDAO;

public class CreateAccountFrame extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateAccountFrame() {
        // Set frame properties
        setTitle("Create Account");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose the frame on close
        setLocationRelativeTo(null); // Center the frame on the screen

        // Create panels
        JPanel createAccountPanel = createCreateAccountPanel();

        // Set layout and add panels
        setLayout(new GridLayout(1, 1));
        add(createAccountPanel);
    }

    private JPanel createCreateAccountPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add some padding

        // Add create account form components
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        JButton createAccountButton = new JButton("Create Account");

        // Set constraints and add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(0, 0, 10, 0); // Add margin between username label and field
        panel.add(usernameLabel, constraints);

        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0, 0, 10, 0); // Add margin between password label and field
        panel.add(usernameField, constraints);

        constraints.gridy = 1;
        panel.add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(20, 0, 0, 0); // Add margin above the create account button
        panel.add(createAccountButton, constraints);

        // Add action listener to the create account button
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle account creation logic here
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                Customer customer = new Customer(username, password);
                
                // Attempt to create the account using the Customer object
                boolean accountCreated = Authentication.usernameExists(customer.getUsername());

                if (!accountCreated) {
                	
                	DatabaseDAO.insertCustomer(customer.getUsername(), customer.getPassword());
                    // For now, just show a success message
                    JOptionPane.showMessageDialog(CreateAccountFrame.this, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                    // Redirect to CustomerFrame
                    dispose(); // Close the current CreateAccountFrame
                    CustomerFrame customerFrame = new CustomerFrame();
                    customerFrame.setVisible(true);
                } else {
                    // Show an error message if the account creation fails
                    JOptionPane.showMessageDialog(CreateAccountFrame.this, "Username already exists. Please choose another username.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CreateAccountFrame().setVisible(true);
            }
        });
    }
}

