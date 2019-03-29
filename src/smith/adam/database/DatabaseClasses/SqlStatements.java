package smith.adam.database.DatabaseClasses;




import javax.xml.crypto.Data;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class SqlStatements {

    public static List<Clients> getPeople() throws Exception{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            List<Clients> clients = new LinkedList<>();
            conn = DBConnection.getConnection();

            String sql = "SELECT * FROM JASWData.Clients ORDER BY "+DatabaseColumnNames.getFirstNameColumn()+";";

            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            clients = GetClientsDAO.getAllClients(rs);
            return clients;

        }
        catch(Exception e){
            System.out.println("Error for getPeople clients SqlStatments" + e);
        }finally{
            conn.close();
            stmt.close();
            rs.close();

        }
        return null;

    }


    public static void deletePerson(int id) throws Exception{
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = DBConnection.getConnection();

            String sql = "DELETE FROM JASWData.Clients  WHERE  "+ DatabaseColumnNames.getIdColumn() +"= ?;";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.execute();
            System.out.println("Deleted Person id: " + id);

        } catch(Exception e){
            System.out.println("Error in SQLStatments when deleting from database" + e);
        }
        finally{
            conn.close();
            stmt.close();
        }
    }

    public static void updatePerson(Clients person){
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBConnection.getConnection();


            String sql = "UPDATE JASWData.Clients SET "+DatabaseColumnNames.getFirstNameColumn()+"= ?,"+
                    DatabaseColumnNames.getLastNameColumn()+ " =?," +DatabaseColumnNames.getPhoneColumn() +" =?," +
                    DatabaseColumnNames.getStreetAddressColumm()+" =?,"+DatabaseColumnNames.getUnitColumn()+" =?,"+
                    DatabaseColumnNames.getCityColumn()+" =?,"+DatabaseColumnNames.getStateColumn()+" =?," +
                    DatabaseColumnNames.getZipColumn() +" =?"+" WHERE "+ DatabaseColumnNames.getIdColumn() + " =?;";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getLastName());
            stmt.setString(3, person.getPhoneNumber());
            stmt.setString(4, person.getStreetAdr());
            stmt.setString(5, person.getUnit());
            stmt.setString(6, person.getCity());
            stmt.setString(7, person.getState());
            stmt.setString(8, person.getZip());
            stmt.setInt(9, person.getId());
            stmt.executeUpdate();


            DBViewTable.updateTable();




        }catch(Exception c){
            System.out.println("Error with inserting into the Database DB EDIT" + c);
        }finally{
            try {
                conn.close();
                stmt.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }


    public static void insertPerson(Clients person) throws Exception{

        Connection conn = null;
        PreparedStatement stmt = null;



        try{
            conn = DBConnection.getConnection();

            String sql = "INSERT INTO JASWData.Clients ("+DatabaseColumnNames.getFirstNameColumn()+" ,"+
                    DatabaseColumnNames.getLastNameColumn()+" ,"+DatabaseColumnNames.getPhoneColumn()+" ,"+
                    DatabaseColumnNames.getStreetAddressColumm()+","+DatabaseColumnNames.getUnitColumn()+" ,"+
                    DatabaseColumnNames.getCityColumn()+" ,"+DatabaseColumnNames.getStateColumn()+","+
                    DatabaseColumnNames.getZipColumn()+") VALUES (?,?,?,?,?,?,?,?);"; //DatabaseColumnNames contains public variables that contain
                                                                     // the names of the columns


            stmt = conn.prepareStatement(sql);


            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getLastName());
            stmt.setString(3, person.getPhoneNumber());
            stmt.setString(4, person.getStreetAdr());
            stmt.setString(5, person.getUnit());
            stmt.setString(6, person.getCity());
            stmt.setString(7, person.getState());
            stmt.setString(8, person.getZip());

            stmt.execute();

        } catch(Exception e){
            System.out.println("SqlStatments Error in Insert Person "+ e);
        }finally{
            conn.close();
            stmt.close();
        }
    }

    public static Clients editRecord(int ID) throws Exception {

        Clients person;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();

            String sql = "SELECT * FROM JASWData.Clients WHERE "+ DatabaseColumnNames.getIdColumn() +" = ?;";


            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ID);
            rs = stmt.executeQuery();

            person = GetClientsDAO.makePersonSingle(rs);

            return person;


        } catch (Exception e) {
            System.out.println("Error when editing Client " + e);
        } finally {
            try {
                conn.close();
                stmt.close();
                rs.close();
            }
            catch(Exception E){
                System.out.println("Error with Closing connection in SQLStatments Edit" + E);
            }
        }
        return new Clients(); // for any reason it fails to edit return empty person object
    }

}
