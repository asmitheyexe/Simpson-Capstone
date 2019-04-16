package smith.adam.invoice;
/*
   This may be the most complicated class for all the invoice classes
   This class handles formatting a text file so the Python script will correctly
   read information in. This file has specific output styles that are necessary for the
   code to work.
 */
import javafx.scene.control.TextField;
import smith.adam.database.DatabaseClasses.TextFieldFactory;
import smith.adam.database.DatabaseClasses.Clients;
import java.io.File;
import java.io.PrintStream;
import java.util.List;


public class InvoiceExtractAllTexts {

    private static List<TextField> customerInfo;

    public static void printAllTexts(){

        customerInfo = CustomerViewBox.getTextFields();

        TextField bidPrice = BaseBidView.getBidField();

        InvoiceAdditionPairs additionalCosts = AdditionalCostView.getPricesAndDescriptions();
        List<String> descriptions = additionalCosts.getDescriptions();
        List<Double> prices = additionalCosts.getPrices();

        String currentDirectory = System.getProperty("user.dir");
        String pathToFile = currentDirectory +"\\ExcelFiles\\DataFiles\\DataFile.txt";

        String command = "python "+ currentDirectory+"\\ExcelFiles\\capstone.py";
        System.out.println(command);
        File myFile = new File(pathToFile);
        // open the file

        try{
            PrintStream writeToFile = new PrintStream(myFile);
            //write customer info first, follow it by 'End'
            //If person has a company affliate then write it else write NA
            // Print address information follow by End
            // continue the trend for all the columns with similarities
            System.out.println("Writing Customer Info \n");
            int x = 0;
            for(TextField cust : customerInfo){
                if(x == 3){
                    writeToFile.println("End");
                    if(cust.getText().equals("")){
                        System.out.println(cust.getText());
                        writeToFile.println("NA");
                        writeToFile.println("End");
                        x++;
                    }else {
                        System.out.println(cust.getText());
                        writeToFile.println(cust.getText());
                        x++;
                        writeToFile.println("End");
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
                //the python will concantinate all the strings here and split them by the commas
                //this will give me a array of desc, price, desc,price making it easier to create the excel file
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

    public static boolean checkPersonInfo(){
        Clients person = TextFieldFactory.makePersonFromTextFields(customerInfo, 0);
        if(!person.getFirstName().equals("")){
            return true;
        }else if(!person.getCompanyName().equals("")){
            return true;
        }
        return false;
    }

}
