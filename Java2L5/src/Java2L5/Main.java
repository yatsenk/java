package Java2L5;

/**
 * 1, 2, 4 потока на моей системе 2*2core на 30M значений.
 * Как видим, Hyper-Threading обладает ограниченной эффективностью на математических задачах.
 * OneThread arr: 50763 ms
 * TwoThread arr: 32619 ms
 * MultiThread arr: 21138 ms
 */

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        /**
         * 1) Создают одномерный длинный массив
         */
        // так как мы передаем массивы по указателю, то для чистого замера массивы должны быть уникальные
        int array_size = 100000; // должен быть кратен количеству вычислительных потоков или сработает функция досчета (что тоже неплохо)
        int array_quantity = 3; // по количеству тестовых массивов для трех методов
        float[][] array = new float[3][array_size]; // целевые массивы
        long timer; // таймер вычислений
        /**
         * 2) Заполняют этот массив единицами
         */
        for (int i = 0; i < array_quantity; i++) {
            for (int j = 0; j < array_size ; j++) { array[i][j] = 1; }
        }

        timer = System.currentTimeMillis();
        calcOneThread(array[0],0,array_size, 0);
        System.out.println("OneThread arr: " + (System.currentTimeMillis() - timer) + " ms");

        timer = System.currentTimeMillis();
        calcTwoThread(array[1]);
        System.out.println("TwoThread arr: " + (System.currentTimeMillis() - timer) + " ms");

        timer = System.currentTimeMillis();
        calcMultiThread(array[2]);
        System.out.println("MultiThread arr: " + (System.currentTimeMillis() - timer) + " ms");
    }

    /**
     * Количество запущенных воркеров (потоков) вместо join
     * Этот вариант выглядит интересным, хотя и небезопасным - запрос на смену значения может быть одновременным.
     * Больше не используется - действительно так и получается.
     */
    //private static int countThreads = 0;
    //private static Integer countThreads = 0; // а так IDEA сообщает про non-final в случае использования syncronized,
    //так что нам надо вероятно создавать класс счетчика - оставим это на более глубокое погружение в потоки.

    /**
     * Основной однопоточный метод, вычисляющий всю математику. Полностью переиспользуется.
     * @param arr входящий массив
     * @param start ячейка начала вычислений в массиве
     * @param end ячейка окончания вычислений в массиве
     * @param offset смещение для прибавления к i в алгоритме вычислений на случай разбиения массива на несколько
     */
    private static void calcOneThread(float[] arr, int start, int end, int offset){
        //countThreads++; // больше не используется
        /**
         * Проходят по всему массиву и для каждой ячейки считают новое значение по формуле
         */
        // в которой я исправил целочисленное деление на double, потому что математика
        for (int i = start; i < end; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2d + (i+offset) / 5d) * Math.cos(0.2d + (i+offset) / 5d) * Math.cos(0.4d + (i+offset) / 2d));
        }
        //countThreads--;
    }

    /**
     * Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.
     */
    private static void calcTwoThread(float[] arr){
        final int threads = 2; // выбираем количество потоков
        int half = arr.length / threads; // условно половина массива
        List<Thread> workers = new ArrayList<>(threads); // список потоков для синхронизации

        float [][] divArray = new float[threads][half];
        //копируем массив в подмассивы
        for (int i = 0; i < threads; i++) { System.arraycopy(arr,i*half,divArray[i],0,half); }

        //вычисления можно было запускать в процессе копирования в предыдущем цикле, все равно процессор многоядерный у всех
        for (int i = 0; i < threads; i++) {
            final int finalI = i;
            // лябды позволяют любые методы запускать в потоках, поэтому мы не будем писать дублирующий код
            Thread my = new Thread(() -> calcOneThread(divArray[finalI],0,half,finalI*half));
            my.start();
            workers.add(my);
        }

        //пока считается - досчитываем хвост
        calcMod(arr,threads);

        //ждем окончания вычислений
        for (Thread t : workers){
            try { t.join(); }
            catch (InterruptedException e) { System.out.println("Кто-то перехватил наш основной поток, чего не ожидалось."); }
        }
        //и собираем массив из подмассивов
        for (int i = 0; i < threads; i++) { System.arraycopy(divArray[i],0,arr,i*half,half); }
    }

    /**
     * Метод, который подстраивается под количество доступных ядер пользователя
     * и не копирует массивы, а выполняет сегментирование имеющегося массива.
     * (Кстати, а почему в ДЗ есть требование копировать, но нет требования выравнивать?)
     */
    private static void calcMultiThread(float[] arr){
        int threads = Runtime.getRuntime().availableProcessors(); //количество доступных ядер в системе пользователя
        int part = arr.length / threads; //разбиваем массив на сегменты для вычисления

        Thread [] workers = new Thread[threads]; // список потоков для синхронизации через просто-массив, раз мы уже знаем количество

        for (int i = 0; i < threads; i++) {
            final int finalI = i;
            Thread tr = new Thread(() -> calcOneThread(arr,finalI*part,(finalI+1)*part,0));
            tr.start();
            workers[i] = tr;
        }

        //пока считается - досчитываем хвост в основном потоке
        calcMod(arr,threads);
        //ждем окончания вычислений
        for (Thread t : workers){
            try { t.join(); }
            catch (InterruptedException e) { System.out.println("Кто-то перехватил наш основной поток, чего не ожидалось."); }
        }
    }

    /**
     * Так как это наш собственный метод, мы можем написать наиболее эффективный алгоритм,
     * досчитывающий то, что мы не досчитали, чтобы нам не пришлось выравнивать массив по воркерам,
     * как возможно пришлось бы делать с библиотечным методом.
     */
    private static void calcMod(float[] arr, int threads){
        if (arr.length % threads == 0) return;
        //будем считать в один поток, потому что конкретно этот алгоритм нет смысла параллелить по причине быстрой работы
        calcOneThread(arr,arr.length - arr.length % threads,arr.length,0);
    }

    /**
     * == Так делать нельзя. ==
     * Так как нам для корректных вычислений нужна синхронизация, то я написал
     * функцию ожидания обнуления счетчика потоков. Надеюсь, она не слишком энергоемкая с 10ms.
     * И наверное, эта функция приведет к сбоям в продакшене.
     * == Я уже проверил. Поэтому так делать нельзя. ==
     */
//    private static void waitALittle() {
//        while (countThreads > 0) {
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}