/**
 * 3. Большая задача:
 *
 * a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
 * b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
 * c. Для хранения фруктов внутри коробки можете использовать ArrayList;
 * d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного фрукта(вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
 * e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут в compare в качестве параметра, true - если их веса равны, false в противном случае(коробки с яблоками мы можем сравнивать с коробками с апельсинами);
 * f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку(помним про сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами), соответственно в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
 * g. Не забываем про метод добавления фрукта в коробку.
 */

import java.util.ArrayList;

//Let be one-file for learning case
interface Fruit {
    float getWeight();//Let's weight may be differ for everyone thing.
}

class Apple implements Fruit{
    private float weight = 1.0f;

    public Apple() {}
    public Apple(float weight) {
        this.weight = weight;
    }

    @Override
    public float getWeight() {
        return weight;
    }
}

class Orange implements Fruit {
    private float weight = 1.5f;

    public Orange() {}
    public Orange(float weight) {
        this.weight = weight;
    }

    @Override
    public float getWeight() {
        return weight;
    }
}

//Special box only for fruits. Maybe isothermic?
class Box<T extends Fruit> {
    //Keep something in a Box
    private ArrayList<T> aBoxKeeper = new ArrayList<>();

    //Put something in a Box with type T or it's childs.
    public void put(T something){
        aBoxKeeper.add(something);
    }
    public void add(T something){ //Someone like put, someone like add, Java core arrays like both.
        put(something);
    }

    //Calculate netto weight of the box by every item. Another way we can write Map for class-weight and multiply items quantity to weight.
    public float getWeight(){
        float sumWeight = 0;
        for (T something: aBoxKeeper) sumWeight += something.getWeight();
        return sumWeight;
    }

    //Compareing box with box for logistics reason.
    public <E extends Box<?>>boolean compare(E anotherBox){ //Box may be different, and anyway "boolean compare (Box<?> anotherBox)" not from this lesson.
        return this.getWeight() == anotherBox.getWeight();
    }

    //Moving fruit to friutbox by type.
    public <E extends Box<T>>void move(E anotherBox){ //Box may be differ size or some other child reasons.
        //It's ultralazy, but OK, no add extra addAll method or public aBoxKeeper
        for (T item : aBoxKeeper){
            anotherBox.add(item);
        }
        aBoxKeeper = new ArrayList<>(); //Is it a best practice?
    }
}

public class Task3 {
    public static void main(String[] args) {
        Box<Fruit> fruitBox = new Box<>(); //We can put here everything: apple and orange and any other fruit.
            fruitBox.add(new Apple());
            fruitBox.add(new Orange());
            System.out.println("Fruitbox weight: " + fruitBox.getWeight());
        System.out.println("Appleboxes:");
        Box<Apple> appleBox1 = new Box<>();
            appleBox1.add(new Apple());
            System.out.println(appleBox1.getWeight());
        Box<Apple> appleBox2 = new Box<>();
            appleBox2.add(new Apple(2));
            System.out.println(appleBox2.getWeight());
        System.out.println("Compareing a1<>a2: " + appleBox1.compare(appleBox2));
        System.out.println("Moving apples:");
        appleBox1.move(appleBox2);
            System.out.println(appleBox1.getWeight());
            System.out.println(appleBox2.getWeight());

        System.out.println("Orangebox:");
        Box<Orange> orangeBox = new Box<>();
            orangeBox.add(new Orange(1));
            orangeBox.add(new Orange(2));
            System.out.println(orangeBox.getWeight());
            System.out.println("Compareing or<>a2: " + orangeBox.compare(appleBox2));
        System.out.println("Moving oranges to apples:");
        //orangeBox.move(appleBox2); // Compilation error as expected.
    }
}