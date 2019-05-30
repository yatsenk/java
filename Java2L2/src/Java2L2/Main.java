package Java2L2;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String [][] s = new String[4][4];
        // веселое заполнение массива, +10 для одинаковой разрядности при выводе
        for (int i = 0; i < 16 ; i++) s[i/4][i%4] = String.valueOf(i+10);

        //добавляем нечисловое значение
        //s[0][2] = "aa";

        //изменяем длину массива
        //s[2] = new String[]{"10","20","30","40","50"};

        for (int i = 0; i < 4; i++) System.out.println(Arrays.toString(s[i]));

        try {
            System.out.println("Сумма чисел массива: " + ArrayExceptions.array(s));
        } catch (MySizeArrayException | MyArrayDataException e) {
            System.out.println("Что-то пошло не так: ");
            System.out.println(e);
        } finally {
            //Тут мы могли бы выводить результат расчета, но в текущей архитектуре лучше вывести ничего.
            System.out.println();
        }
    }
}

class ArrayExceptions{
    // Нам не требуется throws, вероятно, из-за Runtime Exceptions. Ошибки все равно доходят до try вы вызывающем методе.
    // При Exceptions требуется принудительная обработка.
    // Будет ли хорошим подходом обозначать пробрасываемые методом ошибки, даже если они Runtime?
    static long array(String[][] s) {
        long sum = 0;

        for (int i = 0; i < 4; i++) {
            if (s.length != 4 || s[i].length != 4) throw new MySizeArrayException(4,4);
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                try {
                    sum+=Integer.parseInt(s[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i,j);

                }
            }
        }
        return sum;
    }
}

// наследуемся от Runtime Exceptions (выглядит логично)
class MySizeArrayException extends ArrayIndexOutOfBoundsException{
    public MySizeArrayException(int i, int j){
        super("Массив не соответствует требуемому размеру " + i + "x" + j);
    }
}

class MyArrayDataException extends NumberFormatException{
    public MyArrayDataException(int i, int j){
        super("В ячейке: " + i + " " + j + " число не обнаружено.");
    }
}