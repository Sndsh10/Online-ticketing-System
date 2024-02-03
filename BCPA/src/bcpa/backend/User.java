package bcpa.backend;

import java.util.ArrayList;
import java.util.List;

public class User {
	private Profile profile;
    private String username;
    private String password;
    private static List<User> users = new ArrayList<>();

    public User(String username, String password) {
        this.setPassword(password);
        this.setUsername(username);
        this.setProfile(new Profile());
    }

    public static boolean createAccount(User user) {
        // Check if the username already exists
        for (User existingUser : getUsers()) {
            if (existingUser.getUsername().equals(user.getUsername())) {
                //JOptionPane.showMessageDialog(null, "Username already exists. Please choose another username.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        // Add the user to the list
        getUsers().add(user);
        return true;
    }

    public static boolean login(String username, String password) {
        // Your login logic here
        // Check if the username and password match any existing user
        for (User existingUser : getUsers()) {
            if (existingUser.getUsername().equals(username) && existingUser.getPassword().equals(password)) {
                return true; // Login successful
            }
        }

        return false; // Login failed
    }

    public static List<User> getUsers() {
        return users;
    }

	/**
	 * @return the profile
	 */
	public Profile getProfile() {
		return profile;
	}

	/**
	 * @param profile the profile to set
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Username: " + this.getUsername() + ", Password: " + this.getPassword();
	}
}
