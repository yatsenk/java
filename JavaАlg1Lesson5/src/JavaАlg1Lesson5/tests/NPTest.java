package JavaАlg1Lesson5.tests;

import JavaАlg1Lesson5.Item;
import JavaАlg1Lesson5.NP;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;


public class NPTest{
    private final int ITEMS_QUANTITY = 6;
    private final int MAX_WEIGHT = 20;
    private final int BOUND_WEIGHT = 10;
    private final int BOUND_COST = 5;

    private NP np;

    Random rnd = new Random();
    private Item[] array = new Item[ITEMS_QUANTITY];

    @Before
    public void setUp() throws Exception {

        for (int i = 0; i < ITEMS_QUANTITY; i++) {
            array[i] = new Item(rnd.nextInt(BOUND_WEIGHT)+1,rnd.nextInt(BOUND_COST)+1);
        }
        np = new NP (array,MAX_WEIGHT);

    }

    @Test
    public void calcLotsOfMemoryAndRake() {
        np.calcLotsOfMemoryAndRake(); // не должен падать. Неудачный тест, да.
    }

    @Test
    public void factorial() {
        Assert.assertEquals(120, np.factorial(5));
    }

    @Test
    public void factorialCycle() {
        Assert.assertEquals(120, np.factorialCycle(5));
    }
}