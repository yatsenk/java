package JavaАlg1Lesson8.Test;

import java.util.LinkedList;
import java.util.Random;

/**
 * https://habr.com/ru/company/skillbox/blog/443886/
 *
 * Нам дают упорядоченный массив и определенное значение. Затем просят создать функцию,
 * которая возвращает true или false, в зависимости от того, может ли сумма любых двух чисел
 * из массива быть равной заданному значению.
 */

public class GoogleArray {
    static int arraySize = 10000000;
    static int [] array = new int[arraySize]; // массив
    static int div = arraySize/100000; // для удобства сравнения порядков
    static int target = arraySize; // искомое

    static boolean isOK = false; // флаг для циклического перебора


    public static void main(String[] args) {
        long starttime;

        fillArray();

//        starttime = System.nanoTime();
//        System.out.println("Метод 1. Сумма есть в массиве: " + check1(target) + " за " + ((float)(System.nanoTime() - starttime)/div));
//        starttime = System.nanoTime();
//        System.out.println("Метод 2. Сумма есть в массиве: " + check2(target) + " за " + ((float)(System.nanoTime() - starttime)/div));
//        starttime = System.nanoTime();
//        System.out.println("Метод 3. Сумма есть в массиве: " + check3(target) + " за " + ((float)(System.nanoTime() - starttime)/div));
        starttime = System.nanoTime();
        System.out.println("Метод 4. Сумма есть в массиве: " + check4(target) + " за " + ((float)(System.nanoTime() - starttime)/div));
//        starttime = System.nanoTime();
//        System.out.println("Метод 5. Сумма есть в массиве: " + check5(target) + " за " + ((float)(System.nanoTime() - starttime)/div));
//        starttime = System.nanoTime();
//        System.out.println("Метод 4/5: Сумма есть в массиве: " + checkAdapt(target) + " за " + ((float)(System.nanoTime() - starttime)/div));
        starttime = System.nanoTime();
        System.out.println("Метод 6: Сумма есть в массиве: " + checkAdapt(target) + " за " + ((float)(System.nanoTime() - starttime)/div));
//        starttime = System.nanoTime();
//        System.out.println("Метод 4/6: Сумма есть в массиве: " + checkAdapt2(target) + " за " + ((float)(System.nanoTime() - starttime)/div));


        System.out.println("\nВремя выполнения в ns x" + div);
        //System.out.println("Массив поиска: " + Arrays.toString(array));

        /**
         * Надо добраться до профайлеров, иначе мерять производительность получается чуть не вручную из-за взаимного влияния методов.
         */
    }

    public static void fillArray(){
        Random rnd = new Random();
        // последовательно упорядоченный
        array[0] = 0;
        for (int i = 1; i < array.length; i++) {
            array[i] = array[i-1] + rnd.nextInt(2);
        }
    }

    /**
     * Неправильно: _любых двух_, а не последовательно стоящих
     */
    public static boolean check1(int target){
        for (int i = 0; i < array.length; i++) {
            int sum = 0;
            for (int j = i; j < array.length; j++) {
                sum += array[j];
                if (sum == target) return true;
                else if (sum > target) break;
            }
        }
        return false;
    }

    /**
     * Усиленно занимаемся ерундой, используя рекурсивный полный перебор (но этим можно пользоваться для N>3 чисел).
     * Ну очень энергоемкая рекурсия, чувствую себя индусом.
     * Из чего следует, что решать NP-задачи рекурсивно - это плохо.
     */
    public static boolean check2(int target){
        // сначала формируем массив чисел, которые < target, потому что _любых двух_, а не одного
        LinkedList<Integer> ll = new LinkedList<>();
        for (int a : array) {
            if (a < target) ll.add(a);
            else break;
        }
        Integer [] tmpArray = ll.toArray(new Integer[0]);
        check2NP(tmpArray,target,tmpArray.length);
        return isOK;
    }

    //  запускаем перебор по массиву
    public static void check2NP(Integer[] tmpArray, Integer target,int rotate_length){
        if (rotate_length == 1) return;
        for (int i = 0; i < rotate_length; i++) {
            check2NP(tmpArray,target,rotate_length - 1);
            if (rotate_length == 2) check2calc(tmpArray, target);
            check2rotate(tmpArray,rotate_length);
        }
    }

    // вращаем хвосты
    public static void check2rotate(Integer[] tmpArray, int rotate_length){
        Integer i = tmpArray[rotate_length - 1];
        System.arraycopy(tmpArray,0,tmpArray,1,rotate_length - 1);
        tmpArray[0] = i;
    }

    // вычисляем, кто прав
    public static void check2calc(Integer[] tmpArray, Integer target){
        Integer sum = tmpArray[0];
        for (int i: tmpArray) {
            if (sum + i == target) isOK = true;
            else if (sum + i > target) break;
        }
    }

    /**
     * Для меня это выглядит наиболее оптимальным методом.
     * Проверяем вложенными циклами, выполняя отсечения лишних переборов.
     */
    public static boolean check3(int target){
        for (int i = 0; i < array.length; i++) {
            int first = array[i];
            if (first > target/2) break; // если слагаемое больше половины цели - следующие будут еще больше
            for (int j = i+1; j < array.length; j++) { // надо начиинать со следующего числа, иначе складываем с собой
                if (first + array[j] == target) return true;
                else if (first + array[j] > target) break;
            }
        }
        return false;
    }

    /**
     * Про бинарный поиск я и забыл, спасибо подсказке.
     * На некоторых наборах данных легко конкурирует со сходящимся алгоритмом 5.
     */
    public static boolean check4(int target){
        int min,med,max,top; // используемые в бинарном поиске переменные

        // ищем верхнюю границу бинарного поиска бинарным поиском, так мы сократим себе итерации поиска
        min = med = 0;
        max = array.length - 1;
        while (min <= max){
            med = (max + min) / 2;
            if (array[med] == target) break;
            else if (array[med] > target) max = med - 1;
            else min = med + 1;
        }
        top = med - 1; //med либо указыает на target, либо на target+1

        //ищем два числа между 0 и верхней границей поиска
        for (int i = 0; i <= top; i++)  { // перебираем индекс первого слагаемого
            int second = target - array[i]; // вычисляем второе слагаемое
            if (second < array[i]) break; // если ожидаемое второе слагаемое меньше первого - это мы уже перебирали, прекращаем

            // бинарный поиск в цикле
            min = (i+1); // min ставим больше индекса первого слагаемого
            max = top; // по верхней границе моиска
            while (min <= max){
                med = (max + min) / 2;
                if (array[med] == second) return true;
                else if (array[med] > second) max = med - 1;
                else min = med + 1;
            }
        }
        return false;
    }

    /**
     * Последовательно сходимся к середине. Вау!
     * А еще это самый простой алгоритм.
     */
    public static boolean check5(int target){
        int first = 0, last = array.length-1;
        while (first != last){
            int sum = array[first] + array[last];
            if (sum > target) last--;
            else if (sum < target) first++;
            else return true;
        }
        return false;
    }

    /**
     * Скорость поиска в зависимости от ожидаемого распределения слагаемых:
     *
     * Целевая сумма = размеру массива, слагаемые находятся в первых 10% и во второй половине массива
     * Метод 3. Сумма есть в массиве: true за 356849205145ns
     * Метод 4. Сумма есть в массиве: true за 56383125ns
     * Метод 5. Сумма есть в массиве: true за 6855542ns
     *
     * Целевая сумма = 1/10 размера массива, оба слагаемых находятся в первых 10% массива
     * Метод 3. Сумма есть в массиве: true за 11009063ns
     * Метод 4. Сумма есть в массиве: true за 24691ns
     * Метод 5. Сумма есть в массиве: true за 14727456ns
     *
     * Следствие: мы можем выбрать быстрый метод вычислений:
     * в первой половине массива используем бинарный поиск,
     * во второй - сходящийся.
     */

    public static boolean checkAdapt(int target){
        if (array[array.length / 2] > target) return check4(target);
        else return check5(target);
    }

    /**
     * Скрещиваем ежа с ужом: ищем верхний интервал бинарным поиском, а дальше продолжаем сходящимся.
     * Оптимизация сходящегося метода.
     */

    public static boolean check6(int target){
        int min,med,max,sum;

        // ищем верхнюю границу необходимого поиска бинарным поиском, так мы сократим себе итерации поиска
        min = med = 0;
        max = array.length - 1;
        while (min <= max){
            med = (max + min) / 2;
            if (array[med] == target) break;
            else if (array[med] > target) max = med - 1;
            else min = med + 1;
        }

        min = 0; max = med; // нашли, дальше ищем схождением
        while (min != max){
            sum = array[min] + array[max];
            if (sum > target) max--;
            else if (sum < target) min++;
            else return true;
        }
        return false;
    }


    /**
     * Оптимизированный адаптивный поиск: улучшен алгоритм схождения.
     * Идея провалилась, предыдущий 6 еж с ужом оказался идеальным вариантом.
     *
     */
    public static boolean checkAdapt2(int target){
        if (array[array.length / 3] > target) return check4(target);
        else return check6(target);
    }



}
