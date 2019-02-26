package smith.adam.database;

import com.sun.tools.javac.Main;
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

    public static void newWindow(){
        newWindow = new Stage();
        newWindow.setScene(MainBox());
        newWindow.show();

    }

    public static void updateTable(){
        newWindow.setScene(MainBox());
    }

    private static Scene MainBox(){

        HBox topMenu = new HBox();

        Button btnCreate = new Button("Insert");
        btnCreate.setOnAction(e -> DBInsert.openWindow());

        Button btnEdit = new Button("Edit");
        btnEdit.setOnAction(e -> DBEdit.openWindow());

        Button btnDelete = new Button("Delete");
        btnDelete.setOnAction(e -> DBDelete.openWindow());

        Button updateBtn = new Button("Refresh Table");
        updateBtn.setOnAction( e -> updateTable());

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
        list = GetClientsDAO.getPeople();
        for(Clients person : list){
            clients_fx.add(person);
        }

        return clients_fx;
    }

    private static Scene dataTable_Scene(){
        TableView<Clients> table;
        table = makeColumns();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(table);
        Scene dataScene = new Scene(vBox);
        return dataScene;
    }

    private static VBox dataTable_vBox(){
        TableView<Clients> table;
        table = makeColumns();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(table);
        vBox.maxWidth(500);
        return vBox;
    }


}
