package lambdas;

/**
 * Person mit Vor- und Nachname. Diese Klasse nicht verändern!
 */
public class Person {

    private final String firstName;
    private final String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Person)) {
            return false;
        }
        Person other = (Person) obj;
        return firstName.equals(other.firstName) &&
                lastName.equals(other.lastName);
    }

    public String toString() {
        return firstName + " " + lastName;
    }
}
