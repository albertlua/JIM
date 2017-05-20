import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Window.Type;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
/**
 * JIMCheck.java
 * 
 * <hr>
 * 
 * Checks in or out an item from the database.
 * 
 * @author Albert Lua
 * @author Filip Graniczny
 */
public class JIMCheck {

	private boolean checked; // true means checking back in, false means checking out
	private Item item;
	private Database db;
	
	private JFrame frmReturn;
	private JTextField textField;
	private JLabel label;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Item item, Database db, boolean checked) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JIMCheck window = new JIMCheck(item, db, checked);
					window.frmReturn.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JIMCheck(Item item, Database db, boolean checked) {
		this.item = item;
		this.db = db;
		this.checked = checked;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReturn = new JFrame();
		frmReturn.setResizable(false);
		frmReturn.setIconImage(Toolkit.getDefaultToolkit().getImage(JIMCheck.class.getResource("/img/jim.png")));
		frmReturn.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		frmReturn.setType(Type.UTILITY);
		if (checked) {
			frmReturn.setTitle("Return "+item.getName());
		} else {
			frmReturn.setTitle("Checkout "+item.getName());
		}
		frmReturn.setBounds(100, 100, 400, 130);
		frmReturn.setLocationRelativeTo(null);
		frmReturn.getContentPane().setLayout(null);
		
		JLabel lblCheckNumber = new JLabel("Check Number:");
		lblCheckNumber.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCheckNumber.setBounds(10, 11, 86, 14);
		frmReturn.getContentPane().add(lblCheckNumber);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField.setBounds(106, 8, 278, 20);
		frmReturn.getContentPane().add(textField);
		textField.setColumns(10);
		if (!checked) {
			textField.setEditable(false);
			int checkNum = (int)(Math.random() * 999999 + 100000);
			textField.setText(""+checkNum);
			item.setCheckNum(checkNum);
			DBStatic.itemUpdate(item, db);
		}
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemCheck();
			}
		});
		
		label = new JLabel();
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setText("<dynamic>");
		if (checked) {
			label.setText("ENTER the check number you received when checking out.");
		} else {
			label.setText("SAVE the check number for when you return the item.");
		}
		label.setBounds(10, 36, 374, 14);
		frmReturn.getContentPane().add(label);
		
		button = new JButton("<dynamic>");
		button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button.setBackground(new Color(204, 153, 255));
		if (checked) {
			button.setText("Return Item");
		} else {
			button.setText("Checkout Item");
		}
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemCheck();
			}
		});
		button.setBounds(10, 61, 374, 23);
		frmReturn.getContentPane().add(button);
	}
	
	// Item return/checkout method
	private void itemCheck() {
		if (checked) {
			// Textfields are String objects
			String checkNum = ""+item.getCheckNum();
			System.out.println(checkNum);
			System.out.println(textField.getText());
			if (checkNum.equals(""+textField.getText())) {
				item.setAvailable(true);
				DBStatic.itemUpdate(item, db);
				JOptionPane.showMessageDialog(null, "Item successfully returned.");
				frmReturn.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "Check number incorrect.");
			}
		} else {
			item.setAvailable(false);
			DBStatic.itemUpdate(item, db);
			JOptionPane.showMessageDialog(null, "SAVE check number: "+textField.getText());
			JOptionPane.showMessageDialog(null, "Item checked out.");
			frmReturn.setVisible(false);
		}
	}

}
