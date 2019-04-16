package smith.adam.invoice;
/*
    This class is responsible for creating the GUI for the user.
    It created the fields is a similar fashion as the Database insert class

 */
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import smith.adam.database.DatabaseClasses.*;

import java.util.LinkedList;
import java.util.List;


public class CustomerViewBox {
    // create a empty clients obj
    // create a empty list of textfields to store all the textfields in
    private static Clients person = new Clients();
    private static List<TextField> textFields = GenericSceneClasses.insertSceneFields(person);

    public static GridPane returnDefaultView(){
        String titlePrompt = "Customer Info";
        String clientIdPrompt = "Enter ClientID";
        String clientLabelPrompt ="Retrieve Client Info";

        // TextFieldFactory creates generic standard textfields with prompt text you pass in
        TextField retrieveClientID = TextFieldFactory.makeTextField(clientLabelPrompt);
        //GenericSceneClasses has factory classes that create generic templated class like Button
        Button getInfoBtn = GenericSceneClasses.buttonFactory(clientIdPrompt);

        // when the button is clicked get the row in the Database that correlates with
        // the ID passed in by the user and populate the fields with the information
        // in the row
        getInfoBtn.setOnAction(e -> {
            int id = Integer.parseInt(retrieveClientID.getText());
            try{
                final Clients client = SqlStatements.editRecord(id);
                textFields = GenericSceneClasses.insertSceneFields(client);
                InvoiceMainScene.updateMainScreen();

            }catch (Exception c){
                System.out.println("Error in CustomerViewBox getting client " + c);
            }
        });

        // add all the fields and buttons to the layout and return the scene
        GridPane layout = new GridPane();

        Label titleSection = new Label(titlePrompt);

        layout.add(titleSection, 0,1);
        layout.add(GenericSceneClasses.returnInvoiceFields(textFields), 0,2);
        layout.add(retrieveClientID, 0,3);
        layout.add(getInfoBtn,0,4);
        layout.setVgap(5);

        return layout;
    }

    public static List<TextField> getTextFields(){
        List<TextField> returnList = textFields;

        person = TextFieldFactory.makePersonFromTextFields(textFields,0);
        person = DataValidation.validateEntry(person);
        if(!person.getFirstName().equals("")){
            resetFields();
            return returnList;
        }

        return returnEmptyList();

    }

    private static void resetFields(){
        person = new Clients();
        textFields = GenericSceneClasses.insertSceneFields(person);
    }

    private static List<TextField> returnEmptyList(){
        List<TextField> returnList = GenericSceneClasses.insertSceneFields(new Clients());
        return returnList;
    }

}
