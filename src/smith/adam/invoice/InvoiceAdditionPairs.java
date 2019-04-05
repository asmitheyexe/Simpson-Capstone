package smith.adam.invoice;

import java.util.List;
/*
    This class is a nice way to store a list of Descriptions of Prices that should be parallel arrays.
    This class is used to transport data from AdditionalCostView to InvoiceExtractAllTexts
 */
public class InvoiceAdditionPairs {

    private List<String> descriptions;
    private List<Double> prices;

    public InvoiceAdditionPairs(List<String> descriptions, List<Double> prices){
        this.descriptions = descriptions;
        this.prices = prices;
    }

    public List<String> getDescriptions(){return descriptions;}
    public List<Double> getPrices(){return prices;}
}
