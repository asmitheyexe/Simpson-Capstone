package smith.adam.database.DatabaseClasses;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.LinkedList;
import java.util.List;

public class GenericSceneClasses {

    public static List<TextField> insertSceneFields(Clients person){

        final String firstNamePromptText = "Enter First Name";
        final String lastNamePromptText = "Enter Last Name";
        final String phonePromptText = "Enter Phone Number (555-555-5555)";
        final String streetAddrPromptText = "Enter Street Address";
        final String unitPromptText = "Enter Unit if Applicable";
        final String cityPromptText = "Enter City";
        final String statePromptText = "Enter State Initials";
        final String zipPromptText = "Enter ZipCode";
        List<TextField> listOfFields = new LinkedList<>();

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
        listOfFields.add(inputPhone);

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
        listOfFields.add(inputCity);

        //input for State
        final TextField inputState = TextFieldFactory.makeTextField(statePromptText);
        inputState.setText(person.getState());
        listOfFields.add(inputState);

        //input for zipcode
        final TextField inputZip = TextFieldFactory.makeTextField(zipPromptText);
        inputZip.setText(person.getZip());
        listOfFields.add(inputZip);

        return listOfFields;

    }

    public static GridPane returnInsertEditScene(Button btn, List<TextField> listOfFields){
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10,10,10,10));




        VBox sideButtons = new VBox(btn);
        sideButtons.setPadding(new Insets(10));
        GridPane.setConstraints(sideButtons,1,0);
        layout.getChildren().addAll(sideButtons);
        VBox textFields = new VBox();

        for (TextField i : listOfFields){
            textFields.getChildren().addAll(i);
        }

        GridPane.setConstraints(textFields,0,0);
        layout.getChildren().addAll(textFields);


        return layout;

    }


    public static Button buttonFactory(String btnText){

        Button btn = new Button();
        btn.setPadding(new Insets(10,10,10,10));
        btn.setText(btnText);
        return btn;
    }
}
