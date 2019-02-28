package JavaАlg1Lesson3;

/**
 * Никто не знает, зачем делать приоритетную очередь
 * на основе MyDeque, а не MyStack. Впрочем, добавлять
 * лишние ссылки на предыдущий элемент - интересно (и падуче).
 * Теперь больше C++ в нашем курсе Java!
 */
public class MyDequePrior extends MyDeque {

    public void add(String s){
        for (int i = 0; i < s.length(); i++) { add(s.charAt(i)); }
    }

    @Override
    public void add(char ch){
        if (size == 0) { //если это будет самый первый элемент
            pFirst = pLast = new Deque(null,null,ch);
        } else{
            Deque pCheck = pLast; //создаем временный указатель
            while (pCheck.ch > ch && pCheck.first != null) { pCheck = pCheck.first; } //ищем элемент меньше

            /**
             * Пояснения к вставке второго и далее элементов
             * 1. Если вставляем в середине очереди после pCheck, где существуют соседи - все просто.
             * 2. Иначе проверяем: если элемент самый первый и больше - надо вставлять перед pCheck.
             * 3. Иначе следующий простой случай - а вдруг мы вставляем за последим элементом?
             * 4. Теперь мы знаем, что вставляем сразу за первым элементом, у которого есть last,
             *    обработанный в 3. Для наглядности оставил ненужный if.
             */
            if (pCheck.last != null && pCheck.first != null) {
                // Все просто:
                // 1. pCheck.last - элемент, который мы только что вставили
                // 2. pCheck.last.last - элемент после только что вставленного элемента
                // 3. pChech.last.last.first - очевидно, 2. должен ссылаться на элемент перед 2. - а это 1.
                pCheck.last = new Deque(pCheck, pCheck.last,ch);
                pCheck.last.last.first = pCheck.last;
            } else if (pCheck.first == null && pCheck.ch > ch){
                pFirst = new Deque(null, pCheck,ch);
                pCheck.first = pFirst;
            } else if (pCheck.last == null){
                pLast = new Deque(pCheck,null,ch);
                pCheck.last = pLast;
            } else if (pCheck.first == null && pCheck.last != null) {
                pCheck.last = new Deque(pCheck, pCheck.last, ch);
                pCheck.last.last.first = pCheck.last;
            }
        }
        size++;
    }

    // сейчас это приоритетная очередь по бОльшему элементу
    // а если читать с начала - то по меньшему
    // не зря же у нас тут Deque
    public char remove(){
        return removeLast();
    }

    // убираем все методы добавления, потому что
    // они делают приоритет бессмысленным
    @Deprecated
    public void addFirst(String s){}

    @Deprecated
    public void addFirst(char ch){}

    @Deprecated
    public void addLast(String s){}

    @Deprecated
    public void addLast(char ch){}
}
