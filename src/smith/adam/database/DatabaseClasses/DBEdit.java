package smith.adam.database.DatabaseClasses;
/*
    This class is responsible for handling the EDIT button on the top ribbon in the Database view

    The class works by GUI for entering a int value to select who you wish to delete. Will investigate into maybe a mouse
    click function that acts like a Web Application event for getting the ID.

    The user then gets a GUI that is similar to the Insert GUI except the fields are auto populated by current information
    in the database.

    It is a crude but working example.
 */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DBEdit extends Application {
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

        final String inputIDPrompt = "Enter ID";

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10,10,10,10));

        layout.setHgap(10);

        //VBox container = new VBox();
        final TextField inputID = TextFieldFactory.makeTextField(inputIDPrompt);
        inputID.setPadding(new Insets(10));
        GridPane.setConstraints(inputID, 0,0);


        Button confrimEditBtn = new Button("Edit this ID");
        GridPane.setConstraints(confrimEditBtn,1,0);
        confrimEditBtn.setPadding(new Insets(10));

        confrimEditBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(inputID.getText());
                person = SqlStatements.editRecord(id);
                newWindow.setScene(makeEditScene());
            }catch(Exception badDelete){System.out.println("Bad input from text");}
        });
        layout.getChildren().addAll(confrimEditBtn,inputID);
        return new Scene(layout);
    }

    private static Scene makeEditScene() throws Exception{

        final String btnText = "Save and Insert into Database";
        List<TextField> listOfFields =GenericSceneClasses.insertSceneFields(person);

        Button saveBtn = GenericSceneClasses.buttonFactory(btnText);

        saveBtn.setOnAction(e -> {

            person = TextFieldFactory.makePersonFromTextFields(listOfFields, person.getClient_id());
            person = DataValidation.validateEntry(person);
            if(!person.getFirstName().equals("")){
                SqlStatements.updatePerson(person);
                person = new Clients();
                try{
                    DBViewTable.updateTable();
                }catch(Exception g){
                    System.out.println("Error updating table in dbedit " + g);
                }

                newWindow.close();
            }
        });
        return new Scene(GenericSceneClasses.returnInsertEditScene(saveBtn,listOfFields));
    }
}
