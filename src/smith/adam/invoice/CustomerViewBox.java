package smith.adam.invoice;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import smith.adam.database.DatabaseClasses.Clients;
import smith.adam.database.DatabaseClasses.GenericSceneClasses;

import java.util.List;


public class CustomerViewBox {

    public static GridPane returnView(){
        String titlePrompt = "Customer Info";
        GridPane layout = new GridPane();

        Label titleSection = new Label(titlePrompt);

        List<TextField> textFields = GenericSceneClasses.insertSceneFields(new Clients());

        layout.add(titleSection, 0,0);
        layout.add(GenericSceneClasses.returnInvoiceFields(textFields), 0,1);

        return layout;
    }

}
