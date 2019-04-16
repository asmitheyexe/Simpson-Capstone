package smith.adam.database.DatabaseClasses;
/*

    credit to amazon, most of the code was taken from their website.
    url:https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/java-rds.html

 */
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    private final static Logger logger = Logger.getLogger(DBConnection.class.getName());


    public static Connection getConnection() throws Exception {
        //this will allow the program to be used without having to specify where the files are
        String currentDirectory = System.getProperty("user.dir");

        //i store the information for the database in a txt file that is read in by the class and establishes a connection
        String fileName = currentDirectory +"\\src\\smith\\adam\\database\\DatabaseClasses\\storage\\ConfigStuff\\context.txt";
        FileReader inputFile = new FileReader(fileName); //Made this so the file for getting a DB connection would be fine to store on Github
        Scanner inputText = new Scanner(inputFile); // read in the context file for the information to connect to the database

        String[] context = new String[5];
        int counter = 0;
        while(inputText.hasNext()){
            context[counter] = inputText.nextLine();
            counter++;
        }

        try {
            Class.forName(context[0]);
            String dbName = context[1];
            String userName = context[2];
            String password = context[3];
            String hostname = context[4];
            String port = "3306";
            String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
            Connection con = DriverManager.getConnection(jdbcUrl);
            logger.info("Remote connection successful.");

            inputText.close();
            inputFile.close();
            return con;

        } catch (ClassNotFoundException e) {
            System.out.println("Error in DBConnection");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Unable to get a database connection.", e);
        }
    return null;
    }
}
