package JavaАlg1Lesson4;

import JavaАlg1Lesson4.Interfaces.IStack;

public class StackMyLinkedList<T> implements IStack<T> {
    private MyLinkedList<T> list = new MyLinkedList<>();

    @Override
    public void push(T data) {
        list.insertFirst(data);
    }

    @Override
    public T pop() {
        return list.delete();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String display() {
        return list.display();
    }
}
