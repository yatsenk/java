package JavaАlg1Lesson3;


import java.util.Arrays;

/**
 * Пишем свою очередь с увеличением глубины
 */
public class MyQueueArr {
    char [] queue;
    int minsize; //минимальный размер очереди
    int size = 16; //первичный размер очереди
    int read = 0, write = -1; //флаги чтения-записи
    int items = 0; //глубина очереди


    public MyQueueArr(){
        queue = new char[size];
        minsize = size;
    }
    public MyQueueArr(int minsize){
        this.minsize = size = minsize;
        queue = new char[size];
    }

    /**
     * Изменяем  массив очереди
     * так как рост массива в любом случае требует копирования, то скопируем все аккуратно
     * @param newsize новый размер массива, считать самостоятельно
     */
    public void expand(int newsize){
        if (newsize < items) return; //делаем ничего, если новый размер меньше количества элементов
        if (newsize < minsize) newsize = minsize; //размер не может быть меньше minsize
        char [] tmp = new char[newsize];

        int items_tmp = items; //так как мы удаляем элементы, нам надо знать их исходное количество

        for (int i = 0; i < items_tmp ; i++) {
            tmp[i] = remove();
        }
        queue = tmp; //возвращаем новый массив
        items = items_tmp; //возвращаем количество объектов после удаления
        read = 0; //переставляем указатели чтения-записи и размер
        write = items - 1;
        size = newsize;
    }

    /**
     * Сжимаем массив, если такая большая очередь нам больше не нужна
     */
    public void compact(){
        expand(items);
    }

    public boolean isEmpty(){
        return items == 0;
    }

    public void print(){ System.out.println(Arrays.toString(queue)); }

    public void add(char ch){
        if (items == size){ expand((int)(size*1.2)); }
        if (write == size - 1){ write = -1; }
        queue[++write] = ch;
        items++;
    }

    public char remove(){
        char tmp = queue[read++];
        if (read == size) { read = 0;}
        items--;
        return tmp;
    }

    public char peek(){
        return queue[read];
    }



//Прошлая версия с жонглированием указателями не позволяла увеличивать массив менее 2х и сжимать совсем
//    public void expand(int grows){
//        int oldsize = size;
//        if (grows >= 2) {size *=grows;}
//
//        queue = Arrays.copyOf(queue, size); //тут надо было System.arraycopy
//        if (write < read) {
//            for (int i = 0; i <= write; i++) {
//                queue[oldsize + i] = queue[i];
//            }
//            write  = oldsize + write;
//        } //иначе и так все хорошо, например, это был рост массива по запросу
//    }


}
