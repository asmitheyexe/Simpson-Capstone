package smith.adam.database.DatabaseClasses;
/*
    Used some code from https://stackoverflow.com/questions/696782/retrieve-column-names-from-java-sql-resultset

 */
import java.sql.ResultSetMetaData;
import java.util.LinkedList;
import java.util.List;

public class DatabaseColumnNames {

    private String firstNameColumn;
    private String lastNameColumn;
    private String phoneColumn;
    private String streetAddressColumm;
    private String unitColumn;
    private String cityColumn;
    private String stateColumn;
    private String zipColumn;
    private String idColumn;

    // makes an Object for column Names if need be
    public DatabaseColumnNames()throws Exception{
        try {
            ResultSetMetaData rsmd =SqlStatements.getAllColumns();
            this.firstNameColumn = rsmd.getColumnName(1);
            this.lastNameColumn = rsmd.getColumnName(2);
            this.phoneColumn = rsmd.getColumnName(3);
            this.streetAddressColumm = rsmd.getColumnName(4);
            this.unitColumn = rsmd.getColumnName(5);
            this.cityColumn = rsmd.getColumnName(6);
            this.stateColumn = rsmd.getColumnName(7);
            this.zipColumn = rsmd.getColumnName(8);
            this.idColumn = rsmd.getColumnName(9);
        }catch(Exception c){
            System.out.println("Error while retrieving column names!! DatabaseColumnNames.java" + c);
        }
}

    public static List<String> returnColumns()throws Exception{
        List<String> columnNames = new LinkedList<>();
        DatabaseColumnNames TempObj = new DatabaseColumnNames();

        columnNames.add(TempObj.getFirstNameColumn());
        columnNames.add(TempObj.getLastNameColumn());
        columnNames.add(TempObj.getPhoneColumn());
        columnNames.add(TempObj.getStreetAddressColumm());
        columnNames.add(TempObj.getUnitColumn());
        columnNames.add(TempObj.getCityColumn());
        columnNames.add(TempObj.getStateColumn());
        columnNames.add(TempObj.getZipColumn());
        columnNames.add(TempObj.getIdColumn());

        return columnNames;
    }

    public String getFirstNameColumn() {return firstNameColumn;}

    public String getLastNameColumn() {return lastNameColumn;}

    public String getPhoneColumn() {return phoneColumn;}

    public String getStreetAddressColumm() {return streetAddressColumm;}

    public String getUnitColumn() {return unitColumn;}

    public String getCityColumn() {return cityColumn;}

    public String getStateColumn() {return stateColumn;}

    public String getZipColumn() {return zipColumn;}

    public String getIdColumn() {return idColumn;}



}
