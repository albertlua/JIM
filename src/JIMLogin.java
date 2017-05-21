import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
/**
 * JIMLogin.java
 * 
 * <hr>
 * 
 * This class is the GUI aspect of logging into the database.
 * 
 * @author Albert Lua
 * @author Filip Graniczny
 */
public class JIMLogin {
	
	private JFrame frmJimJava;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JTextField databaseField;
	private JTextField addressField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JIMLogin window = new JIMLogin();
					window.frmJimJava.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JIMLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJimJava = new JFrame();
		frmJimJava.setIconImage(Toolkit.getDefaultToolkit().getImage(JIMLogin.class.getResource("/img/jim.png")));
		frmJimJava.setResizable(false);
		frmJimJava.setBounds(100, 100, 350, 235);
		frmJimJava.setTitle("JIM - Java Inventory Management");
		frmJimJava.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJimJava.getContentPane().setLayout(null);
		frmJimJava.setLocationRelativeTo(null);
		
		JLabel lblJimJava = new JLabel("By Albert Lua, Filip Graniczny");
		lblJimJava.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblJimJava.setBounds(10, 181, 200, 15);
		lblJimJava.setHorizontalAlignment(SwingConstants.LEFT);
		frmJimJava.getContentPane().add(lblJimJava);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUsername.setBounds(10, 38, 90, 14);
		frmJimJava.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPassword.setBounds(10, 66, 90, 14);
		frmJimJava.getContentPane().add(lblPassword);
		
		JLabel lblDatabaseName = new JLabel("Database Name:");
		lblDatabaseName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDatabaseName.setBounds(10, 94, 90, 14);
		frmJimJava.getContentPane().add(lblDatabaseName);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAddress.setBounds(10, 122, 90, 14);
		frmJimJava.getContentPane().add(lblAddress);
		
		JLabel lblPleaseEnterYour = new JLabel("Please enter your database credentials below:");
		lblPleaseEnterYour.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblPleaseEnterYour.setBounds(10, 11, 324, 14);
		frmJimJava.getContentPane().add(lblPleaseEnterYour);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnLogin.setBackground(new Color(204, 153, 255));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		btnLogin.setBounds(10, 147, 324, 23);
		frmJimJava.getContentPane().add(btnLogin);
		
		JLabel lblVAlpha = new JLabel(JIMMain.getVERSION());
		lblVAlpha.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblVAlpha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVAlpha.setBounds(220, 182, 114, 14);
		frmJimJava.getContentPane().add(lblVAlpha);
		
		usernameField = new JTextField();
		usernameField.setToolTipText("Username for the MySQL user.");
		usernameField.setBounds(110, 35, 224, 20);
		frmJimJava.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		usernameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		passwordField.setToolTipText("Password for the MySQL user.");
		passwordField.setBounds(110, 63, 224, 20);
		frmJimJava.getContentPane().add(passwordField);
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		
		databaseField = new JTextField();
		databaseField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		databaseField.setToolTipText("Name of the database in MySQL.");
		databaseField.setBounds(110, 91, 224, 20);
		frmJimJava.getContentPane().add(databaseField);
		databaseField.setColumns(10);
		databaseField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		
		addressField = new JTextField();
		addressField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		addressField.setToolTipText("Address of the MySQL database.");
		addressField.setBounds(110, 119, 224, 20);
		frmJimJava.getContentPane().add(addressField);
		addressField.setColumns(10);
		addressField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		
		// Read config file if available about saved login info
		usernameField.setText(readConfig.getDBUsername());
		passwordField.setText(readConfig.getDBPassword());
		databaseField.setText(readConfig.getDBName());
		addressField.setText(readConfig.getDBURL());
	}
	
	/**
	 * Login method to allow login via button or enter.
	 * @param void Takes no input.
	 */
	@SuppressWarnings("deprecation")
	private void login() {
		// Attempt to connect
		Database db = new Database(usernameField.getText(), passwordField.getText(),
									databaseField.getText(), addressField.getText());
		if (db.connect()) {
			// Connection success
			JIMMain.main(null, db);
			
			// Save to config for future
			readConfig.setDBUsername(usernameField.getText());
			readConfig.setDBPassword(passwordField.getText());
			readConfig.setDBName(databaseField.getText());
			readConfig.setDBURL(addressField.getText());
			
			frmJimJava.setVisible(false);
		} else{
			JOptionPane.showMessageDialog(null, "Could not login to database.");
		}
	}
}
