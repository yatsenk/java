package JavaАlg1Lesson3;

/**
 * Делаем вид, что мы тут пишем на C++ с ручным управлением ссылками, только деструктора не хватает
 */
public class MyDeque{
    private int size = 0;
    private Deque pFirst;
    private Deque pLast;

    public int getSize() {
        return size;
    }

    public void add(char ch){
        addLast(ch);
    }

    public void addFirst(String s){ for (char c : s.toCharArray()) addFirst(c); } //перегрузим для строк

    public void addLast(String s){ for (char c : s.toCharArray()) addLast(c); } //перегрузим для строк

    public void addFirst(char ch){
        if (size == 0) { pFirst = pLast = new Deque(null,null,ch); }
        else {
            Deque tmpO = new Deque(null, pFirst, ch);
            pFirst.first = tmpO;
            pFirst = tmpO;
        }
        size++;
    }

    public void addLast(char ch){
        if (size == 0) { pFirst = pLast = new Deque(null,null,ch); } //без этого будет падать, потому что ссылки
        else {
            Deque tmpO = new Deque(pLast, null, ch);
            pLast.last = tmpO;
            pLast = tmpO;
        }
        size++;
    }

    public char removeFirst(){
        char tmpCh = pFirst.ch; //сначала читаем char, пока объект еще досутпен
        if (size == 1) { //если объекты закончились, то записывать ссылки в поля - уже некуда
            pFirst = pLast = null;
        } else {
            pFirst = pFirst.last;
            pFirst.first = null;
        }
        size--;
        return tmpCh;
    }

    public char removeLast(){
        char tmpCh = pLast.ch;
        if (size == 1) {
            pLast = pFirst = null;
        } else {
            pLast = pLast.first;
            pLast.last = null;
        }
        size--;
        return tmpCh;
    }

    public StringBuilder toArray(){
        StringBuilder out = new StringBuilder();
        Deque pointer = pFirst;
        for (int i = 0; i < size; i++) {
            out.append(pointer.ch);
            pointer = pointer.last;
        }
        return out;
    }

    /**
     * реализует двунаправленный список:
     * first по убыванию к 0 элементу
     * last по возрастанию
     */
    class Deque{
        Deque first;
        Deque last;
        char ch;

        public Deque(Deque first, Deque last, char ch) {
            this.first = first;
            this.last = last;
            this.ch = ch;
        }
    }

}
