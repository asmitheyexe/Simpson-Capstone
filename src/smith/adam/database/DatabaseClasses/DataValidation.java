package smith.adam.database.DatabaseClasses;

import java.util.regex.*;

public class DataValidation {
    final private static String firstNameField = "^((?![0-9\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\=\\-\\[\\]\\{\\}\\;\\:\\\"\\\\\\/\\<\\>\\?]).)+$";
    final private static String phoneFieldsPattern = "[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]"; //checks for straight numbers ex. 5555555555 inputed
    final private static String phoneFieldsPattern2 = "[0-9][0-9][0-9]-[0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]"; //checks for pattern as 555-555-5555 inputed
    final private static String statePattern = "[a-z|A-Z][A-Z|a-z]"; // checks for correct State pattern. Should be 2 letters, Iowa = IA, Maybe make list of allowed states for future validation
    final private static String unitPattern = "[0-9]+"; // Checks unit matches Pattern
    final private static String zipPattern ="[0-9][0-9][0-9][0-9][0-9]"; //Checks if Zip matches Pattern

    //returns true or false if pattern works
    public static boolean isFirstLastNamePattern(String name){
        return Pattern.matches(firstNameField,name);
    }

    public static boolean isStatePattern(String state){
        return Pattern.matches(statePattern, state);
    }

    public static boolean isPhoneNumberPattern(String number){
       return (Pattern.matches(phoneFieldsPattern,number) || Pattern.matches(phoneFieldsPattern2,number));
    }

    public static boolean isUnitPattern(String unit){
        return Pattern.matches(unitPattern, unit);
    }

    public static boolean isZipPattern(String zip){
        return Pattern.matches(zipPattern, zip);
    }

    public static Clients validateEntry(Clients person){
        int[] dataIsValid = new int[5];
        String errors = "";
        if(DataValidation.isFirstLastNamePattern(person.getFirstName()) && DataValidation.isFirstLastNamePattern(person.getLastName())){
            dataIsValid[0] = 1;
        }else{
            if(!DataValidation.isFirstLastNamePattern(person.getFirstName())){
                errors += "First name is formatted wrong, \n";
            }
            if(!DataValidation.isFirstLastNamePattern(person.getLastName())){
                errors += "Last name is formatted wrong, \n";
            }
        }
        if(DataValidation.isPhoneNumberPattern(person.getPhoneNumber())){
            dataIsValid[1] =1;
        }else{
            errors += "Phone number is formatted wrong, \n";
        }

        if(DataValidation.isUnitPattern(person.getUnit()) || person.getUnit().equals("")){
            dataIsValid[2] =1;
        }else{
            errors += "Unit is formatted wrong, \n";
        }
        if(DataValidation.isStatePattern(person.getState())){
            dataIsValid[3] =1;
        }else{
            errors += "State is formatted wrong (Iowa = IA), \n";
        }
        if(DataValidation.isZipPattern(person.getZip())){
            dataIsValid[4] = 1;
        }else{
            errors += "Zip code is formatted wrong \n";
        }

        int total = 0;
        for(int i : dataIsValid){
            total += i;
        }
        if(total == 5){
            return person;
        }
        ErrorMessageFactory.displayError(errors);

        return new Clients();
    }



}
