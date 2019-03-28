package smith.adam.database.DatabaseClasses;
/*
    TEXTFIELD PARAMATERS : extField inputLastName,TextField inputPhone,TextField inputStreetAdr,TextField inputUnit,TextField inputCity,TextField inputState,TextField inputZip


 */
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.List;

public class TextFieldFactory {

    public static TextField makeTextField(String promptText){
        final TextField inputField = new TextField();
        inputField.setPromptText(promptText);
        inputField.setPrefColumnCount(10);
        return inputField;
    }

    public static Clients makePersonFromTextFields(TextField inputFirstName, TextField inputLastName,TextField inputPhone,TextField inputStreetAdr,TextField inputUnit,TextField inputCity,TextField inputState,TextField inputZip, int id){
        List<TextField> fields = new LinkedList<>();
        fields.add(inputFirstName);
        fields.add(inputLastName);
        fields.add(inputPhone);
        fields.add(inputStreetAdr);
        fields.add(inputUnit);
        fields.add(inputCity);
        fields.add(inputState);
        fields.add(inputZip);

        return extractText(fields, id);

    }




    private static Clients extractText(List<TextField> listOfFields, int id){
        List<String> strings = new LinkedList<>();
        for(TextField field : listOfFields){
            strings.add(field.getText());
        }

        Clients person = new Clients(strings.get(0), strings.get(1), strings.get(2), strings.get(3), strings.get(4), strings.get(5), strings.get(6), strings.get(7), id);
        return person;


    }
}
