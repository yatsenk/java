package JavaАlg1Lesson3;


/**
 * Я понял, почему не используется бинарный поиск:
 * 1. Не работает на списках
 * 2. Массив все равно двигать
 */
public class MyQueuePrior extends MyQueueArr {

    public MyQueuePrior() {
        super();
    }

    public MyQueuePrior(int minsize) {
        super(minsize);
    }

    public void addLinear(String s){ for (char c : s.toCharArray()) addLinear(c);}

    public void addLinear(char ch){ //линейный поиск - скучно и работает со второго раза
        if (items == size){ expand((int)(size*1.5)); }
        int i;
        for (i = items - 1 ; i >=0; i--) {
            if (queue[i] > ch) {
                queue[i+1] = queue[i];
            } else break;
        }
        queue[i+1] = ch;
        items++;
    }

    public void addBinary(String s){ for (char c : s.toCharArray()) addBinary(c);}

    public void addBinary(char ch){
        if (items == size){ expand((int)(size*1.5)); }
        int i = binaryFind(queue,ch,items); //ищем бинарным поиском
        System.arraycopy(queue,i,queue,i+1,items - i); //копируем часть после i на +1
        queue[i] = ch;
        items++;
    }

    public int binaryFind(char [] m, char value, int length){ //данные меньше массива хранения, нам нужен собственный поиск
        int low = 0;
        int high = length;
        int mid = 0;

        while (high >= low){
            mid = (low + high) / 2;
            if (m[mid] > value){
                high = mid - 1;
            } else if (m[mid] < value) {
                low = mid + 1;
            } else {
                return mid; //если найдено
            }
        }
        //подпираем костылем с контрольной проверкой на длину
        //иначе можно увлечься и выйти за диапазон данных
        while (m[mid] < value && m[mid]!= (char)0 && mid < length) mid++;
        return mid; //если не найдено
    }

}
