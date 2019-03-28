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

    public Clients validateEntry(Clients person){
        int[] dataIsValid = new int[5];
        if(DataValidation.isFirstLastNamePattern(person.getFirstName()) && DataValidation.isFirstLastNamePattern(person.getLastName())){
            dataIsValid[0] = 1;
        }else{
            /*
            Pop error for names
             */
            System.out.println("First or Last name is bad");
        }
        if(DataValidation.isPhoneNumberPattern(person.getPhoneNumber())){
            dataIsValid[1] =1;
        }else{
            /*
            Pop error for names
             */
            System.out.println("Phone Number is bad");
        }

        if(DataValidation.isUnitPattern(person.getUnit())){
            dataIsValid[2] =1;
        }else{
            /*
                Error popup
             */
            System.out.println("Unit is bad");
        }
        if(DataValidation.isStatePattern(person.getState())){
            dataIsValid[3] =1;
        }else{
            /*
            Pop error for names
             */
            System.out.println("State is bad");
        }
        if(DataValidation.isZipPattern(person.getZip())){
            dataIsValid[4] = 1;
        }else{
            /*
              Pop up error
              using ErrorMessageFactory once implemented
             */
            System.out.println("Zip is bad");
        }

        int total = 0;
        for(int i : dataIsValid){
            total += i;
        }
        if(total == 5){
            return person;
        }


        return new Clients();
    }



}
