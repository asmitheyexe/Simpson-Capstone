package smith.adam.database.DatabaseClasses;
/*
    TEXTFIELD PARAMATERS : TextField inputFirstName, TextField inputLastName,TextField inputPhone,TextField inputStreetAdr,TextField inputUnit,TextField inputCity,TextField inputState,TextField inputZip

 */
import javafx.scene.control.TextField;
import java.util.LinkedList;
import java.util.List;

public class TextFieldFactory {

    public static TextField makeTextField(String promptText){
        final TextField inputField = new TextField();
        inputField.setPromptText(promptText);
        inputField.setPrefColumnCount(10);
        return inputField;
    }

    public static Clients makePersonFromTextFields(List<TextField> texts, int id){
        return extractText(texts, id);

    }

    private static Clients extractText(List<TextField> listOfFields, int id){
        List<String> strings = new LinkedList<>();
        for(TextField field : listOfFields){
            strings.add(field.getText());
            System.out.println(field.getText());
        }
        return new Clients(strings.get(0), strings.get(1), strings.get(2), strings.get(3), strings.get(4), strings.get(5), strings.get(6), strings.get(7), id);

    }
}
