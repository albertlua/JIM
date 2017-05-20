import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;

import net.proteanit.sql.DbUtils;
/**
 * JIMMain.java
 * 
 * <hr>
 * 
 * JIM is an inventory management software running on Java.
 * 
 * @author Albert Lua
 * @author Filip Graniczny
 */
public class JIMMain {

	private Database db;
	
	private JFrame frmJimJava;
	
	private static String VERSION = "v1.1 BETA";
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnReloadData;
	private JLabel lblNewLabel;
	private JLabel lblDoubleClick;
	private JButton btnNewItem;
	private JLabel lblDataRefreshesEvery;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Database db) {
		// Check for tables required
		if (!db.tableExists("ITEMS")) {
			JOptionPane.showMessageDialog(null, "No tables in database. Will create tables.");
			DBStatic.generateTable(db);
			JOptionPane.showMessageDialog(null, "Tables are now created.");
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JIMMain window = new JIMMain(db);
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
	public JIMMain(Database db) {
		this.db = db;
		initialize();
		Timer timer = new Timer();
		timer.schedule(new dataCollection(), 0, 5000);
	}
	
	// Timer
	class dataCollection extends TimerTask {
	    public void run() {
	    	getData(db);
	    }
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frmJimJava = new JFrame();
		frmJimJava.setTitle("JIM - Java Inventory Management");
		frmJimJava.setIconImage(Toolkit.getDefaultToolkit().getImage(JIMMain.class.getResource("/img/jim.png")));
		frmJimJava.setBounds(100, 100, 690, 420);
		frmJimJava.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJimJava.setLocationRelativeTo(null);
		frmJimJava.setMinimumSize(new Dimension(400, 200));
		
		scrollPane = new JScrollPane();
		
		btnReloadData = new JButton("Reload Data");
		btnReloadData.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnReloadData.setBackground(new Color(204, 153, 255));
		btnReloadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getData(db);
			}
		});
		
		lblNewLabel = new JLabel(VERSION);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		lblDoubleClick = new JLabel("* double click on entry to view item");
		lblDoubleClick.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		btnNewItem = new JButton("New Item");
		btnNewItem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JIMNew.main(null, db);
			}
		});
		btnNewItem.setBackground(new Color(204, 153, 255));
		
		lblDataRefreshesEvery = new JLabel("Data refreshes every 5 seconds.");
		lblDataRefreshesEvery.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GroupLayout groupLayout = new GroupLayout(frmJimJava.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnReloadData)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDataRefreshesEvery)
					.addPreferredGap(ComponentPlacement.RELATED, 414, Short.MAX_VALUE)
					.addComponent(btnNewItem, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 376, Short.MAX_VALUE)
					.addComponent(lblDoubleClick)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnReloadData)
						.addComponent(btnNewItem)
						.addComponent(lblDataRefreshesEvery))
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblDoubleClick))
					.addContainerGap())
		);
		
		table = new JTable() {  
			public boolean isCellEditable(int row, int column){  
				return false;  
			}
		};
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		frmJimJava.getContentPane().setLayout(groupLayout);
		getData(db);
		
		// Table listener to run editor
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
		        JTable table = (JTable)me.getSource();
		        Point p = me.getPoint();
		        int row = table.rowAtPoint(p);
		        if (me.getClickCount() == 2 && row!=-1) {
		        	JIMView.main(null, new Item((int)table.getValueAt(row, 0), (String)table.getValueAt(row, 1), (String)table.getValueAt(row, 2),
		        			(String)table.getValueAt(row, 3), (String)table.getValueAt(row, 4), (boolean)table.getValueAt(row, 5),
		        			(int)table.getModel().getValueAt(row, 6)), db);
		        }
		    }
		});
	}
	
	/*# JIM Methods */
	/**
	 * Gets and returns the version number/name of the software.
	 * @param void Takes no input.
	 * @return The version number/name.
	 */
	public static String getVERSION() {
		return VERSION;
	}
	/**
	 * Receive all the data from the MySQL database.
	 * @param db The <i>Database</i> object to pull data from.
	 */
	public void getData(Database db) {
		try {
			// Pull data from MySQL database
			PreparedStatement pst = db.getConn().prepareStatement("SELECT * FROM ITEMS");
			ResultSet rs = pst.executeQuery();
			
			// Show data in table
			table.setModel(DbUtils.resultSetToTableModel(rs));
			table.getColumnModel().getColumn(0).setMaxWidth(50);
			table.getColumnModel().getColumn(5).setMaxWidth(60);
			//table.getColumnModel().getColumn(6).setMaxWidth(0);
			table.getColumnModel().removeColumn(table.getColumnModel().getColumn(table.getColumnCount()-1));
			System.err.println("Data pulled successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
}