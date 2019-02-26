package smith.adam.database;

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
        final TextField inputText = new TextField();
        inputText.setPromptText("Remove from Database");
        inputText.setPrefColumnCount(10);
        inputText.setPadding(new Insets(10));
        GridPane.setConstraints(inputText, 0,0);


        Button deleteBtn = new Button("Delete this ID");
        GridPane.setConstraints(deleteBtn,1,0);
        deleteBtn.setPadding(new Insets(10));

        deleteBtn.setOnAction(e -> {
            try {
                String id = inputText.getText();
                deleteFromDB(Integer.parseInt(id));
                DBViewTable.updateTable();
                newWindow.close();

            }catch(Exception badDelete){System.out.println("Bad input from text");}

        });

        layout.getChildren().addAll(deleteBtn,inputText);

        return new Scene(layout);
    }

    private static void deleteFromDB(int value) throws Exception{

        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            conn = DBConnection.getConnection();

            String sql = "DELETE FROM JASWData.Clients  WHERE id = ?;";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,value);
            stmt.execute();

        } catch(Exception e){
            System.out.println("Error when deleting from database");
        }finally{
            conn.close();
            stmt.close();
        }


    }

}
