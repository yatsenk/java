package Task3;
/**
 * 3. Большая задача:
 *
 * a. Есть классы Task3.Fruit -> Task3.Apple, Task3.Orange;(больше фруктов не надо)
 * b. Класс Task3.Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
 * c. Для хранения фруктов внутри коробки можете использовать ArrayList;
 * d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного фрукта(вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
 * e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут в compare в качестве параметра, true - если их веса равны, false в противном случае(коробки с яблоками мы можем сравнивать с коробками с апельсинами);
 * f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку(помним про сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами), соответственно в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
 * g. Не забываем про метод добавления фрукта в коробку.
 */

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