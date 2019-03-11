package JavaАlg1Lesson5;

import java.util.ArrayList;
import java.util.LinkedList;

public class NP {
    private Item[] in; // входной массив
    private int maxWeight;

    private Item[] out; // результирующий массив
    private int fullWeight,fullCost, outCost;

    private int strFillArray; // строка, с которой работает fillArray


    public NP(Item[] in, int maxWeight) {
        this.in = in;
        this.maxWeight = maxWeight;

        outCost = 0; // важно для цикла сравнения на 185 строке
    }


    /**
     * Адекватные методы начинаются со 130 строки и 7 утра
     */
    // бессмысленный и беспощадный вычислительный метод вместо пойти спать
    public Item[] calcLotsOfMemoryAndRake(){
        int size1 = in.length;
        int size2 = (int)factorial(size1);
        // хранить много ссылок на объекты должно быть не очень накладно
        // и надо оправдать вычисления факториалов в учебных целях(с)
        // размер массива на 1 больше, потому что строка-образец
        Item[][] tmp = new Item[size2+1][size1];

        System.arraycopy(in,0,tmp[0],0,in.length);
        strFillArray = 0;
        fillArray(tmp,in.length);
        getMaxBackpack(tmp);
        return out;
    }

    // раз у нас рекурсия - считаем факториал рекурсивно
    // работает в два-пять раз медленнее, потом long заканчивается, не проверял
    // private не ставим, иначе метод тестировать отдельно не получится
    public long factorial(int n){
        if (n < 1) return 0; // такие считать не будем
        if (n == 1) return 1;
        return factorial(n - 1)*n;
    }

    // но практичнее выглядит так
    public long factorialCycle(int n){
        if (n < 1) return 0; // такие считать не будем
        long result = 1; // можно что-нибудь предвычислить, но так нагляднее
        for (int i = 1; i <=n ; i++) {
            result *= i;
        }
        return result;
    }

    // этот код я писал до утра
    private void fillArray(Item [][] tmp, int rotate_length){
        if (rotate_length == 1) return;
        for (int i = 0; i < rotate_length; i++) {
            fillArray(tmp,rotate_length-1);
            // копирование предыдущей строки
            if (rotate_length == 2){
                System.arraycopy(tmp[strFillArray],0,tmp[strFillArray+1],0,tmp[strFillArray].length);
                strFillArray++;
            }
            // перестановки
            Item node = tmp[strFillArray][0];
            for (int j = 0; j < rotate_length - 1; j++) {
                tmp[strFillArray][j] = tmp[strFillArray][j+1];
            }
            tmp[strFillArray][rotate_length - 1] = node;
        }
    }

    public void display(){
        if (out == null) return;
        fullWeight = fullCost = 0;
        System.out.println("Выбранные предметы: ");
        for (Item item : out) {
            fullCost += item.getCost();
            fullWeight += item.getWeight();
            item.display();
        }
        System.out.println("Общий вес: " + fullWeight + " общая стоимость: " + fullCost);
    }

    private void display(Item[][] arr){
        for (Item[] i : arr) {
            for (Item j:i) {
                System.out.print(j.getCost() + " ");
            }
            System.out.println();
        }
    }

    //и тут тоже сделаем рекурсию на тему урока
    private void getMaxBackpack(Item[][] arr){
        if (strFillArray == 0) return;
        int sumWeight = 0;
        ArrayList<Item> tmpL = new ArrayList<>();
        for (Item i:arr[strFillArray]) {
            if (sumWeight + i.getWeight() <= maxWeight){
                sumWeight += i.getWeight();
                tmpL.add(i);
            }
        }
        Item[] tmpA = tmpL.toArray(new Item[0]);
        if (out == null) out = tmpA;
        if (calcCost(tmpA) > calcCost(out)) out = tmpA;
        strFillArray--;
        getMaxBackpack(arr);
    }

    private int calcCost(Item[] arr) {
        int cost = 0;
        for (Item i : arr) {
            cost += i.getCost();
        }
        return cost;
    }

    /**
     * Адекватные методы начинаются здесь, все выше - в обучающих целях
     */


    public void startQuickNP(){
        quickNP(in.length);
    }

    private void quickNP(int rotate_length){
        if (rotate_length == 1) return;
        for (int i = 0; i < rotate_length; i++) {
            quickNP(rotate_length - 1);
            // делаем полезное
            if (rotate_length == 2) quickNPBackpack();
            // так как я не уверен, как работает стек - вынесем в отдельный метод
            quickNPRotate(rotate_length);
        }
    }
    // в этом методе была ошибка: почему я решил в 7 утра, что в arraycopy должно быть rotate_length - 2 ?
    // в строке 74, главное, все правильно
    public void quickNPRotate(int rotate_length){
        Item node = in[rotate_length - 1];
        System.arraycopy(in,0,in,1,rotate_length - 1); // совсем быстро работает относительно цикла java
        in[0] = node;
    }

    // примерно тут я заподозрил, что стоит проектировать лучше
    private void quickNPBackpack(){
        int sumWeight = 0;
        int sumCost = 0;
        //int tmpACost; // использовался для расчета стоимости массива, нет массива - нет расчета
        LinkedList<Item> tmp = new LinkedList<>(); // работает быстрее ArrayList с учетом потоков и даже прямых обращений по номеру
                                                    // на списках размером до 10 000 элементов

        for (Item i:in) { // делаем лист предметов меньше или равных максимального веса, теперь оптимизированно (см. Greedy)
            sumWeight += i.getWeight();
            if (sumWeight <= maxWeight){
                tmp.add(i);
                sumCost += i.getCost();
            } else {
                break;
            }
        }
        /**
         * Нижеследующий код убрал, чтобы зря не копировать массив и не считать его, добавил sumCost += i.getCost();
         */
//        Item[] tmpA = tmpL.toArray(new Item[0]); // самый быстрый вариант преобразования списка в массив
//        tmpACost = calcCost(tmpA); // считаем, не надо ли заменить выходной массив

//        Item[] tmpA = tmpL.stream().toArray(Item[]::new); // совсем медленно, вероятно лишнее преобразование данных

//        Item[] tmpA = new Item[tmpL.size()]; // все еще хороший довольно производительный алгоритм
//        for (int i = 0; i < tmpL.size(); i++) { tmpA[i] = tmpL.get(i); }

        /**
         * Вынос этого сравнения на null в конструктор не дает выигрыша в производительности,
         * зато оно тут вообще не надо, если outCost == 0. Убрали еще один костыль.
         */
//        if (out == null){
//            out = tmpL.toArray(new Item[0]);
//            outCost = sumCost;
//        } else
        if (sumCost > outCost) {
            out = tmp.toArray(new Item[0]);
            outCost = sumCost;
        }
    }
}
