package JavaАlg1Lesson3;

/**
 * Никто не знает, зачем делать приоритетную очередь
 * на основе MyDeque, а не MyStack. Впрочем, добавлять
 * лишние ссылки на предыдущий элемент - интересно (и падуче).
 * Теперь еще больше C++ в нашем курсе Java!
 */
public class MyDequePrior extends MyDeque {

    public void add(String s){
        for (int i = 0; i < s.length(); i++) { add(s.charAt(i)); }
    }

    @Override
    public void add(char ch){
        if (pLast == null) { //если элементов нет
            pFirst = pLast = new Deque(null,null,ch);
        } else{
            Deque pCheck = pLast; // создаем временный указатель на место вставки
            while (pCheck.ch > ch && pCheck.first != null) { pCheck = pCheck.first; } // ставим указатель на элемент меньше

            /**
             *  addFirst, addLast - из суперкласса, заодно поправил в super логику управления ссылками
             *  addMiddle - метод текущего класса, для уникального случая вставки посередине
             *  В итоге итерации к четвертой и третьему утру остался простой и аккуратный код класса
             *  из двух методов, который я и учусь писать с первого раза.
             */
            if (pCheck.first == null && pCheck.ch > ch){        // если вставляем _перед_ первым элементом
                addFirst(ch);
            } else if (pCheck.last == null){                    // иначе если вставляем после последнего (или единственного) элемента
                addLast(ch);
            } else {                                            // иначе общий случай с двумя соседями
                addMiddle(pCheck, ch);
            }
        }
    }

    /**
     * Все просто:
     * 1. pInsertAfter.last - элемент, который мы только что вставили
     * 2. pInsertAfter.last.last - элемент после только что вставленного элемента
     * 3. pInsertAfter.last.last.first - очевидно, 2. должен ссылаться на элемент перед 2. - а это 1.
     */
    void addMiddle(Deque pInsertAfter,char ch){
        pInsertAfter.last = new Deque(pInsertAfter, pInsertAfter.last,ch);
        pInsertAfter.last.last.first = pInsertAfter.last;
        size++;
    }

    /**
     * Сейчас это приоритетная очередь по бОльшему элементу,
     * если читать с начала - то по меньшему.
     */
    public char remove(){
        return removeLast();
    }
}


/**
 * Предыдущая реализация логики вставки метода add, переусложнена (очевидно, раз 1 и 4 совпадают).
 * Не уверен, что работает быстрее, хотя в этом и был смысл такой реализации.
 * Оставлю тут в учебных целях "как бывает сложно аж до javadoc" и
 * как не надо повторно изобретать велосипед по ночам.
 */
//            /**
//             * Пояснения к вставке второго и далее элементов
//             * 1. Если вставляем в середине очереди после pCheck, где существуют соседи - все просто.
//             * 2. Иначе проверяем: если элемент самый первый и больше - надо вставлять перед pCheck.
//             * 3. Иначе следующий простой случай - а вдруг мы вставляем за последим элементом?
//             * 4. Теперь мы знаем, что вставляем сразу за первым элементом, у которого есть last,
//             *    обработанный в 3. Для наглядности оставил ненужный if.
//             */
//            if (pCheck.last != null && pCheck.first != null) {
//                // Все просто:
//                // 1. pCheck.last - элемент, который мы только что вставили
//                // 2. pCheck.last.last - элемент после только что вставленного элемента
//                // 3. pChech.last.last.first - очевидно, 2. должен ссылаться на элемент перед 2. - а это 1.
//                pCheck.last = new Deque(pCheck, pCheck.last,ch);
//                pCheck.last.last.first = pCheck.last;
//            } else if (pCheck.first == null && pCheck.ch > ch){
//                pFirst = new Deque(null, pCheck,ch);
//                pCheck.first = pFirst;
//            } else if (pCheck.last == null){
//                pLast = new Deque(pCheck,null,ch);
//                pCheck.last = pLast;
//            } else if (pCheck.first == null && pCheck.last != null) {
//                pCheck.last = new Deque(pCheck, pCheck.last, ch);
//                pCheck.last.last.first = pCheck.last;
//            }