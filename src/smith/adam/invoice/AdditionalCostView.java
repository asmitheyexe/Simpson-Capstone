package smith.adam.invoice;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import smith.adam.database.DatabaseClasses.GenericSceneClasses;
import smith.adam.database.DatabaseClasses.TextFieldFactory;

public class AdditionalCostView {

    public static GridPane returnAdditionCostView(){
        String titlePrompt = "Customer Info";
        String descriptionPrompt = "Enter Description of Service";
        String clientIdPrompt = "Enter ClientID";
        String clientLabelPrompt ="Retrieve Client Info";
        String nextDescriptionPrompt = "Add additional description";

        GridPane layout = new GridPane();

        Text title = new Text(titlePrompt);

        layout.add(title,1,0);

        TextArea description = new TextArea();
        description.setPromptText(descriptionPrompt);
        description.setPrefHeight(100);
        description.setPrefWidth(150);
        description.setWrapText(true);

        layout.add(description,1,1);

        Button nextDescBtn = GenericSceneClasses.buttonFactory(nextDescriptionPrompt);
        layout.add(nextDescBtn,1,2);


        TextField retrieveClientID = TextFieldFactory.makeTextField(clientLabelPrompt);

        Button getInfoBtn = GenericSceneClasses.buttonFactory(clientIdPrompt);
        layout.add(retrieveClientID, 1,3);
        layout.add(getInfoBtn,1,4);
        layout.setVgap(10);
        return layout;
    }
}
