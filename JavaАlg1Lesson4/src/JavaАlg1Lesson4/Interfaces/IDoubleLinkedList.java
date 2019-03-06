package JavaАlg1Lesson4.Interfaces;

public interface IDoubleLinkedList<T> extends ILinkedList<T>{
//    вставляет последний элемент
    void insertLast(T data);
//    удаляет последний элемент
    T deleteLast();
}
