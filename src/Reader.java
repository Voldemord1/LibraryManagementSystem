// класс читатель в конструкторм, c геттеарми и сеттерами
public class Reader {
    private String ID;
    private String name;
    private String mobileNumber;
    private String email;
    
    public Reader(){
        ID = "1111111";
        name = "Ivanov";
        mobileNumber = "1234567890";
        email = "example@gmail.com";
    }

    public Reader(String ID, String name, String mobileNumber, String email) {
        this.ID = ID;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
