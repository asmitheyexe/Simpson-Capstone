package smith.adam.database.DatabaseClasses;

/*
    This class is responsible for building the GUI table scene. It takes the all the rows in the database and assigns the
    values that corresponds with each column name specified.
 */
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

public class DBViewTable extends Application {

    private static Stage newWindow;

    @Override
    public void start(Stage primaryStage) throws Exception{

    }

    public static void newWindow()throws  Exception{
        newWindow = new Stage();
        newWindow.setScene(MainBox());
        newWindow.show();

    }

    public static void updateTable()throws Exception{
        newWindow.setScene(MainBox());
    }

    private static Scene MainBox() throws Exception{

        HBox topMenu = new HBox();

        Button btnCreate = new Button("Insert");
        btnCreate.setOnAction(e -> DBInsert.openWindow());

        Button btnEdit = new Button("Edit");
        btnEdit.setOnAction(e -> DBEdit.openWindow());

        Button btnDelete = new Button("Delete");
        btnDelete.setOnAction(e -> DBDelete.openWindow());

        Button updateBtn = new Button("Refresh Table");
        updateBtn.setOnAction( e -> {
            try{
                updateTable();
            }catch (Exception c){
                System.out.println("Exception in mainBox Update button");
            }
        });

        topMenu.getChildren().addAll(btnCreate, btnEdit, btnDelete, updateBtn);

        topMenu.setSpacing(20);

        BorderPane borderpane = new BorderPane();
        borderpane.setPadding(new Insets(10, 0, 10, 10));
        borderpane.setTop(topMenu);


        VBox centerMenu = dataTable_vBox();
        centerMenu.setPadding(new Insets(10,10,10,10));
        centerMenu.setPrefSize(100,100);
        borderpane.setCenter(centerMenu);

        Label copyright = new Label("JASW LLC. 2019");

        VBox bottomMenu = new VBox(copyright);

        borderpane.setBottom(bottomMenu);

        Scene scene = new Scene(borderpane, 1000,500);
        return scene;
    }


    public static TableView<Clients> makeColumns() throws Exception{
        TableView<Clients> table;
        String firstName = DatabaseColumnNames.getFirstNameColumn();
        String lastName = DatabaseColumnNames.getLastNameColumn();
        String client_id = DatabaseColumnNames.getIdColumn();
        String phoneNum = DatabaseColumnNames.getPhoneColumn();
        String streetAdr =  DatabaseColumnNames.getStreetAddressColumm();
        String city =  DatabaseColumnNames.getCityColumn();
        String state =  DatabaseColumnNames.getStateColumn();
        String unit =  DatabaseColumnNames.getUnitColumn();
        String zip =  DatabaseColumnNames.getZipColumn();

        List<String> columnNames;

        //first name column
        TableColumn<Clients, String> firstNameC = new TableColumn<>(firstName);
        firstNameC.setMinWidth(50);
        firstNameC.setCellValueFactory(new PropertyValueFactory<>(firstName));

        //last name column
        TableColumn<Clients, String> lastNameC = new TableColumn<>(lastName);
        lastNameC.setMinWidth(50);
        lastNameC.setCellValueFactory(new PropertyValueFactory<>(lastName));

        //phone column
        TableColumn<Clients, String> phoneC = new TableColumn<>(phoneNum);
        phoneC.setMinWidth(100);
        phoneC.setCellValueFactory(new PropertyValueFactory<>(phoneNum));

        //street addr column
        TableColumn<Clients, String> streetAddrC = new TableColumn<>(streetAdr);
        streetAddrC.setMinWidth(100);
        streetAddrC.setCellValueFactory(new PropertyValueFactory<>(streetAdr));


        TableColumn<Clients, String> unitC = new TableColumn<>(unit);
        unitC.setMinWidth(50);
        unitC.setCellValueFactory(new PropertyValueFactory<>(unit));

        TableColumn<Clients, String> cityC = new TableColumn<>(city);
        cityC.setMinWidth(50);
        cityC.setCellValueFactory(new PropertyValueFactory<>(city));

        TableColumn<Clients, String> stateC = new TableColumn<>(state);
        stateC.setMinWidth(50);
        stateC.setCellValueFactory(new PropertyValueFactory<>(state));

        TableColumn<Clients, String> zipC = new TableColumn<>(zip);
        zipC.setMinWidth(50);
        zipC.setCellValueFactory(new PropertyValueFactory<>(zip));

        //id column
        TableColumn<Clients, Integer> idC = new TableColumn<>(client_id);
        idC.setMinWidth(25);
        idC.setCellValueFactory(new PropertyValueFactory<>(client_id));

        table = new TableView<>();
        table.setItems(getClients());
        table.getColumns().addAll(firstNameC, lastNameC, phoneC,streetAddrC, unitC, cityC,stateC,zipC,idC);

        return table;
    }

    private static ObservableList<Clients> getClients() throws Exception{ //Uses JavaFX's ObservableList to store object for building a Table
        ObservableList<Clients> clients_fx = FXCollections.observableArrayList();
        List<Clients> list;
        list = SqlStatements.getPeople(); // returns all the rows in the Clients Database
        for(Clients person : list){
            clients_fx.add(person); //add the client to the FX array
        }

        return clients_fx; //return the Objects from Database
    }


    private static VBox dataTable_vBox() throws Exception{ // creating the Scene for the Database Table in the Window
        TableView<Clients> table;
        table = makeColumns(); // Calls the method that creates the columns and fills them.
        VBox vBox = new VBox();
        vBox.getChildren().addAll(table); // add the Scene to a container to make it easier to access
        vBox.maxWidth(500); // setting how big the table can be
        return vBox;
    }


}
