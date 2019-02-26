package sample;



import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import smith.adam.database.DBViewTable;

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
        String invoiceImagePath = "D:\\Capstone\\Capstone_FX\\src\\smith\\adam\\database\\storage\\pictures\\invoiceImage.png";
        String dbImagePath = "D:\\Capstone\\Capstone_FX\\src\\smith\\adam\\database\\storage\\pictures\\databaseImage.png";
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

        databaseBtn.setOnAction(e -> {
            DBViewTable.newWindow();
        });
        layout.getChildren().addAll(databaseBtn,invoiceBtn);

        Scene myScene = new Scene(layout);
        return myScene;

    }

    public static void main(String[] args) throws Exception{

        launch(args);

    }
}
