package smith.adam.database.DatabaseClasses;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.List;

public class DBInsert extends Application{

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

        /*
         *These Strings are for prompt texts, makes it easy for editing
         */
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

        Button insertBtn = new Button(); //button to click to insert
        insertBtn.setPadding(new Insets(10,10,10,10));
        insertBtn.setText(btnText);

        VBox sideButtons = new VBox(insertBtn);
        sideButtons.setPadding(new Insets(10));

        GridPane.setConstraints(sideButtons,1,0);

        layout.getChildren().addAll(sideButtons);

        VBox textFields = new VBox();


        /*
         * Add every TextField to the list for easy use of TextFieldFactory class
         */

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


        textFields.getChildren().addAll( inputFirstName, inputLastName,inputPhone, inputStreetAdr,inputUnit,inputCity, inputState,inputZip);
        layout.getChildren().addAll(textFields);


        insertBtn.setOnAction(e -> {
            try {
                person = TextFieldFactory.makePersonFromTextFields(inputFirstName, inputLastName, inputPhone, inputStreetAdr,inputUnit,inputCity, inputState,inputZip, person.getClient_id());
                System.out.println(person.toString());
                if(person.getFirstName() != ""){
                    SqlStatements.insertPerson(person); //insert the person into the database
                    DBViewTable.updateTable();
                    newWindow.close();
                }else{
                    Stage errorWindow = new Stage();
                    Label error = new Label("Error with one of the Entries");
                    VBox errorBox = new VBox();
                    errorBox.getChildren().addAll(error);
                    Scene errorScene = new Scene(errorBox);
                    errorWindow.setScene(errorScene);
                    errorWindow.show();
                }



            }catch(Exception c){System.out.println("Error with creating client");}
        });
        return new Scene(layout);

    }











}
