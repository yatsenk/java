package JavaАlg1Lesson6.Test;

import JavaАlg1Lesson6.MyTree;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class MyTreeTest{

    private MyTree<Integer> tree;
    private final int BOUND = 1000;
    private final int FILL_ROUNDS = BOUND*10;

    @Before
    public void setUp(){
        tree = new MyTree<>();
        Random rnd = new Random();
        //заполняем случайными значениями максимально плотно
        for (int i = 0; i < FILL_ROUNDS; i++) {
            int r = rnd.nextInt(BOUND);
            tree.insert(r,r+100);
        }
    }

    @Test
    public void findData() {
        tree.insert(34,5);
        // если нашел
        Assert.assertEquals(5,(long)tree.findData(34));
        // если не нашел
        Assert.assertNull(tree.findData(12345678));
    }

    @Test
    public void insert() {
        // убедимся, что объекты с одинаковыми индексами перезаписываются,
        // а также что индексы в разумных пределах 2 100 000 000 не падают
        int i = 3456789;
        tree.insert(Integer.MAX_VALUE,Integer.MAX_VALUE);
        tree.insert(Integer.MAX_VALUE,i);
        int result = tree.findData(Integer.MAX_VALUE);
        Assert.assertEquals(i,result);
    }

    @Test
    public void delete() {
        final int BIG_VALUE = Integer.MAX_VALUE-1;

        // проверяем удаление листа
        tree.insert(BIG_VALUE,BIG_VALUE);
        tree.delete(BIG_VALUE);
        Assert.assertNull(tree.findData(BIG_VALUE));

        // проверяем удаление узла с одним потомком
        tree.insert(BIG_VALUE,BIG_VALUE);
        tree.insert(BIG_VALUE+1,BIG_VALUE+1);
        tree.delete(BIG_VALUE);
        Assert.assertNotNull(tree.findData(BIG_VALUE+1));
        tree.delete(BIG_VALUE+1);

        // удаляем последовательно все дерево
        int k = 0;
        for (int i = 0; i < BOUND; i++) {
            if (tree.delete(i)) { k++; }
        }
        Assert.assertTrue(tree.root == null && k > BOUND/2); // хотя бы половина элементов в дереве была
    }

    @Test
    public void display() {

    }

    @Test
    public void findMin() {
        tree.insert(0,100);
        int result = tree.findMin(tree.root).getData();
        Assert.assertEquals(100,result);
    }

    @Test
    public void findMax() {
        tree.insert(BOUND,199);
        int result = tree.findMax(tree.root).getData();
        Assert.assertEquals(199,result);
    }

    @Test
    public void fillDepth() {
        // root - нулевой узел
        Assert.assertEquals(0,tree.root.distance);

        // расчет глубины находится в разумных пределах
        int lg2 = (int)(Math.log10(BOUND)/Math.log10(2)); // логарифм по основанию 2
        Assert.assertTrue(lg2 * 3 > tree.fillDepth(tree.root,0));
    }

    @Test
    public void balance() {
        int td1 = tree.depth;
        int tn1 = tree.nodes;
        tree.balance();
        int td2 = tree.depth;
        int tn2 = tree.nodes;
        Assert.assertTrue(td1 > td2);
        Assert.assertEquals(tn1,tn2);
    }

    @Test
    public void checkBalance(){
        Assert.assertFalse(tree.checkBalance()); // рандомно созданное дерево должно быть несбалансированным
        tree.balance();
        Assert.assertTrue(tree.checkBalance()); // отбалансированне дерево - должно быть сбалансированным
    }
}