package JavaАlg1Lesson2;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        ArrayOperator o = new ArrayOperator();
        ArraySort s = new ArraySort();
        ArrayFind f = new ArrayFind();
        long start,finish; //таймеры производительности для sort

//        1. Создать массиов большого размера...
        int [][] m = new int[13][10000000];

//        2. Написать методы удаления...
//        Я понимаю, что это - усложненные методы относительно базы, но очень хотелось
        int [] m_small = new int[19];
        o.generateRand(m_small);


        System.out.println("2. Операции");
        System.out.println("Исходный массив");
        o.print(m_small);

        System.out.println("Удаляем позицию 5");
        m_small = o.deleteByPosition(m_small,5);
        o.print(m_small);

        System.out.println("Удаляем значение 5");
        m_small = o.deleteByValue(m_small,5);
        o.print(m_small);

//        ...добавления
        System.out.println("Добавляем на позицию 2");
        m_small = o.insert(m_small,2,5);
        o.print(m_small);

//        ...поиска

        System.out.println();
        System.out.println("2. Поиск");
        System.out.println("Линейно ищем значение 4");
        int linearFind = f.linearFind(m_small,4);
        System.out.println(linearFind == m_small.length ? "Не найдено" : "Позиция: "+linearFind);
        System.out.println("Поиск занял " + f.getStep() + " шагов");
        System.out.println();

        Arrays.sort(m_small); //сортируем библиотечным qsort
        o.print(m_small);
        System.out.println("Теперь массив отсортирован и мы можем бинарно найти 4");
        int binaryFind = f.binaryFind(m_small,4);
        System.out.println(binaryFind == m_small.length ? "Не найдено" : "Позиция: "+binaryFind);
        System.out.println("Поиск занял " + f.getStep() + " шагов");
        System.out.println("Library binarySearch: " + Arrays.binarySearch(m_small,4));

//        3. Заполнить массив...
        System.out.println();
        //o.generateReverse(m[0]); //в обратной последовательности
        o.generateRand(m[0]); //случайными числами
        for (int i = 1; i < m.length; i++) o.fullCopy(m[0],m[i]);

//        4. Написать методы сортировок...
        System.out.println("4. Сортировка");
//          s.bubbleSort1(m[0]); // самая простая
        s.bubbleSort2(m[1]); // более быстрый пузырек как в методичке
        s.selectSort(m[2]); // самая быстрая на массивах в 1000
//          s.insertSort1(m[3]); // быстрее пузырька и писать быстрее пузырька - больше преимуществ нет
//          s.insertSort2(m[4]); // самая быстрая на отсортированных массивах и простая
//          s.insertSort3(m[5]); // самая быстрая на рандомных массивах больше 10 000
        s.insertSort4(m[6]); // версия предыдущей, нет вызовов других функций, самая технически оптимизированная

//        Библиотечные сортировки
        start = System.currentTimeMillis();
        Arrays.sort(m[7]);
        finish = System.currentTimeMillis();
        System.out.println("  8. Library sort: " + (finish - start) + "ms");

        start = System.currentTimeMillis();
        Arrays.parallelSort(m[8]);
        finish = System.currentTimeMillis();
        System.out.println("  9. Library parallelSort: " + (finish - start) + "ms");

//        Расческа
//        s.combSort1(m[9]); //неправильный алгоритм
        s.combSort2(m[10]); //правильный простой и быстрый алгоритм
//            System.out.println("   Array exchanges: " + s.getStep());
        s.qsort(m[11]); //я почти понял, как работает математика внутри
//            System.out.println("   Array exchanges: " + s.getStep());
        s.countingSort(m[12]); //кушает O(n) памяти со сложностью O(n), применим только на массивах с известным диапазоном значений
                               //также используется в Library Sort
    }

}
