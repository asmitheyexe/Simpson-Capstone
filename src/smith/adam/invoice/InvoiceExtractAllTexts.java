package smith.adam.invoice;

import javafx.scene.control.TextField;

import java.util.List;

public class InvoiceExtractAllTexts {

    public static void printAllTexts(){
        List<TextField> customerInfo = CustomerViewBox.getTextFields();
        TextField bidPrice = BaseBidView.getBidField();

        InvoiceAdditionPairs additionalCosts = AdditionalCostView.getPricesAndDescriptions();
        List<String> descriptions = additionalCosts.getDescriptions();
        List<Double> prices = additionalCosts.getPrices();

        System.out.println("Customer Info \n");

        for(TextField cust : customerInfo){
            System.out.println(cust.getText());
        }

        System.out.println("\nAll additional Costs that may have been entered\n");

        for(int i = 0; i < descriptions.size(); i++){
            System.out.println(String.format("Description = %s , price = %.2f", descriptions.get(i), prices.get(i)));
        }

        System.out.println(String.format("\nBid price is %.2f", Double.parseDouble(bidPrice.getText())));
    }

}
