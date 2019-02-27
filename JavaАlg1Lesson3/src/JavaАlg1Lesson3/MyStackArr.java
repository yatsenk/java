package JavaАlg1Lesson3;

import java.util.Arrays;

/**
 * Пишем свой ArrayList
 */
public class MyStackArr {
    private int top = -1;
    private int size; //текущий размер стека
    private int min_size; //минимальный размер стека
    private char[] chars;

    MyStackArr(){
        min_size = size = 8;
        chars = new char[size];
    }

    /**
     * Если нам сразу нужен большой стен
     * @param min_size вот такого размера
     */
    MyStackArr(int min_size){
        this.min_size = this.size = min_size;
        chars = new char[size];
    }

    /**
     * Сжимаем стек для освобождения памяти
     * если мы предполагаем, что не будем туда
     * ничего записывать
     */
    public void compact(){
        if (size > top && top > min_size){
            size = top + 1 ;
            chars = Arrays.copyOf(chars, size);
        } else {
            size = min_size;
            chars = Arrays.copyOf(chars, size);
        }
    }

    public int getSize() {
        return top+1;
    }

    public void print(){
        System.out.println(Arrays.toString(chars));
    }

    public void reset(){
        chars = new char[min_size];
        top = -1;
    }


    public void push(char ch){
        if (size - 1 == top) { //увеличиваем размер массива
        size *=2;
        chars = Arrays.copyOf(chars, size);
        }
        chars[++top] = ch;
    }

    public char pop(){
        if (top < 0) return 0;
        return chars[top--];
    }

    public char peer(){
        return chars[top];
    }
}
