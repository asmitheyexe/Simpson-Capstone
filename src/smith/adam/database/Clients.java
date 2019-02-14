package smith.adam.database;

public class Clients {
    private String firstName;
    private String lastName;
    private int id;
    private String phoneNumber;
    private String address;

    public Clients(){
        this.firstName = "";
        this.lastName = "";
        this.id = 0;
        this.phoneNumber = "";
        this.address = "";
    }

    public Clients(String fn, String ln, int id, String pn, String addr){
        this.firstName = fn;
        this.lastName = ln;
        this.id = id;
        this.phoneNumber = pn;
        this.address = addr;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String toString(){
        String out = this.firstName + " " + this.lastName+ " " + this.id+ " " +this.phoneNumber+ " " +this.address;
        return out;
    }
}
