package JavaАlg1Lesson4.Interfaces;

public interface IStack<T> {
    void push(T data);
    T pop();
    boolean isEmpty();
    String display();
}
