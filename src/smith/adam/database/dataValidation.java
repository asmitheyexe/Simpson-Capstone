package smith.adam.database;

import java.util.regex.*;

public class dataValidation {
    public static boolean isName(String name){
        String pattern = "/[a-z|A-Z]+/";
        return Pattern.matches(pattern,name);
    }

    public static boolean isAddress(String addr){
        String pattern = "/[a-z|A-Z]+/";
        return Pattern.matches(pattern,addr);
    }

    public static boolean isNumber(String number){
        String pattern = "/[a-z|A-Z]+/";
        return Pattern.matches(pattern,number);
    }



}
