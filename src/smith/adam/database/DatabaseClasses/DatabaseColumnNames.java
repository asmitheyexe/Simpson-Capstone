package smith.adam.database.DatabaseClasses;
/*
    Used some code from https://stackoverflow.com/questions/696782/retrieve-column-names-from-java-sql-resultset


    Need to figure out how to better do a column varification step.

    To speed up the program i will just store the columns in private variables with getters

 */
import java.sql.ResultSetMetaData;
import java.util.LinkedList;
import java.util.List;

public class DatabaseColumnNames {

    final private static String firstNameColumn = "firstName";
    final private static String lastNameColumn = "lastName";
    final private static String phoneColumn = "phoneNumber";
    final private static String streetAddressColumm = "streetAdr";
    final private static String unitColumn = "unit";
    final private static String cityColumn = "city";
    final private static String stateColumn = "state";
    final private static String zipColumn = "zip";
    final private static String idColumn = "client_id";

    public static List<String> returnColumns()throws Exception{
        List<String> columnNames = new LinkedList<>();

        columnNames.add(DatabaseColumnNames.getFirstNameColumn());
        columnNames.add(DatabaseColumnNames.getLastNameColumn());
        columnNames.add(DatabaseColumnNames.getPhoneColumn());
        columnNames.add(DatabaseColumnNames.getStreetAddressColumm());
        columnNames.add(DatabaseColumnNames.getUnitColumn());
        columnNames.add(DatabaseColumnNames.getCityColumn());
        columnNames.add(DatabaseColumnNames.getStateColumn());
        columnNames.add(DatabaseColumnNames.getZipColumn());
        columnNames.add(DatabaseColumnNames.getIdColumn());

        return columnNames;
    }

    public static String getFirstNameColumn() {return firstNameColumn;}

    public static String getLastNameColumn() {return lastNameColumn;}

    public static  String getPhoneColumn() {return phoneColumn;}

    public static  String getStreetAddressColumm() {return streetAddressColumm;}

    public static  String getUnitColumn() {return unitColumn;}

    public static  String getCityColumn() {return cityColumn;}

    public static  String getStateColumn() {return stateColumn;}

    public static  String getZipColumn() {return zipColumn;}

    public static  String getIdColumn() {return idColumn;}



}
