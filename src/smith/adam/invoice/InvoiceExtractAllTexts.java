package smith.adam.invoice;

import javafx.scene.control.TextField;

import java.io.File;
import java.io.PrintStream;
import java.util.List;


public class InvoiceExtractAllTexts {

    public static void printAllTexts(){
        List<TextField> customerInfo = CustomerViewBox.getTextFields();
        TextField bidPrice = BaseBidView.getBidField();

        InvoiceAdditionPairs additionalCosts = AdditionalCostView.getPricesAndDescriptions();
        List<String> descriptions = additionalCosts.getDescriptions();
        List<Double> prices = additionalCosts.getPrices();

        String currentDirectory = System.getProperty("user.dir");
        String pathToFile = currentDirectory +"\\ExcelFiles\\DataFiles\\DataFile.txt";

        String command = "python "+ currentDirectory+"\\ExcelFiles\\capstone_script\\venv\\capstone.py";
        System.out.println(command);
        File myFile = new File(pathToFile);

        try{
            PrintStream writeToFile = new PrintStream(myFile);

            System.out.println("Writing Customer Info \n");
            int x = 0;
            for(TextField cust : customerInfo){
                if(x == 3){
                    writeToFile.println("End");
                    if(cust.getText().equals("")){
                        System.out.println(cust.getText());
                        writeToFile.println("NA");
                        x++;
                    }else {
                        System.out.println(cust.getText());
                        writeToFile.println(cust.getText());
                        x++;
                    }
                }else if(x == 5) {
                    writeToFile.println("End");
                    x++;
                }else
                 {
                    System.out.println(cust.getText());
                    writeToFile.println(cust.getText());
                    x++;
                }

            }
            writeToFile.println("End");

            System.out.println("\nAll additional Costs that may have been entered\n");

            for(int i = 0; i < descriptions.size(); i++){
                writeToFile.println(String.format("%s, %.2f,", descriptions.get(i), prices.get(i)));

                System.out.println(String.format("Description = %s , price = %.2f", descriptions.get(i), prices.get(i)));
            }
            writeToFile.println("End");
            System.out.println(String.format("\nBid price is %.2f", Double.parseDouble(bidPrice.getText())));
            writeToFile.println(String.format("%.2f", Double.parseDouble(bidPrice.getText())));
            writeToFile.println("End");

            writeToFile.close();

            Runtime.getRuntime().exec(command);


        }catch(Exception C){
            System.out.println("Error writing to file" + C);
        }



    }

}
