/**
 * 2. Написать метод, который преобразует массив в ArrayList;
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class ArrayToList<T>{
    public ArrayList<T> convert(T[] array){
        ArrayList<T> list = new ArrayList<>();
        Collections.addAll(list, array);
        return list;
    }
}

public class Task2 {
    public static void main(String[] args) {
        //Fill array
        String[] s = new String[10];
        Arrays.fill(s,"data");
        //Converting and checking
        ArrayToList<String> converter = new ArrayToList<>();
        System.out.println(converter.convert(s).toString());
    }
}
