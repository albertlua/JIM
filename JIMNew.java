import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * JIMNew.java
 * 
 * <hr>
 * 
 * Puts a new item in the database.
 * 
 * @author Albert Lua
 * @author Filip Graniczny
 */
public class JIMNew {

	private Item item;
	private Database db;
	private int id;
	
	private JFrame frmViewItem;
	private JTextField idNum;
	private JTextField name;
	private JTextField category;
	private JTextField location;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Database db) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JIMNew window = new JIMNew(db);
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
	public JIMNew(Database db) {
		this.db = db;
		id = DBStatic.generateID(db);
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
		frmViewItem.setTitle("New Item");
		frmViewItem.setIconImage(Toolkit.getDefaultToolkit().getImage(JIMNew.class.getResource("/img/jim.png")));
		frmViewItem.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		frmViewItem.setBounds(100, 100, 305, 300);
		frmViewItem.getContentPane().setLayout(null);
		frmViewItem.setLocationRelativeTo(null);
		
		JLabel lblIdNumber = new JLabel("ID Number:");
		lblIdNumber.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblIdNumber.setBounds(10, 14, 59, 14);
		frmViewItem.getContentPane().add(lblIdNumber);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblName.setBounds(10, 39, 59, 14);
		frmViewItem.getContentPane().add(lblName);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCategory.setBounds(10, 64, 59, 14);
		frmViewItem.getContentPane().add(lblCategory);
		
		JLabel lblAbout = new JLabel("About:");
		lblAbout.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAbout.setBounds(10, 114, 59, 14);
		frmViewItem.getContentPane().add(lblAbout);
		
		idNum = new JTextField();
		idNum.setFont(new Font("Tahoma", Font.PLAIN, 11));
		idNum.setEditable(false);
		idNum.setBounds(79, 11, 210, 20);
		frmViewItem.getContentPane().add(idNum);
		idNum.setColumns(10);
		idNum.setText(""+id);
		
		name = new JTextField();
		name.setFont(new Font("Tahoma", Font.PLAIN, 11));
		name.setColumns(10);
		name.setBounds(79, 36, 210, 20);
		frmViewItem.getContentPane().add(name);
		
		category = new JTextField();
		category.setFont(new Font("Tahoma", Font.PLAIN, 11));
		category.setColumns(10);
		category.setBounds(79, 61, 210, 20);
		frmViewItem.getContentPane().add(category);
		
		location = new JTextField();
		location.setText((String) null);
		location.setFont(new Font("Tahoma", Font.PLAIN, 11));
		location.setColumns(10);
		location.setBounds(79, 86, 210, 20);
		frmViewItem.getContentPane().add(location);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 139, 279, 79);
		frmViewItem.getContentPane().add(scrollPane);
		
		JTextArea about = new JTextArea();
		scrollPane.setViewportView(about);
		about.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JButton createItem = new JButton("Create Item");
		createItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				item = new Item(id, name.getText(), category.getText(), about.getText(), location.getText(), true, 0);
				DBStatic.itemCreate(item, db);
				JOptionPane.showMessageDialog(null, "Item successfully created.");
				frmViewItem.setVisible(false);
			}
		});
		createItem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		createItem.setBackground(new Color(204, 153, 255));
		createItem.setBounds(10, 229, 279, 23);
		frmViewItem.getContentPane().add(createItem);
		
		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLocation.setBounds(10, 89, 59, 14);
		frmViewItem.getContentPane().add(lblLocation);
	}
}
