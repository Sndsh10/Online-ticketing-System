package bcpa.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class VenueManagerFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public VenueManagerFrame(MainFrame mainFrame) {
        // Set frame properties
        setTitle("Venue Manager Login");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        // Create panels
        JPanel imagePanel = createImagePanel();
        JPanel loginPanel = createLoginPanel(mainFrame); // Pass the reference to MainFrame

        // Set layout and add panels
        setLayout(new GridLayout(1, 2));
        add(imagePanel);
        add(loginPanel);
    }

    private JPanel createImagePanel() {
        JPanel panel = new JPanel();

        // Load the image
        ImageIcon originalImageIcon = new ImageIcon("images/theater.jpg");
        Image originalImage = originalImageIcon.getImage();

        // Calculate the scaled dimensions
        int panelWidth = getWidth() / 2; // Assuming the image panel takes half of the frame width
        int panelHeight = getHeight();
        Image scaledImage = originalImage.getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);

        // Create a new ImageIcon with the scaled image
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

        // Create a JLabel with the scaled image
        JLabel imageLabel = new JLabel(scaledImageIcon);

        // Add the JLabel to the panel
        panel.add(imageLabel);

        return panel;
    }

    private JPanel createLoginPanel(MainFrame mainFrame) {
    	JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add some padding
        panel.setBackground(Color.WHITE); // Set background color to white

        // Add login form components
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

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
        constraints.insets = new Insets(20, 0, 0, 0); // Add margin above the login button
        JButton styledLoginButton = new JButton("Login");
        styledLoginButton.setBackground(new Color(0, 0, 128)); // Dark blue background
        styledLoginButton.setForeground(Color.WHITE); // White text color
        panel.add(styledLoginButton, constraints);

        // Add action listener to the login button
        styledLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle login logic here
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                // Perform authentication and open the main venue manager window if successful
                if (authenticate(username, password)) {
                    openVenueManagerMainWindow();
                } else {
                    JOptionPane.showMessageDialog(VenueManagerFrame.this, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return panel;
    }

    // Override getWidth and getHeight methods
    @Override
    public int getWidth() {
        return super.getWidth();
    }

    @Override
    public int getHeight() {
        return super.getHeight();
    }

    private boolean authenticate(String username, String password) {
        if(username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
        	JOptionPane.showMessageDialog(this, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
        	return true;
        }
        JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    private void openVenueManagerMainWindow() {
        MainManagerFrame managerFrame = new MainManagerFrame();
        managerFrame.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VenueManagerFrame(new MainFrame()).setVisible(true);
            }
        });
    }
}
