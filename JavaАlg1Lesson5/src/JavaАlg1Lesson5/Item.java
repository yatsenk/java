package JavaАlg1Lesson5;

import java.util.Objects;

/**
 * Объект хранения данных в массиве
 */
public class Item implements Comparable<Item>{
    private int weight;
    private int cost;
    private double relativeCost; // для жадного алгоритма

    public Item(int weight, int cost) {
        this.weight = weight;
        this.cost = cost;
        this.relativeCost = (double)cost / weight;
    }

    public int getWeight() {
        return weight;
    }

    public int getCost() {
        return cost;
    }

    public double getRelativeCost() {
        return relativeCost;
    }

    public void display(){
        System.out.println("Вес предмета: " + weight + ", стоимость предмета: " + cost + ".");
    }

    // сортировка для жадин: по относитальной стоимости desc, потом по весу asc
    // компаратор тоже попробуем, и на удивление производительность примерно одинакова
    @Override
    public int compareTo(Item o) {
        return (int)(( - this.relativeCost + o.relativeCost) * 65535 + (this.weight - o.weight));
    }

    // JavaDoc настоятельно рекомендует переопределить методы
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return ( - this.relativeCost + item.relativeCost) * 65535 + (this.weight - item.weight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, relativeCost);
    }
}
