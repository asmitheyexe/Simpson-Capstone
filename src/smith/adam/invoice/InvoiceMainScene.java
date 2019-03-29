package smith.adam.invoice;

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

    private static Scene mainBox(){
        BorderPane layout = new BorderPane();

        GridPane leftPane = new GridPane();
        GridPane centerPane = new GridPane();
        GridPane rightPane = new GridPane();

        leftPane.getChildren().addAll(CustomerViewBox.returnView());
        leftPane.setPadding(new Insets(10));
        centerPane.getChildren().add(AdditionalCostView.returnAdditionCostView());
        centerPane.setPadding(new Insets(10));
        rightPane.getChildren().addAll(BaseBidView.returnBidView());
        rightPane.setPadding(new Insets(10));


        layout.setCenter(centerPane);
        layout.setLeft(leftPane);
        layout.setRight(rightPane);
        return new Scene(layout);
    }
}
