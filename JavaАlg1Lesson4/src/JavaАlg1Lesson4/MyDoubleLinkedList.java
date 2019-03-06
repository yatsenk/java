package JavaАlg1Lesson4;

import JavaАlg1Lesson4.Interfaces.IDoubleLinkedList;
import JavaАlg1Lesson4.Interfaces.ILinkedList;

public class MyDoubleLinkedList<T> implements ILinkedList<T>, IDoubleLinkedList<T> {
    Link first; //инициализируется null, поэтому используем конструктор по-умолчанию
    Link last;
    int size;

    @Override
    public boolean isEmpty(){
        return size == 0; //или first = null
    }

    @Override
    public void insertFirst(T data){
        first = new Link(data, first, null);
        if (first.getNext() == null) {
            last = first;
        } else {
            first.getNext().setPast(first);
        }
        size++;
    }

    @Override
    public void insertLast(T data){
        last = new Link(data, null, last);
        if (last.getPast() == null) {
            last = first;
        } else {
            last.getPast().setNext(last);
        }
        size++;
    }

    @Override
    public T delete(){
        T tmp;

        if (size > 0) {
            tmp = first.getData();
        } else {
            tmp = null;
        }

        if (size > 1){
            first = first.getNext();
            first.setPast(null);
            size--;
        } else if (size == 1){
            first = last = null;
            size = 0;
        }

        return tmp;
    }

    @Override
    public T deleteLast(){
        T tmp;

        if (size > 0) {
            tmp = last.getData();
        } else {
            tmp = null;
        }

        if (size > 1){
            last = last.getPast();
            last.setNext(null);
            size--;
        } else if (size == 1){
            first = last = null;
            size = 0;
        }

        return tmp;
    }

    //удаляем все вхождения
    @Override
    public void delete(T data){
        Link tmp = first;
        while (tmp != null){
            if (tmp.getData().equals(data)) { // тут нам и equals нужен переопределенный
                size--;
                if (size == 1){ // возможно, этот код можно сократить, оперируя tmp.getNext() вместо tmp
                    tmp = first = last = null;
                } else if (tmp == first) {
                    first = tmp.getNext();
                    tmp.setPast(null);
                    tmp = first;
                } else if (tmp == last) {
                    last = tmp.getPast();
                    tmp.getPast().setNext(null);
                    tmp = null;
                } else {
                    tmp.getNext().setPast(tmp.getPast());
                    tmp.getPast().setNext(tmp.getNext());
                    tmp = tmp.getNext();
                }
            } else {
                tmp = tmp.getNext();
            }
        }
    }

    // возвращает строку, чтобы написать JUnit для других методов
    // не знаю, что важнее - производительность или тест всего класса
    @Override
    public String display(){
        StringBuilder sb = new StringBuilder();
        Link tmp = first;
        while (tmp != null){
            System.out.println(tmp.getData());
            sb.append(tmp.getData().toString()).append(" ");
            tmp = tmp.getNext();
        }
        return sb.toString();
    }

    /**
     * Ищем первое вхождение в случае String, Integer или наличия переопределенного toString.
     * Возвращает оригинальные данные.
     */
    @Override
    public T find(T search){
        Link tmp = first;
        while (tmp != null){
            if (tmp.getData().toString().contains(search.toString())){
                return tmp.getData();
            }
            tmp = tmp.getNext();
        }
        return null;
    }

    class Link{
        private T data;
        private Link next;
        private Link past;

        public Link(T data, Link next, Link past) {
            this.data = data;
            this.next = next;
            this.past = past;
        }

        public T getData() {
            return data;
        }

        public Link getNext() {
            return next;
        }

        public Link getPast() {
            return past;
        }

        public void setNext(Link next) {
            this.next = next;
        }

        public void setPast(Link past) {
            this.past = past;
        }
    }
}
