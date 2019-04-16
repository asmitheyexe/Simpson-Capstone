package smith.adam.invoice;
/*
    This class creates a textfield for the bid to go into and can
    return that textfield if another class needs it to extract text
 */
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import smith.adam.database.DatabaseClasses.TextFieldFactory;


public class BaseBidView {

    private static TextField inputBox;

    public static GridPane returnBidView(){
        String titlePrompt = "Base Bid";
        String bidPrompt = "Enter Base Bid";
        GridPane layout = new GridPane();

        Text title = new Text(titlePrompt);
        inputBox = TextFieldFactory.makeTextField(bidPrompt);
        inputBox.setPrefWidth(100);

        layout.add(title,2,0);
        layout.add(inputBox,2,1);
        layout.setPadding(new Insets(10));

        return layout;
    }

    public static TextField getBidField(){return inputBox;}
}
