package smith.adam.database.DatabaseClasses;

public class Clients {

    final private String firstName;
    final private String lastName;
    private int client_id;
    final private String phoneNumber;
    final private String streetAdr;
    final private String unit;
    final private String city;
    final private String state;
    final private String zip;


    public Clients(){
        this.firstName = "";
        this.lastName = "";
        this.phoneNumber = "";
        this.streetAdr = "";
        this.unit = "";
        this.city = "";
        this.state = "";
        this.zip = "";

    }

    public Clients(String firstName, String lastName, String phoneNum, String streetAddr, String unit, String city, String state, String zip, int id){ // constructor with all fields
        this.firstName = firstName; //first name
        this.lastName = lastName; //last name
        this.client_id = id; // id, auto increments in the DATABASE
        this.phoneNumber = phoneNum; // phone number
        this.streetAdr = streetAddr; // street address
        this.unit = unit; //unit if applicable
        this.city = city; // city
        this.state = state; // state
        this.zip = zip; //zip code
    }

    public Clients(String firstName, String lastName, String phoneNum, String streetAddr, String city, String state, String zip, int id){ //Constructor without a unit, non apartment type building
        this.firstName = firstName; //first name
        this.lastName = lastName; //last name
        this.client_id = id; // id, auto increments in the DATABASE
        this.phoneNumber = phoneNum; // phone number
        this.streetAdr = streetAddr; // street address
        this.city = city; // city
        this.state = state; // state
        this.zip = zip; //zip code
        this.unit = "";
    }

    public Clients(String firstName, String lastName, String phoneNum, String streetAddr, String unit, String city, String state, String zip){ //Constructor without a id
        this.firstName = firstName; //first name
        this.lastName = lastName; //last name
        this.phoneNumber = phoneNum; // phone number
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
    public int getId() {
        return client_id;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getStreetAdr() {
        return streetAdr;
    }
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

    public String toString(){
        String out;
        if (this.unit == null){
            out = this.firstName + " " + this.lastName + " " + this.phoneNumber + " " + this.streetAdr + " " + this.city + " " + this.city + " " + this.zip;
            return out;
        }else {
            out = this.firstName + " " + this.lastName + " " + this.phoneNumber + " " + this.streetAdr + " " + this.unit + " " + this.city + " " + this.city + " " + this.zip;
            return out;
        }
    }
}
