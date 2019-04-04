package smith.adam.invoice;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import smith.adam.database.DatabaseClasses.Clients;
import smith.adam.database.DatabaseClasses.GenericSceneClasses;
import smith.adam.database.DatabaseClasses.SqlStatements;
import smith.adam.database.DatabaseClasses.TextFieldFactory;
import java.util.List;


public class CustomerViewBox {

    private static Clients person = new Clients();
    private static List<TextField> textFields = GenericSceneClasses.insertSceneFields(person);

    public static GridPane returnDefaultView(){
        String titlePrompt = "Customer Info";
        String clientIdPrompt = "Enter ClientID";
        String clientLabelPrompt ="Retrieve Client Info";
        TextField retrieveClientID = TextFieldFactory.makeTextField(clientLabelPrompt);

        Button getInfoBtn = GenericSceneClasses.buttonFactory(clientIdPrompt);

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
        return textFields;
    }


}
