package smith.adam.invoice;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import smith.adam.database.DatabaseClasses.GenericSceneClasses;
import smith.adam.database.DatabaseClasses.TextFieldFactory;


public class BaseBidView {

    public static GridPane returnBidView(){
        String titlePrompt = "Base Bid";
        String bidPrompt = "Enter Base Bid";
        String finishBtn = "Finish Invoice";
        GridPane layout = new GridPane();

        Button completeBtn = GenericSceneClasses.buttonFactory(finishBtn);

        Text title = new Text(titlePrompt);
        TextField inputBox = TextFieldFactory.makeTextField(bidPrompt);
        inputBox.setPrefWidth(30);

        layout.add(title,2,0);
        layout.add(inputBox,2,1);
        layout.add(completeBtn,2,4);


        return layout;
    }
}
