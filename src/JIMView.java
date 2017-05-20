import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window.Type;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * JIMView.java
 * 
 * <hr>
 * 
 * Views an item from the database.
 * 
 * @author Albert Lua
 * @author Filip Graniczny
 */
public class JIMView {

	private Item item;
	private Database db;
	private boolean editMode = false;
	
	private JFrame frmViewItem;
	private JTextField idNum;
	private JTextField name;
	private JTextField category;
	private JTextField location;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Item item, Database db) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JIMView window = new JIMView(item, db);
					window.frmViewItem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JIMView(Item item, Database db) {
		this.item = item;
		this.db = db;
		initialize();
		
		/*System.out.println(item.getID());
		System.out.println(item.getName());
		System.out.println(item.getAbout());
		System.out.println(item.getCategory());
		System.out.println(item.getAvailable());*/
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmViewItem = new JFrame();
		frmViewItem.setResizable(false);
		frmViewItem.setType(Type.UTILITY);
		frmViewItem.setTitle("View "+item.getName());
		frmViewItem.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\bhlua_000\\Google Drive\\2016-2017 Sophomore\\AP Computer Science\\Java Final Project\\eclipse\\JIM\\src\\jim.png"));
		frmViewItem.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		frmViewItem.setBounds(100, 100, 305, 360);
		frmViewItem.getContentPane().setLayout(null);
		frmViewItem.setLocationRelativeTo(null);
		
		JLabel lblIdNumber = new JLabel("ID Number:");
		lblIdNumber.setFont(new Font("Ubuntu Light", Font.PLAIN, 11));
		lblIdNumber.setBounds(10, 44, 59, 14);
		frmViewItem.getContentPane().add(lblIdNumber);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Ubuntu Light", Font.PLAIN, 11));
		lblName.setBounds(10, 69, 59, 14);
		frmViewItem.getContentPane().add(lblName);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setFont(new Font("Ubuntu Light", Font.PLAIN, 11));
		lblCategory.setBounds(10, 94, 59, 14);
		frmViewItem.getContentPane().add(lblCategory);
		
		JLabel lblAbout = new JLabel("About:");
		lblAbout.setFont(new Font("Ubuntu Light", Font.PLAIN, 11));
		lblAbout.setBounds(10, 147, 59, 14);
		frmViewItem.getContentPane().add(lblAbout);
		
		idNum = new JTextField();
		idNum.setFont(new Font("Ubuntu", Font.PLAIN, 11));
		idNum.setEditable(false);
		idNum.setBounds(79, 41, 210, 20);
		frmViewItem.getContentPane().add(idNum);
		idNum.setColumns(10);
		idNum.setText(""+item.getID());
		
		name = new JTextField();
		name.setFont(new Font("Ubuntu", Font.PLAIN, 11));
		name.setEditable(false);
		name.setColumns(10);
		name.setBounds(79, 66, 210, 20);
		frmViewItem.getContentPane().add(name);
		name.setText(item.getName());
		
		category = new JTextField();
		category.setFont(new Font("Ubuntu", Font.PLAIN, 11));
		category.setEditable(false);
		category.setColumns(10);
		category.setBounds(79, 91, 210, 20);
		frmViewItem.getContentPane().add(category);
		category.setText(item.getCategory());
		
		location = new JTextField();
		location.setText((String) null);
		location.setFont(new Font("Ubuntu", Font.PLAIN, 11));
		location.setEditable(false);
		location.setColumns(10);
		location.setBounds(79, 116, 210, 20);
		frmViewItem.getContentPane().add(location);
		location.setText(item.getLocation());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 172, 279, 79);
		frmViewItem.getContentPane().add(scrollPane);
		
		JTextArea about = new JTextArea();
		scrollPane.setViewportView(about);
		about.setFont(new Font("Ubuntu", Font.PLAIN, 11));
		about.setEditable(false);
		about.setText(item.getAbout());
		
		JButton btnItemCheckout = new JButton("Item Checkout");
		if (!item.getAvailable()) {
			btnItemCheckout.setText("Item Return");
		}
		btnItemCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (editMode) {
					int n = JOptionPane.showOptionDialog(new JFrame(), "Are you sure you want to delete?", 
					        null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
					        null, new Object[] {"Yes", "No"}, JOptionPane.YES_OPTION);

			        if (n == JOptionPane.YES_OPTION) {
			            DBStatic.itemDelete(item, db);
			            JOptionPane.showMessageDialog(null, "Item deleted.");
			            frmViewItem.setVisible(false);
			        }
				} else {
					if (item.getAvailable()) {
						JIMCheck.main(null, item, db, false);
					} else {
						JIMCheck.main(null, item, db, true);
					}
					frmViewItem.setVisible(false);
				}
			}
		});
		btnItemCheckout.setFont(new Font("Ubuntu", Font.PLAIN, 11));
		btnItemCheckout.setBackground(new Color(204, 153, 255));
		btnItemCheckout.setBounds(10, 262, 279, 23);
		frmViewItem.getContentPane().add(btnItemCheckout);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (editMode) {
					// In edit mode, turn off editing mode
					btnEdit.setText("Edit");
					if (!item.getAvailable()) {
						btnItemCheckout.setText("Item Return");
					} else {
						btnItemCheckout.setText("Item Checkout");
					}
					name.setEditable(false);
					category.setEditable(false);
					location.setEditable(false);
					about.setEditable(false);
					editMode = false;
					
					// Set variables and upload data
					item.setName(name.getText());
					item.setCategory(category.getText());
					item.setLocation(location.getText());
					item.setAbout(about.getText());
					DBStatic.itemUpdate(item, db);
				} else {
					// Not in edit mode, go into edit mode
					btnEdit.setText("Save Changes");
					btnItemCheckout.setText("Delete Item");
					name.setEditable(true);
					category.setEditable(true);
					location.setEditable(true);
					about.setEditable(true);
					editMode = true;
				}
			}
		});
		btnEdit.setFont(new Font("Ubuntu", Font.PLAIN, 11));
		btnEdit.setBackground(new Color(204, 153, 255));
		btnEdit.setBounds(10, 297, 279, 23);
		frmViewItem.getContentPane().add(btnEdit);
		
		// Change text and button enabled based on if item is available
		String availableMsg;
		int red = 0;
		int green = 0;
		if (item.getAvailable()) {
			availableMsg = "Item Available";
			green = 255;
		} else {
			availableMsg = "Item Unavailable";
			btnEdit.setEnabled(false);
			red = 255;
		}
		JLabel lblAvailable = new JLabel(availableMsg);
		lblAvailable.setFont(new Font("Ubuntu", Font.PLAIN, 11));
		lblAvailable.setForeground(new Color(red, green, 0));
		lblAvailable.setHorizontalAlignment(SwingConstants.CENTER);
		lblAvailable.setBounds(10, 11, 279, 14);
		frmViewItem.getContentPane().add(lblAvailable);
		
		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setFont(new Font("Ubuntu Light", Font.PLAIN, 11));
		lblLocation.setBounds(10, 119, 59, 14);
		frmViewItem.getContentPane().add(lblLocation);
	}
}
