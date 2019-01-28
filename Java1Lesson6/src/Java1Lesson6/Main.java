package Java1Lesson6;

public class Main {

    public static void main(String[] args) {
        Cat Cat1 = new Cat("Barsik");
        Dog Dog1 = new Dog("Sharik");
        Dog Dog2 = new Dog("Borshch", 500);

        Cat1.run(200);
        Cat1.jump(2);
        Cat1.swim(5);

        System.out.println();

        Dog1.setRun_distance(440);
        System.out.println(Dog1.run(441));
        Dog1.jump(0.499);
        Dog1.swim(10);

        Dog2.run(500);


    }
}
