package JavaАlg1Lesson4.Interfaces;

public interface IQueue<T> {
    boolean isEmpty();
    void insert(T data);
    T delete();
    String display();

}
