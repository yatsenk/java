package JavaАlg1Lesson6.Test;

import JavaАlg1Lesson6.RBTree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Random;

public class RBTreeTest {
    Random rnd = new Random();
    RBTree tree;


    @Before
    public void setUp() throws Exception {
        tree = new RBTree();
        for (int i = 0; i < 10; i++) {
            tree.insert(i*100,String.valueOf(i));
        }

        for (int i = 10; i < 20; i++) {
            tree.insert(rnd.nextInt(99)+1,String.valueOf(i));
        }
        tree.insert(0,"0");
    }

    @Test
    public void insert() {

    }

    @Test
    public void find() {
        Assert.assertEquals(tree.find(700),"7");
        Assert.assertNull(tree.find(100000000));
    }

    @Test
    public void findNode() {
        Assert.assertNotNull(tree.findNode(100));
        Assert.assertNull(tree.findNode(100000000));
    }

    @Test
    public void findMin() {
        Assert.assertEquals(tree.findMin(),0);
    }

    @Test
    public void findMax() {
        Assert.assertEquals(tree.findMax(),900);
    }

    @Test
    public void printInOrder() {
        System.out.println("Должен печатать строку 0..900:");
        tree.printInOrder();
    }

    @Test
    public void toList() {
        LinkedList<RBTree.Node> l = tree.toList();
        Assert.assertEquals(l.getFirst().getId(),0);
        Assert.assertEquals(l.getLast().getId(),900);
    }

    @Test
    public void checkBalance() {
        Assert.assertTrue(tree.checkBalance());
    }


}