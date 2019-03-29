package smith.adam.database.DatabaseClasses;

import javafx.scene.control.Alert;
public class ErrorMessageFactory {

    public static void displayError(String message){
        Alert error = new Alert(Alert.AlertType.WARNING);
        error.setContentText(message);
        error.show();
    }
}
