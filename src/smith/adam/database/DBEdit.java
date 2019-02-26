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
import java.sql.ResultSet;
import java.sql.SQLException;

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
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10,10,10,10));

        layout.setHgap(10);

        //VBox container = new VBox();
        final TextField inputID = new TextField();
        inputID.setPromptText("Enter ID");
        inputID.setPrefColumnCount(10);
        inputID.setPadding(new Insets(10));
        GridPane.setConstraints(inputID, 0,0);


        Button confrimEditBtn = new Button("Edit this ID");
        GridPane.setConstraints(confrimEditBtn,1,0);
        confrimEditBtn.setPadding(new Insets(10));

        confrimEditBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(inputID.getText());
                editRecord(id);
            }catch(Exception badDelete){System.out.println("Bad input from text");}
        });
        layout.getChildren().addAll(confrimEditBtn,inputID);
        return new Scene(layout);
    }

    private static void editRecord(int ID) throws Exception{

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = DBConnection.getConnection();

            String sql = "SELECT * FROM JASWData.Clients WHERE id=?;";


            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                person.setFirstName(rs.getString("firstName"));
                person.setLastName(rs.getString("lastName"));
                person.setId(rs.getInt("id"));
                person.setPhoneNumber(rs.getString("phoneNumber"));
                person.setAddress(rs.getString("address"));
            }
            newWindow.setScene(makeEditScene());


        } catch(Exception e){
            System.out.println("Error when deleting from database");
        }finally{
            conn.close();
            stmt.close();
            rs.close();
        }
    }

    private static Scene makeEditScene() throws Exception{

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10,10,10,10));


        Button saveBtn = new Button();
        saveBtn.setPadding(new Insets(10,10,10,10));
       saveBtn.setText("Save & update Database record");


        VBox sideButtons = new VBox(saveBtn);
        sideButtons.setPadding(new Insets(10));
        GridPane.setConstraints(sideButtons,1,0);
        layout.getChildren().addAll(sideButtons);
        VBox textFields = new VBox();


        //input first name
        final TextField inputfName = new TextField();
        inputfName.setPromptText("Enter first name");
        inputfName.setPrefColumnCount(10);
        inputfName.setText(person.getFirstName());

        //input last name
        final TextField inputlName = new TextField();
        inputlName.setPromptText("Enter last name");
        inputlName.setPrefColumnCount(10);
        inputlName.setText(person.getLastName());

        //input phone
        final TextField inputPhone = new TextField();
        inputPhone.setPromptText("Enter phone(555-555-5555)");
        inputPhone.setPrefColumnCount(10);
        inputPhone.setText(person.getPhoneNumber());

        //input Address
        final TextField inputAddress = new TextField();
        inputAddress.setPromptText("Enter Address (Street, city,state,zip)");
        inputAddress.setPrefColumnCount(10);
        inputAddress.setText(person.getAddress());


        textFields.getChildren().addAll(inputfName,inputlName,inputAddress,inputPhone);
        GridPane.setConstraints(textFields,0,0);
        layout.getChildren().addAll(textFields);




        saveBtn.setOnAction(e -> {
            Connection conn = null;
            PreparedStatement stmt = null;

            try {
                conn = DBConnection.getConnection();

                person.setPhoneNumber(inputPhone.getText());
                person.setFirstName(inputfName.getText());
                person.setLastName(inputlName.getText());
                person.setAddress(inputAddress.getText());


                String sql = "UPDATE JASWData.Clients SET firstName = ?  , lastName = ? , phoneNumber = ? , address = ? WHERE id=?;";

                stmt = conn.prepareStatement(sql);

                stmt.setString(1, person.getFirstName());
                stmt.setString(2, person.getLastName());
                stmt.setString(3, person.getPhoneNumber());
                stmt.setString(4, person.getAddress());
                stmt.setInt(5, person.getId());
                stmt.executeUpdate();


                DBViewTable.updateTable();
                newWindow.close();



            }catch(Exception c){
                System.out.println("Error with inserting into the Database");
            }finally{
                try {
                    conn.close();
                    stmt.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        return new Scene(layout);
    }
}
