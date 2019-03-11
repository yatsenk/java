package JavaАlg1Lesson5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Greedy {
    private Item[] in; // входной массив
    private int maxWeight;

    private Item[] out; // результирующий массив
    private int fullWeight,fullCost;

    public Greedy(Item[] arr, int maxweight) {
        this.in = arr;
        this.maxWeight = maxweight;
    }

    public int getFullWeight() {
        return fullWeight;
    }

    public void display(){
        fullWeight = fullCost = 0;

        if (out == null) {
            //отсортируем по весу для понятного вывода
            Comparator<Item> relativeComp = Comparator.comparingInt(Item::getWeight);
            Arrays.sort(in,relativeComp);

            System.out.println("Все предметы: ");
            for (Item item : in) {
                fullCost += item.getCost();
                fullWeight += item.getWeight();
                item.display();
            }
            System.out.println("Общий вес: " + fullWeight + " общая стоимость: " + fullCost);
        } else {
            System.out.println("Выбранные предметы: ");
            for (Item item : out) {
                fullCost += item.getCost();
                fullWeight += item.getWeight();
                item.display();
            }
            System.out.println("Общий вес: " + fullWeight + " общая стоимость: " + fullCost);
        }
    }

    public Item[] calc(){
        Arrays.sort(in); // сортируем по возрастанию относительной ценности компаратором из класса Item

        // считаем количество элементов, которые поместятся в рюкзак, и складываем их в ArrayList
        int sumWeight = 0;
        // все-таки с массивом при копировании производительность ожидаю выше
        ArrayList<Item> tmp = new ArrayList<>();

        for (Item i: in) {
            if (sumWeight + i.getWeight() <= maxWeight) {
                tmp.add(i);
                sumWeight += i.getWeight();
            } // цикл не прерываем, потому что сортировка не по весу
        }
/**
 * Указанные в комментариях проблемы с производительностью подробно разобраны в NP и TestListPerformance
 */

        // переписываем ArrayList в Array, не понимая принципа потоков, как ранее лямбд
        // возможно, тут проблемы с производительностью благодаря new
        out = tmp.toArray(new Item[0]);
//        out = tmp.stream().toArray(Item[]::new);
//        out = new Item[tmp.size()];
//        for (int i = 0; i < tmp.size(); i++) {
//            out[i] = tmp.get(i);
//        }
        System.out.println();
        return out;
    }
}
