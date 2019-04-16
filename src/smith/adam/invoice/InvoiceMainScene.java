package smith.adam.invoice;
/*
    This class generates the main scene class
    It just acts as a glue for all the other classes
    for the invoice process
 */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InvoiceMainScene extends Application {
    private static Stage newWindow;

    @Override
    public void start(Stage primaryStage) throws Exception{
    }

    public static void newWindow()throws  Exception{
        newWindow = new Stage();
        newWindow.setScene(mainBox());
        newWindow.show();
    }

    public static void updateMainScreen(){
        newWindow.setScene(mainBox());
    }

    public static void closeWindow(){
        newWindow.close();
    }

    private static Scene mainBox(){
        //This is the main thing the user sees

        BorderPane layout = new BorderPane();

        GridPane leftPane = new GridPane();
        GridPane centerPane = new GridPane();

        leftPane.getChildren().addAll(CustomerViewBox.returnDefaultView());
        leftPane.setPadding(new Insets(10));
        centerPane.add(BaseBidView.returnBidView(),0,0);
        centerPane.add(AdditionalCostView.returnAdditionCostView(),0,1);
        centerPane.setPadding(new Insets(20));
        layout.setLeft(leftPane);
        layout.setCenter(centerPane);
        return new Scene(layout);
    }
}
