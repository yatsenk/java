package JavaАlg1Lesson5.tests;

import JavaАlg1Lesson5.Greedy;
import JavaАlg1Lesson5.Item;
import org.junit.Assert;
import java.util.Random;


/**
 * Сегодня тесты мне не удались: сама домашняя работа получилась объемной.
 */
public class GreedyTest {
    private final int ITEMS_QUANTITY = 5;
    private final int MAX_WEIGHT = 30;
    private final int BOUND_WEIGHT = 15;
    private final int BOUND_COST = 20;
    private Greedy g;

    Random rnd = new Random();
    Item[] array = new Item[ITEMS_QUANTITY];

    @org.junit.Before
    public void setUp() throws Exception {
        for (int i = 0; i < ITEMS_QUANTITY; i++) {
            array[i] = new Item(rnd.nextInt(BOUND_WEIGHT)+1,rnd.nextInt(BOUND_COST)+1);
        }
        g = new Greedy(array,MAX_WEIGHT);

    }

    @org.junit.Test
    public void calc() {
        g.display();
        g.calc();
        g.display();
        // начальный массив отсортирован правильно
        Assert.assertTrue(array[0].getRelativeCost() >= array[array.length-1].getRelativeCost());
        // вес не превышен
        Assert.assertTrue(g.getFullWeight() <= MAX_WEIGHT);
    }
}