package sample;



import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import smith.adam.database.DatabaseClasses.DBViewTable;
import smith.adam.invoice.InvoiceMainScene;


import java.io.FileInputStream;

public class CentralControl extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage window;

        //tables
        window = primaryStage;
        window.setTitle("JASW Contracting LLC.");

        window.setScene(MainScene());
        window.show();

    }

    private Scene MainScene() throws Exception{

        String currentDirectory = System.getProperty("user.dir");
        System.out.println(currentDirectory);
        String invoiceImagePath = currentDirectory + "\\src\\smith\\adam\\database\\DatabaseClasses\\storage\\pictures\\invoiceImage.png";
        String dbImagePath = currentDirectory + "\\src\\smith\\adam\\database\\DatabaseClasses\\storage\\pictures\\databaseImage.png";
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10,10,10,10));

        FileInputStream input = new FileInputStream((invoiceImagePath));
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);

        Button invoiceBtn = new Button("Create Invoice", imageView);
        GridPane.setConstraints(invoiceBtn,0,0);


        input = new FileInputStream((dbImagePath));
        image = new Image(input);
        imageView = new ImageView(image);

        Button databaseBtn = new Button("View Clients", imageView);
        GridPane.setConstraints(databaseBtn,1,0);



        int maxHeight = 300;

        databaseBtn.setPrefWidth(maxHeight);
        invoiceBtn.setPrefWidth(maxHeight);

        //The invoice will be implemented later
        invoiceBtn.setOnAction(e -> {
            try{
                InvoiceMainScene.newWindow();
            }catch(Exception c){
                System.out.println("Error with Invoice button " + c);
            }
        });

        databaseBtn.setOnAction(e -> {
            try{
                DBViewTable.newWindow();
            }catch (Exception C){
                System.out.println("Error with database button");
            }
        });
        layout.getChildren().addAll(databaseBtn,invoiceBtn);

        Scene myScene = new Scene(layout);
        return myScene;

    }

    public static void main(String[] args) throws Exception{

        launch(args);

    }
}
