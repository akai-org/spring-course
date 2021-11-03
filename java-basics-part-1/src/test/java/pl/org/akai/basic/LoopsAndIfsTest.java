package pl.org.akai.basic;

import org.junit.jupiter.api.Test;

import java.util.List;

class LoopsAndIfsTest {

    @Test
    void ifStatement() {
        boolean bool = true;
        if(bool) {
           System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    @Test
    void whileLoop() {
        List<String> list = List.of("Hello", "World", "Goodbye");

        int i = 0;
        while (i < 5) {
            System.out.println(i);
            i++;
        }
    }

    @Test
    void forLoop() {
        List<String> list = List.of("Hello", "World", "Goodbye");

        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        for (String s : list) {
            System.out.println(s);
        }
    }

    @Test
    void switchTest() {
        String season = "Winter";
        switch (season) {
            case "Spring":
                System.out.println("Mamy aktualnie wiosnę");
                break;
            case "Summer":
                System.out.println("Mamy aktualnie lato");
                break;
            case "Autumn":
                System.out.println("Mamy aktualnie jesień");
                break;
            case "Winter":
                System.out.println("Mamy aktualnie zimę");
                break;
            default:
                System.out.println("Nie znam takiej pory roku");
        }
    }
}
