package ru.geekbrains.lesson_2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Калькулятор!");
        System.out.println("Выберите режим: \n1. Сложение \n2. Вычитание \n3. Умножение \n4. Деление");

        Scanner scan = new Scanner(System.in);
        int operation = scan.nextInt();

        System.out.print("Введите первое число: ");
        double a = scan.nextDouble();
        System.out.print("Введите второе число: ");
        double b = scan.nextDouble();

        if (operation == 1) {
            a = a + b;
        } else if (operation == 2) {
            a = a - b;
        } else if (operation == 3){
            a = a * b;
        } else if (operation == 4 && b != 0) {
            a = a / b;
        } else System.out.print("Что-то пошло не так!");
        System.out.print("Результат вычислений: " + a);
    }
}
