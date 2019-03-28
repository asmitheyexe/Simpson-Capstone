package smith.adam.database.DatabaseClasses;
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


public class DBDelete extends Application {

    private static Stage newWindow;

    @Override
    public void start(Stage primaryStage) throws Exception{

    }

    public static void openWindow(){
        newWindow = new Stage();
        newWindow.setScene(makeScene());
        newWindow.show();

    }

    private static Scene makeScene(){
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10,10,10,10));

        layout.setHgap(10);

        //VBox container = new VBox();
        String textPrompt= "Remove from Database";
        final TextField inputText = TextFieldFactory.makeTextField(textPrompt);
        inputText.setPadding(new Insets(10));
        GridPane.setConstraints(inputText, 0,0);


        Button deleteBtn = new Button("Delete this ID");
        GridPane.setConstraints(deleteBtn,1,0);
        deleteBtn.setPadding(new Insets(10));

        deleteBtn.setOnAction(e -> {
            try {

                String id = inputText.getText();
                SqlStatements.deletePerson(Integer.parseInt(id)); // call the method in the SQL statments class to handle the work
                DBViewTable.updateTable(); //update the table
                newWindow.close();

            }catch(Exception badDelete){System.out.println("Bad input from text");}

        });

        layout.getChildren().addAll(deleteBtn,inputText);

        return new Scene(layout);
    }



}
