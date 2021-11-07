package main;

public class Main {

    private static int add(int x, int y) {
        return x + y;
    }

    public static void main(String[] args) {
        System.out.println("Hello!" + add(12, 3));
        System.out.println(add(3, 5));
    }
}
