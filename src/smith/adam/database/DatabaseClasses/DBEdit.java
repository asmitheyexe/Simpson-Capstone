package smith.adam.database.DatabaseClasses;
/*
    This class is responsible for handling the EDIT button on the top ribbon in the Database view

    The class works by GUI for entering a int value to select who you wish to delete. Will investigate into maybe a mouse
    click function that acts like a Web Application event for getting the ID.

    The user then gets a GUI that is similar to the Insert GUI except the fields are auto populated by current information
    in the database.

    It is a crude but working example.
 */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DBEdit extends Application {
    private static Stage newWindow;
    private static Clients person = new Clients();
    @Override
    public void start(Stage primaryStage) throws Exception{

    }

    public static void openWindow(){
        newWindow = new Stage();
        newWindow.setScene(makeScene());
        newWindow.show();

    }

    private static Scene makeScene(){

        final String inputIDPrompt = "Enter ID";

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10,10,10,10));

        layout.setHgap(10);

        //VBox container = new VBox();
        final TextField inputID = TextFieldFactory.makeTextField(inputIDPrompt);
        inputID.setPadding(new Insets(10));
        GridPane.setConstraints(inputID, 0,0);


        Button confrimEditBtn = new Button("Edit this ID");
        GridPane.setConstraints(confrimEditBtn,1,0);
        confrimEditBtn.setPadding(new Insets(10));

        confrimEditBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(inputID.getText());
                person = SqlStatements.editRecord(id);
                newWindow.setScene(makeEditScene());
            }catch(Exception badDelete){System.out.println("Bad input from text");}
        });
        layout.getChildren().addAll(confrimEditBtn,inputID);
        return new Scene(layout);
    }



    private static Scene makeEditScene() throws Exception{

        final String firstNamePromptText = "Enter First Name";
        final String lastNamePromptText = "Enter Last Name";
        final String phonePromptText = "Enter Phone Number (555-555-5555)";
        final String streetAddrPromptText = "Enter Street Address";
        final String unitPromptText = "Enter Unit if Applicable";
        final String cityPromptText = "Enter City";
        final String statePromptText = "Enter State Initials";
        final String zipPromptText = "Enter ZipCode";
        final String btnText = "Save and Insert into Database";
        List<TextField> listOfFields = new LinkedList<>();

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10,10,10,10));


        Button saveBtn = new Button();
        saveBtn.setPadding(new Insets(10,10,10,10));
       saveBtn.setText("Save & update Database record");


        VBox sideButtons = new VBox(saveBtn);
        sideButtons.setPadding(new Insets(10));
        GridPane.setConstraints(sideButtons,1,0);
        layout.getChildren().addAll(sideButtons);
        VBox textFields = new VBox();


        //input first name
        final TextField inputFirstName = TextFieldFactory.makeTextField(firstNamePromptText);
        inputFirstName.setText(person.getFirstName());
        listOfFields.add(inputFirstName); // add to list

        //input last name
        final TextField inputLastName = TextFieldFactory.makeTextField(lastNamePromptText);
        inputLastName.setText(person.getLastName());
        listOfFields.add(inputLastName);

        //input phone
        final TextField inputPhone = TextFieldFactory.makeTextField(phonePromptText);
        inputPhone.setText(person.getPhoneNumber());


        //input street Address
        final TextField inputStreetAdr = TextFieldFactory.makeTextField(streetAddrPromptText);
        inputStreetAdr.setText(person.getStreetAdr());
        listOfFields.add(inputStreetAdr);

        //input for unit if applicable
        final TextField inputUnit = TextFieldFactory.makeTextField(unitPromptText);
        inputUnit.setText(person.getUnit());
        listOfFields.add(inputUnit);

        //Input for City
        final TextField inputCity = TextFieldFactory.makeTextField(cityPromptText);
        inputCity.setText(person.getCity());

        //input for State
        final TextField inputState = TextFieldFactory.makeTextField(statePromptText);
        inputState.setText(person.getState());

        //input for zipcode
        final TextField inputZip = TextFieldFactory.makeTextField(zipPromptText);
        inputZip.setText(person.getZip());


        textFields.getChildren().addAll(inputFirstName,inputLastName,inputPhone, inputStreetAdr, inputUnit,inputCity,inputState,inputZip);
        GridPane.setConstraints(textFields,0,0);
        layout.getChildren().addAll(textFields);




        saveBtn.setOnAction(e -> {
            Connection conn = null;
            PreparedStatement stmt = null;

            try {
                conn = DBConnection.getConnection();
                DatabaseColumnNames columnNames = new DatabaseColumnNames();
                person = TextFieldFactory.makePersonFromTextFields(inputFirstName, inputLastName, inputPhone, inputStreetAdr,inputUnit,inputCity, inputState,inputZip, person.getClient_id());

                String sql = "UPDATE JASWData.Clients SET "+columnNames.getFirstNameColumn()+"= ?,"+
                                           columnNames.getLastNameColumn()+ " =?," +columnNames.getPhoneColumn() +" =?," +
                                            columnNames.getStreetAddressColumm()+" =?,"+columnNames.getUnitColumn()+" =?,"+
                                          columnNames.getCityColumn()+" =?,"+columnNames.getStateColumn()+" =?," +
                                           columnNames.getZipColumn() +" =?"+" WHERE "+ columnNames.getIdColumn() + " =?;";

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
                newWindow.close();



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
        });
        return new Scene(layout);
    }
}
