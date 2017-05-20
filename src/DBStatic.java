/**
 * DBStatic.java
 * 
 * <i>IDHandling</i> contains static methods to do more specific tasks.
 * 
 * DBStatic.java uses:
 * <ul>
 *  <li>java.lang</li>
 *  <li>java.sql</li>
 * </ul>
 * 
 * @author Albert Lua
 * @author Filip Graniczny
 */
abstract public class DBStatic {
    /*# ID Methods */
    /**
     * Generates a unique random ID that is not already within the database.
     * @param Database A <i>Database</i> object that will be checked against.
     * @return The unique ID that can be used in the MySQL database.
     */
    public static int generateID(Database db) {
        int id = (int)(Math.random() * 999999 + 100000);
        try {
            while (checkID(db, id) && (id<100000 && id>999999)) {
                id = (int)(Math.random() * 999999 + 100000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    /**
     * Checks if an ID number already exists in the database.
     * @param Database A <i>Database</i> object that will be checked against.
     * @return Whether the <i>id</i> exists in <i>Database</i>.
     */
    public static boolean checkID(Database db, int id) {
        try {
            if (db.executeQuery("SELECT EXISTS("+
                "SELECT * FROM ITEMS WHERE id = "+id+")").getBoolean(0)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /*# Data Tables */
    /**
     * Sends SQL data to the database to create the appropriate tables.
     * @param void Database A <i>Database</i> object to run <i>updateQuery()</i>.
     */
    public static void generateTable(Database db) {
        // Check if table exists
        if (!db.tableExists("ITEMS")) {
            System.out.println(db.updateQuery("CREATE TABLE ITEMS ("+
                    "id INT not NULL UNIQUE, "+
                    "name VARCHAR(255) not NULL, "+
                    "category VARCHAR(255) not NULL, "+
                    "about TEXT not NULL, "+
                    "location TEXT not NULL, "+
                    "available BOOLEAN not NULL, " +
                    "checkNum INT not NULL)"));
        } else {
            System.out.println("Table not created.");
        }
        return;
    }
    
    /*# Item Manipulation */
    /**
     * Updates selected item in the database.
     * @param item The item to upload.
     * @param db The database to upload the item to.
     */
    public static void itemUpdate(Item item, Database db) {
    	int availableInt;
    	if (item.getAvailable()) {
    		availableInt = 1;
    	} else {
    		availableInt = 0;
    	}
    	db.updateQuery("UPDATE ITEMS "
    			+"SET name=\""+item.getName()+"\", category=\""+item.getCategory()+"\", available=\""+availableInt
    			+"\", about=\""+item.getAbout()+"\", location=\""+item.getLocation()+"\", checkNum=\""+item.getCheckNum()
    			+"\" WHERE id="+item.getID());
    	return;
    }
    /**
     * Adds an item to the database.
     * @param item The item to create.
     * @param db The database to put the new item into.
     */
    public static void itemCreate(Item item, Database db) {
    	int availableInt;
    	if (item.getAvailable()) {
    		availableInt = 1;
    	} else {
    		availableInt = 0;
    	}
    	db.updateQuery("INSERT INTO ITEMS "
    			+"VALUES (\""+item.getID()+"\", \""+item.getName()+"\", \""+item.getCategory()+"\", \""+item.getAbout()+"\", \""+item.getLocation()
    			+"\", \""+availableInt+"\", \""+item.getCheckNum()+"\")");
    	return;
    }
    /**
     * Deletes an item from the database.
     * @param item The item to delete.
     * @param db The database to remove the item from.
     */
    public static void itemDelete(Item item, Database db) {
    	db.updateQuery("DELETE FROM ITEMS WHERE id="+item.getID());
    	return;
    }
}