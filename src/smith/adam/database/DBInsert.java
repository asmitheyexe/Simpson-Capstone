package smith.adam.database;

import sample.CentralControl;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import smith.adam.database.Clients;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import smith.adam.database.GetClientsDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBInsert extends Application{

    private static Stage newWindow;
    private static Clients person= new Clients();



    @Override
    public void start(Stage primaryStage) throws Exception{

    }

    public static void newWindow(){
        newWindow = new Stage();
        newWindow.setScene(makeScene());
        newWindow.show();

    }

    private static Scene makeScene(){

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10,10,10,10));


        Button insertBtn = new Button();
        insertBtn.setPadding(new Insets(10,10,10,10));
        insertBtn.setText("Insert into the Database");

        VBox sideButtons = new VBox(insertBtn);
        sideButtons.setPadding(new Insets(10));

        GridPane.setConstraints(sideButtons,1,0);

        layout.getChildren().addAll(sideButtons);

        VBox textFields = new VBox();
        //input first name
        final TextField inputfName = new TextField();
        inputfName.setPromptText("Enter first name");
        inputfName.setPrefColumnCount(10);

        //input last name
        final TextField inputlName = new TextField();
        inputlName.setPromptText("Enter last name");
        inputlName.setPrefColumnCount(10);

        //input phone
        final TextField inputPhone = new TextField();
        inputPhone.setPromptText("Enter phone(555-555-5555)");
        inputPhone.setPrefColumnCount(10);

        //input Address
        final TextField inputAddress = new TextField();
        inputAddress.setPromptText("Enter Address (Street, city,state,zip)");
        inputAddress.setPrefColumnCount(10);

        textFields.getChildren().addAll(inputfName,inputlName,inputAddress,inputPhone);
        GridPane.setConstraints(textFields,0,0);
        layout.getChildren().addAll(textFields);




        insertBtn.setOnAction(e -> {
            try {
                person.setPhoneNumber(inputPhone.getText());
                person.setFirstName(inputfName.getText());
                person.setLastName(inputlName.getText());
                person.setAddress(inputAddress.getText());

                try {
                    person.setId(IdentificationNumberGetter.nextID());
                }catch (Exception i){System.out.println("Error in opening file for ID");};


                insertIntoDB();

            }catch(Exception c){System.out.println("Error with inserting into the Database");}
        });

        System.out.println(person.toString());

        return new Scene(layout);

    }

    private static void insertIntoDB() throws Exception{

        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            conn = DBConnection.getConnection();

            String sql = "INSERT INTO JASWData.Clients VALUES ("+person.getFirstName()+","+person.getLastName()+","+person.getId()+","+person.getPhoneNumber()+","+person.getAddress()+");";

            stmt = conn.prepareStatement(sql);

            stmt.executeQuery();

            } catch(Exception e){
            System.out.println("Error for database");
        }


        }










}
