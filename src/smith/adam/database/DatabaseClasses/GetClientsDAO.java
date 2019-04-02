package smith.adam.database.DatabaseClasses;
/*
 * Example code from: https://web-development-class.readthedocs.io/en/latest/semester_2/08_db_connections/managing_database_connections.html
 * Created by Dr. Craven @ Simpson College.
 *
 */


import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class GetClientsDAO {


    public static Clients makePerson(ResultSet rs)throws Exception {
        List<String> strings = new LinkedList<>();
        List<String> columns = DatabaseColumnNames.returnColumns();

        try {
            for(int i = 0; i < columns.size() - 1; i++){
                strings.add(rs.getString(columns.get(i)));
            }
            int id = rs.getInt(10);
            boolean hasCompany = true;


            return new Clients(strings.get(0), strings.get(1), strings.get(2), strings.get(3), strings.get(4), strings.get(5), strings.get(6), strings.get(7), strings.get(8), id);

        }catch(Exception C){System.out.println("Error In GetClientsDAO MakePerson "+C);}
        return new Clients();
    }

    public static Clients makePersonSingle(ResultSet rs)throws Exception {
        List<String> strings = new LinkedList<>();
        List<String> columns = DatabaseColumnNames.returnColumns();

        try {
            while(rs.next()) {
                for (int i = 0; i < columns.size() - 1; i++) {
                    strings.add(rs.getString(columns.get(i)));
                }
                int id = rs.getInt(10);
                return new Clients(strings.get(0), strings.get(1), strings.get(2), strings.get(3), strings.get(4), strings.get(5), strings.get(6), strings.get(7), strings.get(8), id);

            }


        }catch(Exception C){System.out.println("Error in GetClientsDAO MakePersonSingle " + C);}
        return new Clients();
    }

    public static List<Clients> getAllClients(ResultSet rs) {
        List<Clients> clients = new LinkedList<>();
        Clients client;
        try {
            while (rs.next()) {
                client = makePerson(rs);
                clients.add(client);
            }
        }catch(Exception C){System.out.println("Error in GetClientsDAO getAllClients "+C);}
        return clients;
    }


}



