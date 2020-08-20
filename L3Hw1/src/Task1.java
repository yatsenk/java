/**
 * 1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа);
 */

class Flip<T>{
    public void flip(T[] array, int pos1, int pos2){
        if (pos1 < array.length && pos2 < array.length && pos1 >= 0 && pos2 >= 0){
            T tmp = array[pos1];
            array[pos1] = array[pos2];
            array[pos2] = tmp;
        } else {
            throw new ArrayIndexOutOfBoundsException("Some index is wrong, array length: " + array.length + ", pos1: " + pos1 + ", pos2: " + pos2);
        }
    }
}

public class Task1 {
    public static void main(String[] args) {
        //Fill array
        String[] s = new String[100];
        for (int i = 0; i < s.length; i++) s[i] = "pos_" + i;

        //Flip array
        Flip<String> flipper = new Flip<>();
        int val1 = 50, val2 = 90;
        flipper.flip(s,val1,val2);

        //Check result
        System.out.println(s[val1] + " " + s[val2]);
    }
}