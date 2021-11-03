package pl.org.akai;

import org.junit.jupiter.api.Test;

class JavaTrapTest {

    @Test
    void booleanTest() {
        Boolean a = true;
        Boolean b = false;
        Boolean c = null;
        // three values
    }

    @Test
    void integerTest() {
        Integer firstInteger = 127;
        Integer secondInteger = 127;
        System.out.println(firstInteger == secondInteger);
        System.out.println(firstInteger.equals(secondInteger));
        System.out.println("============================");

        firstInteger = 128;
        secondInteger = 128;
        System.out.println(firstInteger == secondInteger);
        System.out.println(firstInteger.equals(secondInteger));
        System.out.println("============================");

        int primitiveFirstInteger = 128;
        int primitiveSecondInteger = 128;
        System.out.println(primitiveFirstInteger == primitiveSecondInteger);
        //System.out.println(primitiveFirstInteger.equals(primitiveSecondInteger));
    }

    @Test
    void stringTest() {
        String firstString = "Hello World!";
        String secondString = "Hello World!";
        System.out.println(firstString == secondString);
        System.out.println(firstString.equals(secondString));
        System.out.println("============================");

        String thirdString = new String("Hello World!");
        System.out.println(firstString == thirdString);
        System.out.println(firstString.equals(thirdString));
        System.out.println("============================");

        String fourthString = "Hello ";
        String fifthString = "World!";
        String sixthString = fourthString + fifthString;
        System.out.println(firstString == sixthString);
        System.out.println(firstString.equals(sixthString));
    }
}
