package smith.adam.invoice;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import smith.adam.database.DatabaseClasses.GenericSceneClasses;
import smith.adam.database.DatabaseClasses.TextFieldFactory;

import java.util.LinkedList;
import java.util.List;

public class AdditionalCostView {

    private static List<String> descriptions = new LinkedList<>();
    private static List<Double> prices = new LinkedList<>();

    public static GridPane returnAdditionCostView(){
        String titlePrompt = "Addition Costs";
        String descriptionPrompt = "Enter Description of Service";
        String pricePrompt = "Enter price";
        String finishBtn = "Finish Invoice";
        String nextDescriptionPrompt = "Add additional description";

        List<TextField> currentTextFields = new LinkedList<>();

        Button completeBtn = GenericSceneClasses.buttonFactory(finishBtn);
        GridPane layout = new GridPane();

        Text title = new Text(titlePrompt);
        TextField priceField = TextFieldFactory.makeTextField(pricePrompt);
        layout.add(title,1,0);

        TextArea description = new TextArea();
        description.setPromptText(descriptionPrompt);
        description.setPrefHeight(50);
        description.setPrefWidth(50);
        description.setWrapText(true);

        layout.add(description,1,1);
        layout.add(priceField,1,2);

        Button nextDescBtn = GenericSceneClasses.buttonFactory(nextDescriptionPrompt);

        nextDescBtn.setOnAction(e ->{
            final String textFromField = description.getText();
            final double price = Double.parseDouble(priceField.getText());
            description.setText("");
            priceField.setText("");

            //do something with the text and price Send to another class or something.
            descriptions.add(textFromField);
            prices.add(price);

        });

        completeBtn.setOnAction(e ->{
            InvoiceExtractAllTexts.printAllTexts();
            // send data to a new class that will handle the creating of data to send to the python program.
            //close the invoice box
            InvoiceMainScene.closeWindow();
        });
        layout.add(nextDescBtn,1,3);

        layout.add(completeBtn,1,4);
        layout.setVgap(10);
        return layout;
    }

    public static InvoiceAdditionPairs getPricesAndDescriptions(){return new InvoiceAdditionPairs(descriptions, prices);}
}
