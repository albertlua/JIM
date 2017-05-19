/**
 * Item.java
 * 
 * <i>Item</i> is an object representation for each row in the MySQL database.
 * 
 * Item.java uses:
 * <ul>
 *  <li>java.lang</li>
 * </ul>
 * 
 * @author Albert Lua
 * @author Filip Graniczny
 */
public class Item {
    /*# Instance Variables */
    private int id, checkNum;
    private String name, category, about, location;
    private boolean available;
    
    /*# Constructor */
    /**
     * Creates an <i>Item</i> that can be readily inserted or pulled from <i>Database</i>.
     * @param id ID number for the 
     */
    public Item(int id, String name, String category, String about, String location, boolean available, int checkNum) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.about = about;
        this.location = location;
        this.available = available;
        this.checkNum = checkNum;
    }
    
    /*# Methods */
    // Setter/getter
    /**
     * Sets <i>id</i> to input.
     * @param int ID for the object.
     */
    public void setID(int id) {
        this.id = id;
        return;
    }
    /**
     * Gets <i>id</i> and returns it.
     * @param void Takes no input.
     * @return <i>id</i> of the object.
     */
    public int getID() {
        return id;
    }
    /**
     * Sets <i>name</i> to input.
     * @param String name for the object.
     */
    public void setName(String name) {
        this.name = name;
        return;
    }
    /**
     * Gets <i>name</i> and returns it.
     * @param void Takes no input.
     * @return <i>name</i> of the object.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets <i>about</i> to input.
     * @param String Description of the object.
     */
    public void setAbout(String about) {
        this.about = about;
        return;
    }
    /**
     * Gets <i>about</i> and returns it.
     * @param void Takes no input.
     * @return Description of the object.
     */
    public String getAbout() {
        return about;
    }
    /**
     * Sets <i>location</i> to input.
     * @param String Location of the object.
     */
    public void setLocation(String location) {
        this.location = location;
        return;
    }
    /**
     * Gets <i>location</i> and returns it.
     * @param void Takes no input.
     * @return <i>location</i> of the object.
     */
    public String getLocation() {
        return location;
    }
    /**
     * Sets <i>available</i> to input.
     * @param boolean Availability of the object.
     */
    public void setAvailable(boolean available) {
        this.available = available;
        return;
    }
    /**
     * Gets <i>available</i> and returns it.
     * @param void Takes no input.
     * @return Availability of the object.
     */
    public boolean getAvailable() {
        return available;
    }
    /**
     * Sets <i>category</i> to input.
     * @param boolean Category of the object.
     */
    public void setCategory(String category) {
        this.category = category;
        return;
    }
    /**
     * Gets <i>category</i> and returns it.
     * @param void Takes no input.
     * @return Category of the object.
     */
    public String getCategory() {
        return category;
    }
    /**
     * Sets <i>id</i> to input.
     * @param int ID for the object.
     */
    public void setCheckNum(int checkNum) {
        this.checkNum = checkNum;
        return;
    }
    /**
     * Gets <i>id</i> and returns it.
     * @param void Takes no input.
     * @return <i>id</i> of the object.
     */
    public int getCheckNum() {
        return checkNum;
    }
}