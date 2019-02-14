package smith.adam.database;

/*
 * Example code from: https://web-development-class.readthedocs.io/en/latest/semester_2/08_db_connections/managing_database_connections.html
 * Created by Dr. Craven @ Simpson College.
 *
 */
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

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

    private static List<Clients> getPeople(){
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


    public static TableView<Clients> makeColumns(){
        TableView<Clients> table;
        String firstName = "firstName";
        String lastName = "lastName";
        String id = "id";
        String phoneNum = "phoneNumber";
        String address =  "address";

        //first name column
        TableColumn<Clients, String> firstNameC = new TableColumn<>(firstName);
        firstNameC.setMinWidth(50);
        firstNameC.setCellValueFactory(new PropertyValueFactory<>(firstName));

        //last name column
        TableColumn<Clients, String> lastNameC = new TableColumn<>(lastName);
        lastNameC.setMinWidth(50);
        lastNameC.setCellValueFactory(new PropertyValueFactory<>(lastName));

        //id column
        TableColumn<Clients, Integer> idC = new TableColumn<>(id);
        idC.setMinWidth(25);
        idC.setCellValueFactory(new PropertyValueFactory<>(id));

        //phone column
        TableColumn<Clients, String> phoneC = new TableColumn<>(phoneNum);
        phoneC.setMinWidth(100);
        phoneC.setCellValueFactory(new PropertyValueFactory<>(phoneNum));

        //addr column
        TableColumn<Clients, String> addrC = new TableColumn<>(address);
        addrC.setMinWidth(200);
        addrC.setCellValueFactory(new PropertyValueFactory<>(address));

        table = new TableView<>();
        table.setItems(getClients());
        table.getColumns().addAll(firstNameC, lastNameC, idC, phoneC, addrC);

        return table;
    }

    private static ObservableList<Clients> getClients(){
        ObservableList<Clients> clients_fx = FXCollections.observableArrayList();
        List<Clients> list = new LinkedList<>();
        list = getPeople();
        for(Clients person : list){
            clients_fx.add(person);
        }

        return clients_fx;
    }





}
