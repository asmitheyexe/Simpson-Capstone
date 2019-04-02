package smith.adam.database.DatabaseClasses;

public class Clients {

    private final String firstName;
    private final String lastName;
    private int client_id;
    private final String phoneNumber;
    private final String companyName;
    private final String streetAdr;
    private final String unit;
    private final String city;
    private final String state;
    private final String zip;


    public Clients(){
        this.firstName = "";
        this.lastName = "";
        this.phoneNumber = "";
        this.companyName = "";
        this.streetAdr = "";
        this.unit = "";
        this.city = "";
        this.state = "";
        this.zip = "";

    }

    public Clients(String firstName, String lastName, String phoneNum, String companyName, String streetAddr, String unit, String city, String state, String zip, int id){ // constructor with all fields
        this.firstName = firstName; //first name
        this.lastName = lastName; //last name
        this.client_id = id; // id, auto increments in the DATABASE
        this.phoneNumber = phoneNum; // phone number
        this.companyName = companyName;
        this.streetAdr = streetAddr; // street address
        this.unit = unit; //unit if applicable
        this.city = city; // city
        this.state = state; // state
        this.zip = zip; //zip code
    }

    public Clients(String firstName, String lastName, String phoneNum, String companyName, String streetAddr, String city, String state, String zip, int id){ //Constructor without a unit, non apartment type building
        this.firstName = firstName; //first name
        this.lastName = lastName; //last name
        this.client_id = id; // id, auto increments in the DATABASE
        this.phoneNumber = phoneNum; // phone number
        this.companyName = companyName; // has company name associated
        this.streetAdr = streetAddr; // street address
        this.city = city; // city
        this.state = state; // state
        this.zip = zip; //zip code
        this.unit = "";
    }

    public Clients(String firstName, String lastName, String phoneNum, String streetAddr, String city, String state, String zip, int id){ //Constructor without a unit, non apartment type building no company
        this.firstName = firstName; //first name
        this.lastName = lastName; //last name
        this.client_id = id; // id, auto increments in the DATABASE
        this.phoneNumber = phoneNum; // phone number
        this.companyName = ""; // has company name associated
        this.streetAdr = streetAddr; // street address
        this.city = city; // city
        this.state = state; // state
        this.zip = zip; //zip code
        this.unit = "";
    }

    public Clients(String firstName, String lastName, String phoneNum, String companyName, String streetAddr, String unit, String city, String state, String zip){ // constructor with no id
        this.firstName = firstName; //first name
        this.lastName = lastName; //last name
        this.phoneNumber = phoneNum; // phone number
        this.companyName = companyName;
        this.streetAdr = streetAddr; // street address
        this.unit = unit; //unit if applicable
        this.city = city; // city
        this.state = state; // state
        this.zip = zip; //zip code
    }

    public Clients(String firstName, String lastName, String phoneNum, String companyName, String streetAddr, String city, String state, String zip, boolean isCompany){ //Constructor without a unit MUST PASS IN SOME BOOLEAN TO GET THIS CONSTRUCTOR
        this.firstName = firstName; //first name
        this.lastName = lastName; //last name
        this.phoneNumber = phoneNum; // phone number
        this.companyName = companyName;
        this.streetAdr = streetAddr; // street address
        this.city = city; // city
        this.state = state; // state
        this.zip = zip; //zip code
        this.unit = "";
    }

    public Clients(String firstName, String lastName, String phoneNum, String streetAddr, String unit, String city, String state, String zip){ //Constructor without company name
        this.firstName = firstName; //first name
        this.lastName = lastName; //last name
        this.phoneNumber = phoneNum; // phone number
        this.companyName = "";
        this.streetAdr = streetAddr; // street address
        this.city = city; // city
        this.state = state; // state
        this.zip = zip; //zip code
        this.unit = unit;
    }

    public Clients(String firstName, String lastName, String phoneNum, String streetAddr, String city, String state, String zip){ //Constructor without a company or unit
        this.firstName = firstName; //first name
        this.lastName = lastName; //last name
        this.phoneNumber = phoneNum; // phone number
        this.companyName = "";
        this.streetAdr = streetAddr; // street address
        this.city = city; // city
        this.state = state; // state
        this.zip = zip; //zip code
        this.unit = "";
    }




    public int getClient_id() {return client_id;}

    public String getFirstName() {return firstName;}
    public String getLastName() {
        return lastName;
    }
    public String getCompanyName() {
        return companyName;
    }
    public int getId() {
        return client_id;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getStreetAdr() {return streetAdr;}
    public String getUnit() {
        return unit;
    }
    public String getCity() {
        return city;
    }
    public String getState() {
        return state;
    }
    public String getZip() {
        return zip;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", client_id=" + client_id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", companyName='" + companyName + '\'' +
                ", streetAdr='" + streetAdr + '\'' +
                ", unit='" + unit + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}
