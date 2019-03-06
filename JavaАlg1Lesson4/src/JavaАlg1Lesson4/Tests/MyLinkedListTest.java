package JavaАlg1Lesson4.Tests;

import JavaАlg1Lesson4.MyLinkedList;
import org.junit.Assert;
import org.junit.Test;


// пробуем сделать свой первый юнит-тест
public class MyLinkedListTest {

    @Test
    public void isEmpty() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();

        boolean expected = true;
        boolean actual = myLinkedList.isEmpty();
        Assert.assertEquals(expected,actual);

        Integer i = 3;
        myLinkedList.insertFirst(i);
        myLinkedList.insertFirst(5);
        expected = false;
        actual = myLinkedList.isEmpty();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void delete() {
        String s = "test data";
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();

        myLinkedList.insertFirst(s);
        String actual = myLinkedList.delete();
        Assert.assertEquals(s,actual);
    }

    @Test
    public void display() {
        String s1 = "aaa";
        String s2 = "bbb";
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        myLinkedList.insertFirst(s1);
        myLinkedList.insertFirst(s2);
        myLinkedList.display();
        // не упал - значит, работает
    }

    @Test
    public void find() {
        Integer s1 = 5533;
        Integer s2 = 3445;
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.insertFirst(s1);
        myLinkedList.insertFirst(s2);

        Integer actual = myLinkedList.find(5);

        Assert.assertEquals(s2,actual);
    }

    @Test
    public void findNDelete() {
        Integer s0 = 3445;
        Integer s1 = 5533;
        Integer s2 = 666;
        Integer s3 = 3445;
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.insertFirst(s0);
        myLinkedList.insertFirst(s1);
        myLinkedList.insertFirst(s2);
        myLinkedList.insertFirst(s3);

        String expected = s2.toString()+" "+s1.toString()+" ";
        myLinkedList.delete(3445);
        String actual = myLinkedList.display();

        Assert.assertEquals(expected,actual);
    }
}