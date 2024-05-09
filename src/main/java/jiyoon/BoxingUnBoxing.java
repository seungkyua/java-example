package jiyoon;

public class BoxingUnBoxing {
    public static void main(String[] args) {
        Double PI = 3.14;  // auto boxing
        double pi = PI;    // auto unboxing

        Integer five = Integer.valueOf(5);     // boxing
        System.out.println(3 + five);            // auto unboxing

        Character c = Character.valueOf('c'); // boxing
        if ('c' == c) {                          // auto unboxing
            System.out.println("true");
        }
    }
}
