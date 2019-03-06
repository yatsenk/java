package JavaАlg1Lesson4.Interfaces;

// пробуем сделать интерфейс для списков
public interface ILinkedList<T> {
//    проверка на пустоту
    boolean isEmpty();
//    вставка первого элемента
    void insertFirst(T data);
//    удаление первого элемента
    T delete();
//    удаление элемента по значению
    void delete(T data);
//    вывод всех хранимых значений
    String display();
//    поиск по значению
    T find(T search);

}
