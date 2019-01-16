package Java1Lesson3;

import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static int wins; //победы в игре

    public static void main(String[] args) {

        //1.
        wins = 0;
        while (randomGame());

        //2.
        wins = 0;
        while (wordGame());


        sc.close();
    }


    // 1. Написать программу, которая загадывает случайное число от 0 до 9
    private static boolean randomGame(){

        System.out.println();
        System.out.println("Игра Угадай число");
        System.out.print("Задайте диапазон угадывания от 0 до: ");

        int number = (int) (Math.random() * (intInput() + 1)); // это число угадываем
        int attempts = 3; //количество попыток
        int attempt; //текущая попытка

        //цикл игры
        do {
            if (attempts > 1) {
                System.out.println("У вас осталось " + attempts + " попытки.");
            } else {
                System.out.println("У вас осталось " + attempts + " попытка.");
            }

            attempts--;

            System.out.print("Введите ваше число: ");
            attempt = intInput();

            if (attempt > number) {
                System.out.println("Загаданное число меньше");
            } else if (attempt < number) {
                System.out.println("Загаданное число больше");
            } else {
                System.out.println("Вы угадали!");
                wins++;
                break;
            }
        } while (attempts !=0);

        if (attempts == 0){
            System.out.println("Вы проиграли.");
        }

        System.out.println();
        System.out.println("Количество ваших побед: " + wins);
        System.out.print("Повторить игру еще раз? 1 - да / 0 - нет: ");
        return intInput()==1;
    }

    //проверка ввода числа
    private static int intInput() {
        while (!sc.hasNextInt()){
            sc.nextLine();
            System.out.print("Вы ввели не число. Попробуйте еще раз: ");
        }
        return sc.nextInt();
    }


    // 2. Создать​​ массив​ ​из ​​слов
    // При запуске программы компьютер загадывает слово
    private static boolean wordGame(){
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pumpkin", "pineapple", "potato"};
        String word = words[(int) (Math.random()*(words.length))]; //загаданное слово

        System.out.println();
        System.out.println("Игра Угадай слово");
        System.out.println("Введите слово: ");

        String input; //переменная ввода пользователем

        while (true) {
            input = sc.nextLine();
            if (input.equals(word)){
                System.out.println("Вы угадали! Правильное слово: " + word);
                wins++;
                break;
            } else {
                for (int i = 0; i < 15; i++) {
                    if (i < word.length() && i < input.length() && word.charAt(i) == input.charAt(i))
                    {
                        System.out.print(word.charAt(i));
                    } else {
                        System.out.print("#");
                    }
                }
            }
            System.out.println();
            System.out.println("Попробуйте еще раз: ");
        }

        System.out.println();
        System.out.println("Количество ваших побед: " + wins);
        System.out.print("Повторить игру еще раз? 1 - да / 0 - нет: ");
        return intInput()==1;
    }

}
