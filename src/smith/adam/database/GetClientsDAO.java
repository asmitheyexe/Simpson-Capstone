package smith.adam.database;

/*
 * Example code from: https://web-development-class.readthedocs.io/en/latest/semester_2/08_db_connections/managing_database_connections.html
 * Created by Dr. Craven @ Simpson College.
 *
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class GetClientsDAO {

    private static String firstName = "firstName";
    private static String lastName = "lastName";
    private static String id = "id";
    private static String phoneNumber = "phoneNumber";
    private static String address = "address";

    public static List<Clients> getPeople(){
        List<Clients> clients = new LinkedList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = DBConnection.getConnection();

            String sql = "SELECT * FROM JASWData.Clients ORDER BY firstName;";

            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while(rs.next()){
                Clients client = new Clients();

                client.setFirstName(rs.getString(firstName));
                client.setLastName(rs.getString(lastName));
                client.setId(rs.getInt(id));
                client.setPhoneNumber(rs.getString(phoneNumber));
                client.setAddress(rs.getString(address));

                clients.add(client);
            }


        }
        catch(Exception e){
            System.out.println("Error for database");
        }
        return clients;
    }








}
