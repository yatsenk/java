package JavaАlg1Lesson8;

import java.util.LinkedList;
import java.util.Objects;

/**
 * Простой
 */

public class MyHashSimple {
    private int hashArraySize;  // размер таблицы хэщей
    private LinkedList<Node> [] keyArray;

    public MyHashSimple(int hashArraySize) {

        this.hashArraySize = // потому что именно столько символов юникода, а нулевой массив нам не нужен
                hashArraySize > 65535 ? 65535 : hashArraySize > 0 ? hashArraySize : 66;
        keyArray = new LinkedList[this.hashArraySize]; // как мы помним, так массивы делать нельзя
        for (int i = 0; i < this.hashArraySize; i++) { // сразу инициализируем списки, чтобы не проверять при вставке
            keyArray[i] = new LinkedList<>();
        }
    }

    public void put(String name, int price){
        keyArray[getHash(name)].add(new Node(name,price));

//        строка выше делает так, только в одну строку
//        int hash = getHash(name);
//        Node tmp = new Node(name,price);
//        keyArray[hash].add(tmp);
    }

    public int get(String name){
        for (Node l : keyArray[getHash(name)] ) {
            if (l.name.equals(name)) return l.price;
        }
        return -1;
    }

    private int getHash(String s){
        return s.charAt(0) % hashArraySize;
//        смысла в toLowerCase нет, если все равно mod, а так имеет смысл делать массивы до 66 букв
//        return s.toLowerCase().charAt(0) % hashArraySize;
    }
}


class Node{
    String name;
    int price;

    Node(String name, int price) {
        this.name = name;
        this.price = price;
    }
}