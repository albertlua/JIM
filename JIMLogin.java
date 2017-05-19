import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
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
		frmJimJava.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\bhlua_000\\Google Drive\\2016-2017 Sophomore\\AP Computer Science\\Java Final Project\\eclipse\\JIM\\src\\jim.png"));
		frmJimJava.setResizable(false);
		frmJimJava.setBounds(100, 100, 350, 200);
		frmJimJava.setFont(new Font("Ubuntu Light", Font.PLAIN, 12));
		frmJimJava.setTitle("JIM - Java Inventory Management");
		frmJimJava.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJimJava.getContentPane().setLayout(null);
		frmJimJava.setLocationRelativeTo(null);
		
		JLabel lblJimJava = new JLabel("By Albert Lua, Filip Graniczny");
		lblJimJava.setBounds(10, 155, 200, 15);
		lblJimJava.setHorizontalAlignment(SwingConstants.LEFT);
		lblJimJava.setFont(new Font("Ubuntu", Font.BOLD, 12));
		frmJimJava.getContentPane().add(lblJimJava);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Ubuntu Light", Font.PLAIN, 11));
		lblUsername.setBounds(10, 36, 90, 14);
		frmJimJava.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Ubuntu Light", Font.PLAIN, 11));
		lblPassword.setBounds(10, 56, 90, 14);
		frmJimJava.getContentPane().add(lblPassword);
		
		JLabel lblDatabaseName = new JLabel("Database Name:");
		lblDatabaseName.setFont(new Font("Ubuntu Light", Font.PLAIN, 11));
		lblDatabaseName.setBounds(10, 76, 90, 14);
		frmJimJava.getContentPane().add(lblDatabaseName);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Ubuntu Light", Font.PLAIN, 11));
		lblAddress.setBounds(10, 96, 90, 14);
		frmJimJava.getContentPane().add(lblAddress);
		
		JLabel lblPleaseEnterYour = new JLabel("Please enter your database credentials below:");
		lblPleaseEnterYour.setFont(new Font("Ubuntu Light", Font.PLAIN, 11));
		lblPleaseEnterYour.setBounds(10, 11, 249, 14);
		frmJimJava.getContentPane().add(lblPleaseEnterYour);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(new Color(204, 153, 255));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		btnLogin.setFont(new Font("Ubuntu", Font.PLAIN, 11));
		btnLogin.setBounds(10, 121, 324, 23);
		frmJimJava.getContentPane().add(btnLogin);
		
		JLabel lblVAlpha = new JLabel(JIMMain.getVERSION());
		lblVAlpha.setFont(new Font("Ubuntu Light", Font.PLAIN, 8));
		lblVAlpha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVAlpha.setBounds(220, 156, 114, 14);
		frmJimJava.getContentPane().add(lblVAlpha);
		
		usernameField = new JTextField();
		usernameField.setToolTipText("Username for the MySQL user.");
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 9));
		usernameField.setBounds(110, 35, 224, 15);
		frmJimJava.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		usernameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Password for the MySQL user.");
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 9));
		passwordField.setBounds(110, 53, 224, 15);
		frmJimJava.getContentPane().add(passwordField);
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		
		databaseField = new JTextField();
		databaseField.setToolTipText("Name of the database in MySQL.");
		databaseField.setFont(new Font("Tahoma", Font.PLAIN, 9));
		databaseField.setBounds(110, 73, 224, 15);
		frmJimJava.getContentPane().add(databaseField);
		databaseField.setColumns(10);
		databaseField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		
		addressField = new JTextField();
		addressField.setToolTipText("Address of the MySQL database.");
		addressField.setFont(new Font("Tahoma", Font.PLAIN, 9));
		addressField.setBounds(110, 93, 224, 15);
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
