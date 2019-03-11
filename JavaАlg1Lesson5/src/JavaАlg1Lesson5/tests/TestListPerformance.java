package JavaАlg1Lesson5.tests;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Тестируем производительность листов
 * Кто сказал, что LinkedList обязательно быстрее на вставку - тот не учитывал наш случай конвертирования в массив (когда mem заканчивается)
 * А вообще производительность надо мерять индивидуально в каждом случае. Думаю, есть более удобные способы замера участка кода.
 */
public class TestListPerformance {
    private static int rounds, maxRounds, repeats;

    /**
     *     Тест на х10 циклов, с приведением к одному циклу
     *      10000 раундов,      2 ms массив,      2 ms лист
     *     100000 раундов,      4 ms массив,      8 ms лист
     *    1000000 раундов,     26 ms массив,    200 ms лист
     *   10000000 раундов,   1933 ms массив,   5381 ms лист
     *
     *         Тест на x1000 циклов, ArrayList vs. LinkedList
     *        100 раундов,     21 ms Array,     22 ms Linked
     *       1000 раундов,     48 ms Array,     48 ms Linked
     *      10000 раундов,    281 ms Array,    257 ms Linked
     *     100000 раундов,   1664 ms Array,   1579 ms Linked
     *    1000000 раундов,  19199 ms Array,  37888 ms Linked
     */

    public static void main(String[] args) {
        rounds = 100;
        maxRounds = 1000000;
        repeats = 1000; // усредняем циклы

        long arrayTime, listTime;

        System.out.println();
        System.out.println("      Тест на x" + repeats + " циклов, ArrayList vs. LinkedList");

        while (rounds <= maxRounds){

            arrayTime = testArray();
            listTime = testList();

            System.out.printf("%10d раундов, %6d ms Array, %6d ms Linked",rounds,arrayTime,listTime);
            System.out.println();

            rounds *=10;
        }
    }

    private static long testArray() {
        long start = System.currentTimeMillis();

        for (int i = 0; i < repeats; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j < rounds; j++) { list.add(j); }
            Integer [] arrayInteger = list.toArray(new Integer[0]);
        }

        long finish = System.currentTimeMillis();
        return finish - start;
    }

    private static long testList() {
        long start = System.currentTimeMillis();

        for (int i = 0; i < repeats; i++) {
            LinkedList<Integer> list = new LinkedList<>();
            for (int j = 0; j < rounds; j++) { list.add(j); }
            Integer[] linkedInteger = list.toArray(new Integer[0]);
        }

        long finish = System.currentTimeMillis();
        return finish - start;
    }

}
