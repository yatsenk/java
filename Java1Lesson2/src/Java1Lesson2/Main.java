package Java1Lesson2;

import java.sql.SQLOutput;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        arr1();
        arr2();
        arr3();
        arr4();
        arr5();

        byte[] array6 = {126,2,2,1,1,127,1,127,2,2,2,125};
        arr6(array6);

        byte[] array7 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
        byte n = 5;
        arr7_1(array7,n);
        arr7_2(array7,n);
        arr7_3(array7,n);
    }

    //1. Задать целочисленный массив, состоящий из элементов 0 и 1.
    private static void arr1(){
        byte[] arr = {1,1,0,0,1,0,1,1,0,0};
        for (byte i=0; i < arr.length; i++){
            switch (arr[i]){
                case 0:
                    arr[i] = 1;
                    break;
                case 1:
                    arr[i] = 0;
                    break;
                default:
                    System.out.println("Это был не тот массив.");
            }
        }
        System.out.println("\nArray 1:" + Arrays.toString(arr));
    }

    //2. Задать пустой целочисленный массив размером 8.
    private static void arr2(){
        byte[] arr = new byte[8];
        for (byte i=0, j=0; i<8; i++, j+=3){
            arr[i] = j;
        }
        System.out.println("\nArray 2:" + Arrays.toString(arr));
    }

    //3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ]
    private static void arr3(){
        byte[] arr = {1,5,3,2,11,4,5,2,4,8,9,1};
        for (byte i = 0; i < arr.length; i++){
            if (arr[i] < 6) arr[i]*=2;
        }
        System.out.println("\nArray 3:" + Arrays.toString(arr));
    }

    //4. Создать квадратный двумерный целочисленный массив
    private static void arr4(){
        byte length = 10; //размер квадратного массива
        byte[][] arr = new byte[length][length];
        for (byte i = 0; i<length;i++){
            arr[i][i] = 1;
        }
        System.out.println("\nArray 4:");
        for (byte i = 0; i<length;i++){
            System.out.println(Arrays.toString(arr[i]));

        }
    }

    //5. Задать одномерный массив и найти в нем
    private static void arr5(){
        byte[] arr = {1,3,5,-126,0,17,13,99,127,2,63,126,-122,99};
        byte max = arr[0];
        byte min = arr[0];
        for (byte i : arr){
            if (max < i) max = i;
            if (min > i) min = i;
        }
        System.out.println("\nArray 5: Max = " + max + " Min = " + min);

        Arrays.sort(arr);
        System.out.println("Array 5, Sorted Min = " + arr[0] + " , Sorted Max = " + arr[arr.length-1]);

    }

    //6. Написать метод, в который передается не пустой
    private static boolean arr6(byte arr[]){
        int left = 0;
        int right = arr.length - 1;
        int sumleft = arr[left];
        int sumright = arr[right];

        while ((left + 1) != right){
            if (sumleft < sumright){
                left++;
                sumleft+=arr[left];
            } else {
                right--;
                sumright+=arr[right];
            }
        }
        System.out.println("\nArray 6: " + (sumleft == sumright));
        return sumleft == sumright;
    }

    //7. Написать метод, которому на вход подаётся одномерный массив и число n

    //смещает на n (и ходит по кругу, потому что круглая Земля)
    //на базисе наибольших общий делителей
    //сложностью между O(arr.length) и O(arr.length*n)
    //в неуемном стремлении к оптимизации всего и вся
    private static boolean arr7_1(byte arr[], byte n){

        byte mod = 0; //считаем общее смещение массива вправо
        n = (byte)(n % arr.length);
        if (n > 0) mod = n;
        if (n < 0) mod = (byte)(arr.length+n);
        if (mod == 0) return true; // смещений не требуется, Добби свободен

        while (mod!=0) {
            byte move = gcd(arr.length, mod); // наибольший общий делитель, на который сдвигаем массив за итерацию

            // проходим последовательным смещениями move длины весь массив move раз
            for (byte i = 1; i <= move; i++) {
                byte tmp = arr[arr.length - i]; // храним значение затираемого элемента массива
                byte j;
                for (j = (byte) (arr.length - i); j >= move; j -= move) {
                    arr[j] = arr[j - move];
                }
                arr[j] = tmp; // и возвращаем его начальному элементу
            }
            mod -=move; // вычисляем оставшееся смещение
        }

        System.out.println("\nArray 7_1: " + Arrays.toString(arr));

        return true;
    }

    //алгоритм поиска наибольшего общего делителя (НОД) для метода arr7_1
    private static byte gcd(int a, int b) {
        while (b !=0) {
            int tmp = a%b;
            a = b;
            b = tmp;
        }
        return (byte)a;
    }

    //смещает на n (и ходит по кругу, потому что круглая Земля)
    //на одну позицию за итерацию O(arr.length*n)
    private static boolean arr7_2(byte arr[], byte n){
        byte mod = 0; //считаем смещение массива вправо
        n = (byte)(n % arr.length);
        if (n > 0) mod = n;
        if (n < 0) mod = (byte)(arr.length+n);
        if (mod == 0) return true; // смещений не требуется, Добби свободен

        for (byte i = 0; i < mod; i++){
            byte tmp = arr[arr.length-1]; // храним значение затираемого элемента массива
            for (byte j=(byte)(arr.length-1);j>0;j--) arr[j] = arr[j-1];
            arr[0] = tmp; // и возвращаем его последнему элементу массива
        }

        System.out.println("\nArray 7_2: " + Arrays.toString(arr));

        return true;
    }


    //смещает на n и зануляет остаток
    private static boolean arr7_3(byte[] arr, byte n){

        if (Math.abs(n) >= arr.length) {
            System.out.println("Ошибка: смещение длиннее массива");
            return false;
        }
        if (n==0) {
            System.out.println("Ошибка: нулевое смещение");
            return false;
        }

        if (n>0){ //выбираем, двигаем мы массив вправо или влево
            for (byte i = (byte)(arr.length - 1); i-n>=0;i--){
                arr[i] = arr[i-n];
            }
            while (n>0){ //обнуляем неиспользуемую часть массива
                n--;
                arr[n] = 0;
            }
        } else {
            System.arraycopy(arr,-n,arr,0,arr.length+n); //копируем системным методом для разнообразия
            /*for (byte i = (byte)-n; i < arr.length; i++){
                arr[i+n] = arr[i];
            }*/
            while (n<0){ //обнуляем неиспользуемую часть массива
                n++;
                arr[arr.length + n - 1] = 0;
            }
        }
        System.out.println("\nArray 7_3: " + Arrays.toString(arr));

        return true;
    }
}