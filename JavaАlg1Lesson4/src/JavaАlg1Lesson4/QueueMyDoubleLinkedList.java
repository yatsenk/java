package JavaАlg1Lesson4;

import JavaАlg1Lesson4.Interfaces.IQueue;

public class QueueMyDoubleLinkedList<T> implements IQueue<T> {
    private MyDoubleLinkedList<T> d = new MyDoubleLinkedList<>();
    @Override
    public boolean isEmpty() {
        return d.isEmpty();
    }

    @Override
    public void insert(T data) {
        d.insertFirst(data);
    }

    @Override
    public T delete() {
        return d.deleteLast();
    }

    @Override
    public String display() {
        return d.display();
    }
}
