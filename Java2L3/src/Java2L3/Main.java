package Java2L3;


import java.util.*;

public class Main {

    public static void main(String[] args) {
        list();
        phonebookCheck();
    }

    /**
     Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
     Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
     Посчитать, сколько раз встречается каждое слово.
     */
	static void list(){
        String [] words = {"apple","tomato","apple","qiwi","pomodoro","cheese","qiwi","blackberry","qiwi","potato","pepper","qiwi"};

//        TreeMap - сортированный, HashSet -  вероятно быстрее, LinkedHashSet - в порядке добавления

//        Set<String> unique = new TreeSet<>();
//        Collections.addAll(unique,words);

        //формируем коллекцию уникальных значений в одну строку
        //не обязательно, поскольку следующее задание проделает также эту работу
        Set<String> unique = new TreeSet<>(Arrays.asList(words));
        System.out.println("\nUnique sorted set: " + unique);

        //считаем повторяющиеся значения
        Map <String,Integer> count = new TreeMap<>();

        for (String s : words){
            if (count.containsKey(s)){ count.put(s, count.get(s) + 1); }
            else { count.put(s,1); }
        }

        System.out.println("\nWords quantity:");
        for (Map.Entry<String, Integer> word : count.entrySet()){
            System.out.println(word.getKey() + ": " + word.getValue());
        }
    }

    /**
     Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и телефонных номеров.
     В этот телефонный справочник с помощью метода add() можно добавлять записи, а с помощью метода get()
     искать номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько
     телефонов (в случае однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны.
     */
    static void phonebookCheck(){
        Phonebook p = new Phonebook();
        p.add("Vasya","+1-926-222-22-22");
        p.add("Petya", "+7-495-333-33-33");
        p.add("Stephan","+43-72-111-31-11");
        p.add("Petya","+7-922-343-71-16");

        System.out.println("\nЗаписи в телефонной книге: ");
        p.get("Vasya");
        p.get("Petya");
    }
}


// поигрался с версией на несовпадении хеша и equals
// извлечь значение невозможно, поэтому сразу можно ничего не записывать
// кажется, со строками и мержем действительно оптимальный вариант
class Phonebook{
    SortedMap<String, String> map;

    // представления не имею, как будет использоваться
    // конкретно эта телефонная книга,
    // но так мы можем выводить по алфавиту как в телефоне
    Phonebook(){ map = new TreeMap<>(); }

    void add(String surname, String phone){ map.merge(surname, phone,(oldVal, newVal) -> oldVal + " " + newVal); }

    void get(String surname){ System.out.println(surname + ": " + map.get(surname)); }
}