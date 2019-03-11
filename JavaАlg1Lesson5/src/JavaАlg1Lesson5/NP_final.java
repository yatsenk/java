package JavaАlg1Lesson5;

import java.util.LinkedList;

/**
 * Эту причесанную версию уже можно кому-нибудь показывать
 */

public class NP_final {

    private Item[] in; // входной массив
    private int maxWeight;

    private Item[] out; // результирующий массив
    private int outCost;

    public NP_final(Item[] in, int maxWeight) {
        this.in = in;
        this.maxWeight = maxWeight;

        outCost = 0; // важно для цикла сравнения на 185 строке
    }

    public void display(){
        if (out == null) return;
        int fullWeight = 0;
        int fullCost = 0;
        System.out.println("Выбранные предметы: ");
        for (Item item : out) {
            fullCost += item.getCost();
            fullWeight += item.getWeight();
            item.display();
        }
        System.out.println("Общий вес: " + fullWeight + " общая стоимость: " + fullCost);
    }

    public void startQuickNP(){
        quickNP(in.length);
    }

    private void quickNP(int rotate_length){
        if (rotate_length == 1) return;
        for (int i = 0; i < rotate_length; i++) {
            quickNP(rotate_length - 1);
            if (rotate_length == 2) quickNPBackpack(); // делаем полезное
            // так как я не уверен, как работает стек - вынесем в отдельный метод
            quickNPRotate(rotate_length);
        }
    }

    private void quickNPRotate(int rotate_length){
        Item node = in[rotate_length - 1];
        System.arraycopy(in,0,in,1,rotate_length - 1); // совсем быстро работает относительно цикла java
        in[0] = node;
    }

    private void quickNPBackpack(){
        int sumWeight = 0;
        int sumCost = 0;
        LinkedList<Item> tmp = new LinkedList<>(); // работает быстрее ArrayList с учетом потоков и даже прямых обращений по номеру на списках размером до 10 000 элементов

        for (Item i:in) { // делаем лист предметов меньше или равных максимального веса
            sumWeight += i.getWeight();
            if (sumWeight <= maxWeight){
                tmp.add(i);
                sumCost += i.getCost();
            } else {
                break;
            }
        }

        if (sumCost > outCost) {
            out = tmp.toArray(new Item[0]);
            outCost = sumCost;
        }
    }
}
