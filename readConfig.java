import java.io.*;
import java.util.*;
public abstract class readConfig
{
    //set config file location/name.
    private static File file = new File("config.jim");
    /**
     * Checks if configuration file exists.
     * @return true if exists, false if otherwise.
     */
    public static boolean ifExists(){
        if(file.exists()){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * Verifies if file exists, if not generates default file (config.jim). Returns error if exception generated following program run.
     */
    public static void loadFile(){
        if(!ifExists()){
            try{
            file.createNewFile();
            FileWriter write = new FileWriter(file);
            write.write("Java Inventory Management (JIM). Authors - Albert Lua, Filip Graniczny\n");
            write.write("Database URL: \n");
            write.write("Database Username: \n");
            write.write("Database Password: \n");
            write.write("Database Name: \n");
            write.close();
            
        }catch(IOException e){
            System.err.println("Failed to load file! loadFile() failed to run correctly");
        }
        } 
    }
    /**
     * Used by setter methods in order to save modified array lists into the configuration file.
     * @param ArrayList updated by the setter method.
     */
     public static void writeFile(ArrayList<String> list){
            try{
            file.createNewFile();
            FileWriter write = new FileWriter(file);
            write.write("Java Inventory Management (JIM). Authors - Albert Lua, Filip Graniczny\n");
            write.write(list.get(1)+"\n");
            write.write(list.get(2)+"\n");
            write.write(list.get(3)+"\n");
            write.write(list.get(4)+"\n");
            write.close();
            
        }catch(IOException e){
            System.err.println("Failed to load file! writeFile() failed to run correctly");
        }
    }
    /**
     * Reads file and turns each line into an a value within an ArrayList<String>. Returns exception if failed to read from file.
     * @return ArrayList<String> containing lines of config.
     */
    public static ArrayList<String> getFileList(){
        loadFile();
         ArrayList<String> list = new ArrayList<String>();
         try{
         Scanner scan = new Scanner(file);
          while(scan.hasNextLine()){
             list.add(scan.nextLine());
         }
         
         scan.close();
         return list;
        }catch(FileNotFoundException e){
            System.err.println("File not found! getFileList() failed to run correctly!");
            return null;
        }
    
    }
    /**
     * Returns saved MySQL database URL.
     * @return String with value of database URL. Null if default value.
     */
    public static String getDBURL(){
        String ret;
        ret = getFileList().get(1);
        ret = ret.substring(14);
        if(ret.equals("")){
            return null;
        }
        return ret;
    }
    /**
     * Returns saved MySQL database username.
     * @return String with value of database username. Null if default value.
     */
    public static String getDBUsername(){
        String ret;
        ret = getFileList().get(2);
        ret = ret.substring(19);
        if(ret.equals("")){
            return null;
        }
        return ret;
    }
    /**
     * Returns saved MySQL database password.
     * @return String with value of database password. Null if default value.
     */
    public static String getDBPassword(){
        String ret;
        ret = getFileList().get(3);
        ret = ret.substring(19);
        if(ret.equals("")){
            return null;
        }
        return ret;
    }
    /**
     * Returns saved MySQL database name.
     * @return String with value of database name. Null if default value.
     */
    public static String getDBName(){
        String ret;
        ret = getFileList().get(4);
        ret = ret.substring(15);
        if(ret.equals("")){
            return null;
        }
        return ret;
    }
    /**
     * Updates saved MySQL database URL.
     * @param String with value of new database URL.
     */
    public static void setDBURL(String URL){
        ArrayList<String> list;
        String st = getFileList().get(1);
        st = st.substring(0,14);
        list = getFileList();
        st += URL;
        list.set(1,st);
        writeFile(list);
    }
    /**
     * Updates saved MySQL database username.
     * @param String with value of new database username.
     */
    public static void setDBUsername(String Username){
        ArrayList<String> list;
        String st = getFileList().get(2);
        st = st.substring(0,19);
        list = getFileList();
        st += Username;
        list.set(2,st);
        writeFile(list);
    }
    /**
     * Updates saved MySQL database password.
     * @param String with value of new database password.
     */
    public static void setDBPassword(String Password){
        ArrayList<String> list;
        String st = getFileList().get(3);
        st = st.substring(0,19);
        list = getFileList();
        st += Password;
        list.set(3,st);
        writeFile(list);
    }
    /**
     * Updates saved MySQL database name.
     * @param String with value of new database name.
     */
    public static void setDBName(String Name){
        ArrayList<String> list;
        String st = getFileList().get(4);
        st = st.substring(0,15);
        list = getFileList();
        st += Name;
        list.set(4,st);
        writeFile(list);
    }
}
