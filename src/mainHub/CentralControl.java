package sample;


import javafx.geometry.Insets;
import javafx.scene.layout.*;
import smith.adam.database.Clients;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import smith.adam.database.DBInsert;
import smith.adam.database.GetClientsDAO;

public class CentralControl extends Application /*implements EventHandler<ActionEvent>*/ {
    //Button class

    private Button button, btn1, btn2;

    Scene scene1, scene2;
    private static Stage window;



    public static void setMyScene(Scene scene){
        window.setScene(scene);
    }

    private static Scene dataTable_Scene(){
        TableView<Clients> table;
        table = GetClientsDAO.makeColumns();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(table);
        Scene dataScene = new Scene(vBox);
        return dataScene;
    }

    private static VBox dataTable_vBox(){
        TableView<Clients> table;
        table = GetClientsDAO.makeColumns();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(table);
        vBox.maxWidth(500);
        return vBox;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{


        //tables
        window = primaryStage;
        window.setTitle("DataBase");

        window.setScene(CentralControl.MainBox());
        window.show();



        //Alertboxs




        /*
        //scene swapping
        window = primaryStage;


        Label label = new Label("First Scene!!!!!!!!");

        //button1
        Button btn1 = new Button("got to scene 2?");
        btn1.setOnAction(e -> window.setScene(scene2));

        //layout1 - children are laid out in vertical column
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label,btn1);
        scene1 = new Scene(layout1,200,200);

        //button2
        Button btn2 = new Button("This scene is bad go to 1?");
        btn2.setOnAction(e -> window.setScene(scene1));

        //layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().addAll(btn2);
        scene2 = new Scene(layout2,600,300);

        window.setScene(scene1);
        window.setTitle("Title of window");
        window.show();

*/

       /*
        //Setting a button onto the layout and scene
        primaryStage.setTitle("JASW CentralControl Application");
        button = new Button();
        button.setText("FirstButton!");
*/
        /*//setOnACtion looks for a handle method, Make a new class for this in future
        button.setOnAction(this);*/

        /*
        //annonamous functions with events, dont need to check for source!
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Lambda function!");
            }
        });*/
/*
        button.setOnAction(e -> System.out.println("Hey now this is crazy!"));
        /*
        LAMBDA FUNCTIONS THIS WAY
        e -> {
        -CODE-
        }

        so example
        button.setOnAction(e -> {

        --code--

        });
         */
/*
        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout, 300,200);
        primaryStage.setScene(scene);
        primaryStage.show();

        */


    }
/*
    //This is the method a event looks for. have to check for source
    @Override
    public void handle(ActionEvent event){
        if(event.getSource()==button){
            System.out.println("You clicked me!");
        }
    }
*/

    //gets all the people


    public static void main(String[] args) throws Exception{

        launch(args);

    }

    private static Scene MainBox(){

        HBox topMenu = new HBox();

        Button btnCreate = new Button("Insert");
        btnCreate.setOnAction(e -> DBInsert.newWindow());
        Button buttonB = new Button("Edit");

        Button buttonC = new Button("Delete");

        topMenu.getChildren().addAll(btnCreate, buttonB, buttonC);

        topMenu.setSpacing(20);

        BorderPane borderpane = new BorderPane();
        borderpane.setPadding(new Insets(10, 0, 10, 10));
        borderpane.setTop(topMenu);


        VBox centerMenu = CentralControl.dataTable_vBox();
        centerMenu.setPadding(new Insets(10,10,10,10));
        centerMenu.setPrefSize(100,100);
        borderpane.setCenter(centerMenu);

        Label copyright = new Label("JASW LLC. 2019");

        VBox bottomMenu = new VBox(copyright);

        borderpane.setBottom(bottomMenu);

        Scene scene = new Scene(borderpane, 1000,500);
    return scene;
    }
}
