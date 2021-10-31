package pl.org.akai.basic;

public enum EnumExample {
    STUDENT(1),
    EMPLOYEE(2);

    int value;

    EnumExample(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
