package smith.adam.database.DatabaseClasses;

public class Clients {
    private String firstName;
    private String lastName;



    private int client_id;
    private String phoneNumber;
    private String streetAdr;
    private String unit;
    private String city;
    private String state;
    private String zip;


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

    public void setClient_id(int client_id) {this.client_id = client_id;}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return client_id;
    }

    public void setId(int id) {
        this.client_id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreetAdr() {
        return streetAdr;
    }

    public void setStreetAdr(String streetAdr) {
        this.streetAdr = streetAdr;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
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
