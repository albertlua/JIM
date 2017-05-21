import java.sql.*;
/**
 * Database.java
 * 
 * <i>Database</i> is a way to connect to a database and pull data from one with relative ease.
 * 
 * Database.java uses:
 * <ul>
 *  <li>java.sql</li>
 *  <li>java.lang</li>
 * </ul>
 * 
 * @author Albert Lua
 * @author Filip Graniczny
 */
public class Database {
    /*# Instance Variables */
    private String username, password, name, address;
    private Connection conn;
    private Statement stmt;
    
    /*# Constructor */
    /**
     * Object that can manipulate the MySQL database. Takes in details of connection to
     * create an object representation.
     * @param username Username for the connection.
     * @param password Password for the connection.
     * @param name Name of the database.
     * @param address Address of the connection.
     */
    public Database(String username, String password, String name, String address) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
    }
    
    /*# Methods */
    /**
     * Attempts a connection to the MySQL database and returns the success of the connection.
     * @param void Takes no input.
     * @return If the connection is successful.
     */
    public boolean connect() {
    	String url = "jdbc:mysql://"+address+"/"+name;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.conn = DriverManager.getConnection(url, this.username, this.password);
            System.err.println("MySQL Connection Success");
            
            // Create Statement object to run SQL queries
            this.stmt = this.conn.createStatement();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    // Setter/getter methods
    /**
     * Sets <i>username</i> to input.
     * @param String New username for the connection.
     */
    public void setUsername(String username) {
        this.username = username;
        return;
    }
    /**
     * Gets <i>username</i> and returns it.
     * @param void Takes no input.
     * @return <i>username</i> for the connection.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Sets <i>password</i> to input.
     * @param String New password for the connection.
     */
    public void setPassword(String password) {
        this.password = password;
        return;
    }
    /**
     * Sets <i>name</i> to input.
     * @param String New name for the connection.
     */
    public void setName(String name) {
        this.name = name;
        return;
    }
    /**
     * Gets <i>name</i> and returns it.
     * @param void Takes no input.
     * @return Database <i>name</i> for the connection.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets <i>address</i> to input.
     * @param String New address for the connection.
     */
    public void setAddress(String address) {
        this.address = address;
        return;
    }
    /**
     * Gets <i>address</i> and returns it.
     * @param void Takes no input.
     * @return <i>address</i> for the connection.
     */
    public String getAddress() {
        return address;
    }
    /**
     * Gets <i>conn</i> and returns it.
     * @return <i>Connection</i> object of the <i>Database</i>.
     */
    public Connection getConn() {
    	return conn;
    }
    
    // SQL related methods
    /**
     * Sends a query to receive data from the MySQL database.
     * @param String SQL query to pull from the database.
     * @return Information returned from the SQL database.
     */
    public ResultSet executeQuery(String query) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(query);
            System.err.println("Query Success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return rs;
    }
    /**
     * Sends a query to run an action MySQL database.
     * @param String SQL query to pull from the database.
     * @return Information returned from the SQL database.
     */
    public int updateQuery(String query) {
        int i = -1;
        try {
            i = stmt.executeUpdate(query);
            System.err.println("Query Success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return i;
    }
    /**
     * Checks if a certain table exists in the MySQL database.
     * @param String Name of the table to check
     * @return Whether the table exists in the MySQL database.
     */
    public boolean tableExists(String table) {
        // SQL tables must be all caps
        table.toUpperCase();
        
        try {
            DatabaseMetaData md = conn.getMetaData();
            ResultSet rs = md.getTables(null, null, table, null);
            
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
}