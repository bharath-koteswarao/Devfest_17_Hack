package bharat.sos_tarp;

/**
 * Created by koteswarao
 * ${CLASS}
 */

public class Person {
    public String name;
    public String password;
    public String reason;

    public Person(String name, String password,String reason) {
        this.name = name;
        this.reason = reason;
        this.password = password;
    }

    public Person() {

    }

    @Override
    public String toString() {
        return this.name + " " + this.reason;
    }
}
