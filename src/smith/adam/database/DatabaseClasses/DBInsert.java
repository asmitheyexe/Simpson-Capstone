package smith.adam.database.DatabaseClasses;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.List;

public class DBInsert extends Application{

    private static Stage newWindow;
    private static Clients person = new Clients();

    @Override
    public void start(Stage primaryStage) throws Exception{
    }

    public static void openWindow(){
        newWindow = new Stage();
        newWindow.setScene(makeScene());
        newWindow.show();
    }

    private static Scene makeScene(){
        final String btnText = "Save and Insert into Database";
        List<TextField> listOfFields = GenericSceneClasses.insertSceneFields(person);
        Button insertBtn = GenericSceneClasses.buttonFactory(btnText);

        insertBtn.setOnAction(e -> {
            try {
                person = TextFieldFactory.makePersonFromTextFields(listOfFields, person.getClient_id());
                person = DataValidation.validateEntry(person); // this will validate all the fields. it will either return a Clients Obj if all fields match the Patterns else it will return an Empty Person Obj
                if(!person.getFirstName().equals("")){
                    SqlStatements.insertPerson(person); //insert the person into the database
                    DBViewTable.updateTable();
                    newWindow.close();
                }
            }catch(Exception c){System.out.println("Error with creating client " + c);}
        });
        return new Scene(GenericSceneClasses.returnInsertEditScene(insertBtn,listOfFields));
    }
}
