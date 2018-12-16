package ru.geekbrains.guess_the_number;

    import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Игра 'Угадай число'. \nВведите диапазон угадывания: ");
        //проверка ввода
        check();
        int range = scanner.nextInt();
        int number = (int) (Math.random() * (range - 1) + 1);
        play(range, number);
        scanner.close();
    }

    private static void check(){ //проверка ввода числа
        while (!scanner.hasNextInt()) {
            System.out.print("Упс, это было не число. Попробуйте еще раз: ");
            scanner.next();
        }
    }

    private static void play(int range, int number){ //метод-процедура игры
        int i = 1;
        int input = 0;
        while (i != 0){
            System.out.print("Введите число в диапазоне от 1 до " + range + ": ");
            //проверка ввода
            check();
            input = scanner.nextInt();
            if (input == number) {
                System.out.println("\nПравильно! Задуманное число: " + number + "\nВы угадали верно с " + i + " попытки.");
                i = 0;
            } else if (input < number) {
                System.out.println("Неверно. Задуманное число больше.");
                i++;
            } else {
                System.out.println("Неверно. Задуманное число меньше.");
                i++;
            }

        }
    }
}
