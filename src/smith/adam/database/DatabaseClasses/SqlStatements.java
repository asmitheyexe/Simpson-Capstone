package smith.adam.database.DatabaseClasses;




import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.LinkedList;
import java.util.List;

public class SqlStatements {

    public static List<Clients> getPeople() throws Exception{
        DatabaseColumnNames columns = new DatabaseColumnNames();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            List<Clients> clients = new LinkedList<>();
            conn = DBConnection.getConnection();

            String sql = "SELECT * FROM JASWData.Clients ORDER BY "+columns.getFirstNameColumn()+";";

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


    // help from https://stackoverflow.com/questions/696782/retrieve-column-names-from-java-sql-resultset on this method
    //this method will help automate the column names without hardcoding it
    public static ResultSetMetaData getAllColumns() throws Exception{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = DBConnection.getConnection();

            String sql = "SELECT * FROM JASWData.Clients;";

            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println(rsmd.getColumnName(1));
            return rsmd;

        } catch(Exception e){
            System.out.println("Error in SQLStatments when Retreiving columns from database" + e);
        }
        finally{

            stmt.close();
            rs.close();
        }
        return null; //idk how to handle returning out of try catchs or if statements
    }

    public static void deletePerson(int id) throws Exception{
        Connection conn = null;
        PreparedStatement stmt = null;
        DatabaseColumnNames columns= new DatabaseColumnNames();

        try{
            conn = DBConnection.getConnection();

            String sql = "DELETE FROM JASWData.Clients  WHERE  "+ columns.getIdColumn() +"= ?;";

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

    public void updatePerson(){

    }


    public static void insertPerson(Clients person) throws Exception{

        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            conn = DBConnection.getConnection();

            DatabaseColumnNames columns = new DatabaseColumnNames();

            String sql = "INSERT INTO JASWData.Clients ("+columns.getFirstNameColumn()+" = ?,"+
                    columns.getLastNameColumn()+" = ?,"+columns.getPhoneColumn()+" = ?,"+
                    columns.getStreetAddressColumm()+" = ?,"+columns.getUnitColumn()+" = ?,"+
                    columns.getCityColumn()+" = ?,"+columns.getStateColumn()+" = ?,"+
                    columns.getZipColumn()+"  = ?) VALUES (?,?,?,?,?,?,?,?);"; //GetClientsDAO contains public variables that contain
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
            System.out.println(e);
        }finally{
            conn.close();
            stmt.close();
        }
    }

    public static Clients editRecord(int ID) throws Exception {

        DatabaseColumnNames columnNames = new DatabaseColumnNames();
        Clients person;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();

            String sql = "SELECT * FROM JASWData.Clients WHERE "+ columnNames.getIdColumn() +" = ?;";


            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ID);
            rs = stmt.executeQuery();

            person = GetClientsDAO.makePersonSingle(rs);

            return person;


        } catch (Exception e) {
            System.out.println("Error when editing Client");
        } finally {
            try {
                conn.close();
                stmt.close();
                rs.close();
            }
            catch(Exception E){
                System.out.println("Error with Closing connection in SQLStatments Edit");
            }
        }
        return new Clients(); // for any reason it fails to edit return empty person object
    }

}
