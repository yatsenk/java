package JavaАlg1Lesson2;

public class ArraySort {

    private int step; // количество обменов

    public int getStep(){
        return step+1;
    }

    public void exchange(int[] m, int a, int b){
        int length = m.length;
        int tmp;
        if (length < a || length < b) return;
        tmp = m[b];
        m[b] = m[a];
        m[a] = tmp;
        step++;
    }

    public void bubbleSort1(int[] m){
        long start = System.currentTimeMillis();
        step = 0;

        int length = m.length;
        boolean doExchange = true; // ускоряем обработку на частично сортированных массивах, устраняя последующие проходы

        for (int i = 0; i < length; i++) {
            if (!doExchange) break;
            doExchange = false;
            for (int j = 0; j < length - 1; j++) {
                if (m[j] > m[j+1]){
                    exchange(m,j,j+1);
                    doExchange = true;
                }
            }
        }
        long finish = System.currentTimeMillis();
        System.out.println("  1. BubbleSort1: " + (finish - start) + "ms");
    }

    public void bubbleSort2(int[] m){
        long start = System.currentTimeMillis();
        step = 0;

        int length = m.length;

        for (int i = 0; i < length; i++) {
            for (int j = length-2; j >= i ; j--) { //теперь мы не доходим до отсортированной части
                if (m[j] > m[j+1]){ // я убрал проверку сортировки, поэтому на сортированных массивах будет медленным
                    exchange(m,j,j+1);
                }
            }
        }
        long finish = System.currentTimeMillis();
        System.out.println("  2. BubbleSort2: " + (finish - start) + "ms");
    }

    public void selectSort(int[] m){
        long start = System.currentTimeMillis();
        step = 0;

        int length = m.length;
        int marker;

        for (int i = 0; i < length; i++) {
            marker = i;
            for (int j = i+1; j < length; j++) { //логично начинать со следующего элемента, но ускорение работы 1/n
                if (m[j] < m[marker]) marker = j;
            }
            //if (i!= marker) // это сравнение увеличивает время выполнения, для случайных массивов
                              // проще поменять одинаковый элемент на сам себя
                exchange(m,i,marker);
        }
        long finish = System.currentTimeMillis();
        System.out.println("  3. SelectSort: " + (finish - start) + "ms");
    }

    public void insertSort1(int[] m){
        long start = System.currentTimeMillis();
        step = 0;

        int length = m.length;
        int i=1;

        while (i<length){ // очень тормознутая сортировка получилась: ходить обратно так далеко
            if(i > 0 && m[i-1] > m[i]) {
                exchange(m,i,i-1);
                i--;
            } else i++;
        }

        long finish = System.currentTimeMillis();
        System.out.println("  4. InsertSort1_simple: " + (finish - start) + "ms");
    }

    public void insertSort2(int[] m){
        long start = System.currentTimeMillis();
        step = 0;

        int length = m.length;

        for (int i = 0; i < length; i++) { // теперь запоминаем, где мы там остановились
            int k=i;
            while (k > 0 && m[k-1] > m[k]){
                    exchange(m,k,k-1);
                    k--;
            }
        }
        long finish = System.currentTimeMillis();
        System.out.println("  5. InsertSort2_not_so_simple: " + (finish - start) + "ms");
    }

    public void insertSort3(int[] m){
        long start = System.currentTimeMillis();
        step = 0;

        int length = m.length;

        for (int i = 0; i < length; i++) {
            int k=i;
            int m_tmp = m[k]; // теперь используем временную переменную, чтобы не обращаться к массиву каждый раз - и иногда это тормозит
            while (k > 0 && m[k-1] > m_tmp){
                exchange(m,k,k-1);
                k--;
            }
        }
        long finish = System.currentTimeMillis();
        System.out.println("  6. InsertSort3_less_simple: " + (finish - start) + "ms");
    }

    public void insertSort4(int[] m){ // алгоритм как в 3, добавили оптимизацию по ресурсам
        long start = System.currentTimeMillis();
        step = 0;

        int length = m.length;
        int i,k,m_tmp,tmp; //тут больше оптимизации

        for (i = 0; i < length; i++) {
            k=i;
            m_tmp = m[k];
            while (k > 0 && m[k-1] > m_tmp){
                tmp = m[k]; //и тут больше оптимизации
                m[k] = m[k-1];
                m[k-1] = tmp;
                k--;
                step++;
            }
        }
        long finish = System.currentTimeMillis();
        System.out.println("  7. InsertSort4_optimized: " + (finish - start) + "ms");
    }

    public void combSort1(int[] m) { //сортировка расческой неправильная, нужно больше проходов
                                     //просто магического числа недостаточно
                                     //поэтому пользоваться ей мы не будем
        long start = System.currentTimeMillis();
        step = 0;

        int length = m.length;
        int interval = length;
        float magic_less = 1.24733f;
        int tmp;

        while (interval > 1){
            interval /= magic_less;
            for (int i = 0; i < (length - interval); i++) {
                if (m[i] > m[i+interval]){
                    tmp = m[i];
                    m[i] = m[i+interval];
                    m[i+interval] = tmp;
                    step++;
                }
            }
        }

        long finish = System.currentTimeMillis();
        System.out.println("  10. CombSort1: " + (finish - start) + "ms");
    }

    public void combSort2(int[] m) { //сортировка расческой правильная - пока не отсортирует
        long start = System.currentTimeMillis();
        step = 0;

        int length = m.length;
        int interval = length;
        float magic_less = 1.24733f;
        int tmp;
        boolean end = false;

        while (!end || interval > 1){
            interval /= magic_less;
            if (interval < 1) interval = 1;
            end = true;
            for (int i = 0; i < (length - interval); i++) {
                if (m[i] > m[i+interval]){
                    tmp = m[i];
                    m[i] = m[i+interval];
                    m[i+interval] = tmp;
                    end = false;
                    step++;
                }
            }

        }

        long finish = System.currentTimeMillis();
        System.out.println("  11. CombSort2: " + (finish - start) + "ms");
    }


    public void qsort(int[] m){
        long start = System.currentTimeMillis();
        step = 0;

        qsort1(m, 0, m.length-1);

        long finish = System.currentTimeMillis();
        System.out.println("  12. qSort: " + (finish - start) + "ms");
    }
    private void qsort1(int[] m, int low, int high){
        if (low >= high) return; //глубина рекурсии закончилась

        int base = m[low + (high-low)/2]; //тут можно оптимизацию
        int l = low, h = high;
        int tmp;

        while (l <= h){
            while (m[l] < base) l++; //сдвигаем указатели поближе
            while (m[h] > base) h--;
            if (l < h) { // если массив можно поделить по опорному элементу
                tmp = m[l];
                m[l] = m[h];
                m[h] = tmp;
                l++;
                h--;
                step++;
            } else if (l == h){
                l++;
                h--;
            }
        }

        //магия рекурсии
        if (low<h) qsort1(m, low, h);
        if (high>l) qsort1(m, l, high);
    }

    public void countingSort(int[] m){ //положительные целые числа > 0 размером не более length+const
        long start = System.currentTimeMillis();
        step = 0;

        int length = m.length;
        int[] k = new int [length + 10]; //временный массив
        for (int i: m) {
            k[i]++; //увеличиваем значения полей в списке частоты встречаемости
        }

        int count = 0;
        for (int i = 0; i < k.length; i++) { //печатаем из списка частотвы
            for (int j = k[i]; j > 0; j--) {
                m[count] = i;
                count++;
            }
        }

        long finish = System.currentTimeMillis();
        System.out.println("  13. CountingSort: " + (finish - start) + "ms");
    }
}
