package smith.adam.invoice;

import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class AdditionalCostView {

    public static GridPane returnAdditionCostView(){
        String titlePrompt = "Customer Info";
        String descriptionPrompt = "Enter Description of Service";

        GridPane layout = new GridPane();

        Text title = new Text(titlePrompt);

        layout.add(title,1,0);

        TextArea description = new TextArea();
        description.setPromptText(descriptionPrompt);
        description.setPrefHeight(100);
        description.setPrefWidth(150);
        description.setWrapText(true);

        layout.add(description,1,1);

        return layout;
    }
}
