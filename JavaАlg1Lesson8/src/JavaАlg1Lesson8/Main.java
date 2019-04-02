package JavaАlg1Lesson8;

public class Main {

    public static void main(String[] args) {

        MyHashSimple my1 = new MyHashSimple(0);
        my1.put("Авокадо", 100);
        my1.put("Мармелад",200);
        my1.put("Арбуз",300);

        System.out.println("Цена арбуза: " + my1.get("Арбуз"));
        System.out.println("Цена авокадо: " + my1.get("Авокадо"));


    }
}
