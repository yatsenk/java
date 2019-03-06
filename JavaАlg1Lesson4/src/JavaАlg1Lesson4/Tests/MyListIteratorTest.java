package JavaАlg1Lesson4.Tests;

import JavaАlg1Lesson4.MyLinkedList;
import com.sun.istack.internal.NotNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Используем JUnit по назначению - проверка поведения общего
 * и в граничных случаях.
 *
 * Не очень по назначению - проверка на NullPointerException.
 */
public class MyListIteratorTest extends MyLinkedList{

    MyLinkedList<Integer> list;
    MyLinkedList.MyListIterator i;

    @Before
    public void setUp() throws Exception {
        list = new MyLinkedList<>();
        i = list.getIterator();
    }

    @Test
    public void nextLink() {
        list.insertFirst(456);
        list.insertFirst(123);
        Assert.assertEquals(123,i.getData());
        i.nextLink();
        Assert.assertEquals(456,i.getData());
        i.nextLink(); // пытаемся выйти за последний элемент
        Assert.assertEquals(456,i.getData());
    }

    @Test
    public void hasNext() {
        Assert.assertFalse(i.hasNext());
        list.insertFirst(123);
        list.insertFirst(456);
        // проверяем, сможет ли указатель подцепить первый элемент в ранее пустом списке
        Assert.assertTrue(i.hasNext());
        i.nextLink(); // перемещаемся на последний элемент
        Assert.assertFalse(i.hasNext());
    }

    @Test
    public void reset() {
        list.insertFirst(123);
        list.insertFirst(456);
        Assert.assertTrue(i.hasNext());
        i.nextLink();
        Assert.assertFalse(i.hasNext());
        i.reset();
        Assert.assertTrue(i.hasNext());
    }

    @Test
    public void getData() {
        list.insertFirst(123);
        Assert.assertEquals(123,i.getData());
        list.insertFirst(456);
        // указатель остается на элементе, даже если элемент уже не первый
        Assert.assertEquals(123,i.getData());
        i.deleteCurrent();
        i.deleteCurrent();
        i.getData(); // пытаемся получить пустые данные
    }

    // тут я уже отловил свою ошибку, когда изображал Цезаря (не салат) и удалил hasNext() из метода
    @Test
    public void getCurrent() {
        Assert.assertNull(i.getCurrent());
        list.insertFirst(1);
        Assert.assertNotNull(i.getCurrent());
    }

    @Test
    public void deleteCurrent() {
        list.insertFirst(123);
        list.insertFirst(456);
        list.insertFirst(789);
        i.nextLink();
        Assert.assertEquals(456,i.deleteCurrent());
        Assert.assertEquals(123,i.deleteCurrent());
        Assert.assertEquals(789,i.deleteCurrent());
        i.deleteCurrent(); // удаляем несуществующий элемент
    }

    @Test
    public void insertAfter() {
        list.insertFirst(123);
        i.insertAfter(456);
        Assert.assertEquals(456,i.getData());
        i.nextLink();
        Assert.assertNotEquals(123,i.getData());
    }

    @Test
    public void insertBefore() {
        list.insertFirst(123);
        i.insertBefore(456);
        Assert.assertEquals(456,i.getData());
        i.nextLink();
        Assert.assertEquals(123,i.getData());
    }
}