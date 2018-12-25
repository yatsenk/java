package Java1Lesson1;

public class Main {
    //1.
    public static void main(String[] args) {
        init(); // инициализируем примитивы
        System.out.println(calculate(5, 5, 5, 5));
        System.out.println(task10and20(10, 20));
        isPositiveOrNegative(-5);
        System.out.println(isNegative(0));
        greetings("Veronika");
        visokos(1900);
    }

    //2.
    private static void init() {
        byte by = -127;
        short sh = -32768;
        int in = -2000000000;
        long lo = (long) (-9 * 10e18);

        float flo = -200000.0f;
        double dou = 200000.0;

        boolean bo = true;
        char ch = 'a';
    }

    //3.
    private static int calculate(int a, int b, int c, int d) {
        return (a * (b + (c / d)));
    }

    //4.
    private static boolean task10and20(int a, int b) {
        return ((a + b) >= 10 && (a + b) <= 20);
    }

    //5.
    private static void isPositiveOrNegative(int x) {
        if (x >= 0) {
            System.out.println(x + " - положительное число");
        } else {
            System.out.println(x + " - отрицательное число");
        }
    }

    //6.
    private static boolean isNegative(int a) {
        return (a < 0);
    }

    //7.
    private static void greetings(String name) {
        System.out.println("Привет, " + name + "!");
    }

    //8.
    private static void visokos(int year) {
        if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {
            System.out.println(year + " - високосный.");
        } else System.out.println("В году " + year + " всего 355 дней.");
    }

}