package JavaАlg1Lesson3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static String test = ("Some test string some length, Hello GB!");

    public static void main(String[] args) throws IOException {
//          1. Реализации стека растущим массивом, объектами и ArrayList
        System.out.println("\nСтек массив:");
        testMyStackArr(12); //некратно расходуем память
        System.out.println("\nСтек на ссылках: ");
        testMyStackObj();
        System.out.println("\nArrayList объект:");
        testArrayList();


//          2. Реализация очереди массивом
        System.out.println("\nОчередь массивом:");
        testMyQueueArr();

//          3. Реализация дека через LinkedList и объектами
        System.out.println("\nДек на ссылках:");
        testMyDeque();
        System.out.println("\nДек LinkedList:");
        testLinkedList();

//        4. Приоритетная очередь
        System.out.println("\nПриоритетная очередь, расширяющая массив MyQueueArr");
        testMyQueuePrior();
        System.out.println("\nПриоритетная очередь, расширяющая массив MyDeque");
        testMyDequePrior();

//        5. Переворот строк
        System.out.println("\nОтдельно выполняем задание 2 и переворачиваем строки.");
        reverse();
    }


    static void testMyStackArr(int min_size) {

        MyStackArr stack = new MyStackArr(min_size);

        for (int i = 0; i < test.length(); i++) { stack.push(test.charAt(i)); }

        System.out.println("1. Стек после записи подрос в размерах:");
        stack.print();

        System.out.print("2. Читаем последнее записанное значение. ");
        System.out.println(stack.getSize() + " символ: " + stack.peer());

        System.out.println("3. Сжимаем стек и смотрим, что получилось:");
        stack.compact();
        stack.print();

        System.out.println("3. Забираем элементы и удаляем стек, заодно выполняя задание 2 'переворачивает строки': ");
        while (stack.getSize() > 0) System.out.print(stack.pop());

        System.out.println();
        System.out.println("4. Сбрасываем стек и делаем новый размером min_size.");
        stack.push('Z');
        stack.reset();
        stack.print();
    }

    static void testMyStackObj() {
        MyStackObj stack = new MyStackObj();

        for (int i = 0; i < test.length(); i++) { stack.push(test.charAt(i)); }

        System.out.print("2. Читаем последнее записанное значение. ");
        System.out.println(stack.getSize() + " символ: " + stack.peer());

        System.out.println("3. Забираем элементы и удаляем стек, заодно выполняя задание 2 'переворачивает строки': ");
        while (stack.getSize() > 0) System.out.print(stack.pop());
        System.out.println();
    }


    static void testArrayList(){
        ArrayList stack = new ArrayList<>(32); //проигнорируем тип для char, зато установим размер

        for (int i = 0; i < test.length(); i++) { stack.add(test.charAt(i)); }

        System.out.println("1. Стек после записи:");
        System.out.println(stack.toString());
        System.out.print("2. Читаем последнее записанное значение. ");
        System.out.println(stack.get(stack.size() - 1));
        System.out.println("3. Читаем и удаляем стек, заодно выполняя задание 2 'переворачивает строки': ");
        while (stack.size() > 0) {
            System.out.print(stack.get(stack.size() - 1));
            stack.remove(stack.size() -1);
        }
        System.out.println();
        System.out.println("4. Сбрасываем стек и делаем новый.");
        stack.clear();
    }

    static void testMyQueueArr(){
        MyQueueArr queue = new MyQueueArr();
        for (int i = 0; i < test.length(); i++) { queue.add(test.charAt(i)); }
        System.out.println("1. Очередь после записи подросла в размерах:");
        queue.print();

        System.out.println("2. Забираем часть элементов и добавляем новые - тестируем кольцо:");
        for (int i = 0; i <= 20 ; i++) { System.out.print(queue.remove()); }//удалили 21
        for (int i = 0; i < test.length(); i++) { queue.add(test.charAt(i)); }//добавили ту же строку
        System.out.println();
        queue.print();

        System.out.println("3. Забираем еще немного элементов и сжимаем очередь до минимального размера:");
        for (int i = 0; i <= 44 ; i++) { queue.remove(); }
        queue.compact();
        queue.print();

        System.out.println("4. Читаем первый элемент в очереди: " + queue.peek());
    }

    static void testMyDeque() { //для разнообразия я сначала написал этот тест, а потом реализацию класса
        MyDeque deque = new MyDeque();

        //пишем чего-нибудь в дек
        for (int i = 0; i < test.length(); i++) { deque.addFirst(test.charAt(i)); }
        deque.add('|');
        for (int i = 0; i < test.length(); i++) { deque.addLast(test.charAt(i)); }

        System.out.println("1. Дек после записи: ");
        System.out.println(deque.toArray());

        //удаляем чего-нибудь из дек
        System.out.print("2. Читаем и удаляем: ");
        for (int i = 0; i <= 10; i++) { System.out.print(deque.removeFirst()); }
        for (int i = 0; i <= 10; i++) { deque.removeLast(); }

        System.out.println();
        System.out.println("3. Дек после удаления: ");
        System.out.println(deque.toArray());

        while (deque.toArray().length() > 0){ deque.removeFirst(); } //издеваемся над производительностью вместо deque.getSize()
        System.out.println("4. Проверяем, что все удалили и ничего не упало. Массив содержит "
                + deque.toArray() + " размера " + deque.getSize());
    }

    static void testLinkedList() {
        LinkedList<String> list = new LinkedList<>();
        list.add("b");
        list.addLast("c");
        list.addFirst("a");
        System.out.println("1. Добавили элементы: "+ list.toString());
        list.removeFirst();
        list.removeLast();
        System.out.println("2. Удалили элементы: "+ list.toString());
    }

    static void testMyQueuePrior(){
        MyQueuePrior p = new MyQueuePrior(8);
        /**
         * Бинарный поиск на items = 13 выдавал неправильную позицию
         * для вставки символа f, потому что упирался в последовательность
         * одинаковых маленьких элементов и не может из нее выпрыгнуть.
         * Исправил в строке 64 класса.
         * Производительность мы не выигрываем, все равно массив двигать.
         * Теперь мы знаем, почему здесь не используется бинарный поиск.
        */
        p.addBinary("bacdfeik"); //на этом раньше падало
        p.addBinary("aabababbabzzzfqttr1");
        p.addLinear("ddqstt;skfgpwvn;xkn");
        p.print(); //вообще это диагностическая печать, поэтому смотрится не очень
    }

    static void testMyDequePrior() { //для разнообразия я сначала написал этот тест, а потом реализацию класса
        MyDequePrior p = new MyDequePrior();

        //пишем чего-нибудь в очередь
        p.add(test);
        p.add('|');
        p.add("VeryVeryLong_and_stableString@WithW");

        System.out.println("1. Очередь после записи: ");
        System.out.println(p.toArray());

        // удаляем чего-нибудь из дек
        // так как getSize() вызывается каждый раз - то удаляем мы половину
        System.out.print("2. Читаем и удаляем: ");
        for (int i = 0; i < p.getSize(); i++) { System.out.print(p.remove()); }

        System.out.println();
        System.out.println("3. Очередь после удаления: ");
        System.out.println(p.toArray());

        // издеваемся над производительностью вместо p.getSize()
        // заодно проверяем корректность работы ссылок
        while (p.toArray().length() > 0){ p.remove(); }
        System.out.println("4. Проверяем, что все удалили и ничего не упало. Массив содержит "
                + p.toArray() + " размера " + p.getSize());
    }

    static void reverse() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        System.out.println("Давайте перевернем пару строк StringBuilder: ");
        for (int i = 0; i < 2; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(br.readLine());
            System.out.println(sb.reverse());
        }

        System.out.println("И деком еще: ");
        for (int i = 0; i < 2; i++) {
            MyDeque d = new MyDeque();
            d.addFirst(br.readLine());
            System.out.println(d.toArray());
        }
    }
}
