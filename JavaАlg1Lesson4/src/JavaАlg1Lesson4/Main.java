package JavaАlg1Lesson4;

public class Main {

    public static void main(String[] args) {
        System.out.println("1. Тестируем односвязный список: ");
        L1LocalTest();
        System.out.println("2. Тестируем двусвязный список: ");
        L2LocalTest();
        System.out.println("3. Тестируем стек поверх односвязного списка: ");
        stackLocalTest();
        System.out.println("4. Тестируем очередь поверх двусвязного списка: ");
        myQueueTest();
        System.out.println("5. Тестируем итератор: ");
        // тесты лежат Tests/MyListIteratorTest и я не знаю,
        // как их сюда прикрутить и надо ли это вообще делать
    }

    public static void L1LocalTest() {
        MyLinkedList myLinkedList = new MyLinkedList();
        //Что-то мне кажется неправильным в том, что эта конструкция вообще работает. Тут пробегал php.
        // Я думаю, так делать нельзя. Совсем нельзя.
        myLinkedList.insertFirst(444);
        myLinkedList.insertFirst("hello");
        myLinkedList.insertFirst(myLinkedList);
        myLinkedList.display();
    }

    public static void L2LocalTest() {
        MyDoubleLinkedList<String> my = new MyDoubleLinkedList<>();
        my.insertFirst("444");
        my.insertFirst("hello");
        my.insertFirst("something");
        my.insertLast("last data");
        my.display();
        System.out.println("  Ищем h: " + my.find("h"));

        System.out.println("  Удаляем 444");
        my.delete("444");
        my.display();

        System.out.println("  Удаляем первое значение");
        my.delete();
        my.display();

        System.out.println("  Удаляем последнее значение");
        my.deleteLast();
        my.display();

        my.delete();
    }

    public static void stackLocalTest() {
        StackMyLinkedList<Integer> m = new StackMyLinkedList<>();

        m.push(12);
        m.push(15);
        m.push(17);
        System.out.println("  Содержимое списока: " + m.display());
        System.out.println("  Верхний элемент: " + m.pop());
        m.pop();
        m.pop();
        m.pop(); // пытаемся удалить из уже пустого стека
        System.out.println("  Пустой список: "+ m.display());
    }

    public static void myQueueTest() {
        QueueMyDoubleLinkedList<String> q = new QueueMyDoubleLinkedList<>();

        q.insert("aaa");
        q.insert("bbb");
        q.insert("ccc");
        System.out.println("  Записали три элемента: " + q.display());
        System.out.println("  Считываем aaa из другого конца очереди: " + q.delete());
        q.delete();
        q.delete();
        q.delete();// удаляем несуществующий элемент
        System.out.println("  Пустая очередь: " + q.display());
    }
}
