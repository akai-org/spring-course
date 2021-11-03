package pl.org.akai.basic;

public interface InterfaceExample {

    String getName();
    int getValue();

    default int getOne() {
        return 1;
    }
}
