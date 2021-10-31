package pl.org.akai.basic;

public class ChildClass extends ClassExample{
    private String surname;

    public String getSurname() {
        return surname;
    }

    public ChildClass(String surname) {
        this.surname = surname;
    }
}
