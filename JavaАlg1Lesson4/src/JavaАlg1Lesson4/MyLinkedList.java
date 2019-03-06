package JavaАlg1Lesson4;

import JavaАlg1Lesson4.Interfaces.ILinkedList;

//попробуем написать обобщенный класс
public class MyLinkedList<T> implements ILinkedList<T>{
    private Link first; //инициализируется null, поэтому используем конструктор по-умолчанию
    private int size;

    @Override
    public boolean isEmpty(){
        return size == 0; //или first = null
    }

    @Override
    public void insertFirst(T data){
        first = new Link(data,first);
        size++;
    }

    @Override
    public T delete(){
        T tmp;
        if (size > 0) {
            tmp = first.getData();
        } else {
            tmp = null;
        }

        if (size > 1){
            first = first.getNext();
        } else {
            first = null;
        }
        size--;
        return tmp;
    }

    @Override
    public String display(){
        StringBuilder sb = new StringBuilder();
        Link tmp = first;
        while (tmp != null){
            System.out.println(tmp.getData());
            sb.append(tmp.getData().toString());
            sb.append(" ");
            tmp = tmp.getNext();
        }
        return sb.toString();
    }

    // Почему эта штука в методичеке выглядит как бессмысленная?
    // Сделаем сравнение по части данных, но зато выведем только первый найденный.
    @Override
    public T find(T search){
        Link tmp = first;
        while (tmp != null){
            if (tmp.getData().toString().contains(search.toString())){
                return tmp.getData();
            }
            tmp = tmp.getNext();
        }
        return null;
    }

    @Override
    public void delete(T data){
        Link last = first;
        Link current = first;
        while (current != null){
            if (current.getData().equals(data)){
                if (last == first){
                    first = last.getNext();
                    last = last.getNext();
                } else {
                    last.setNext(current.getNext());
                }
            } else {
                last = current;
            }
            current = current.getNext();
        }
    }

    public MyListIterator getIterator(){
        return new MyListIterator(this);
    }

    // Зачем в методичеке сам Link тоже обобщенный, если можно обобщить параметр?
    // Возможно, переиспользование (так удобнее без чтения кода использовать еще раз),
    // но в этом коде оставим прямо так.
    class Link{
        private T data;
        private Link next;

        Link(T data, Link next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public Link getNext() {
            return next;
        }

        public void setNext(Link next) {
            this.next = next;
        }
    }


    /**
     * Благодаря JUnit и собственной невнимательности (к сожалению,
     * у меня много задач и мало времени) я нашел много промахов
     * в логике первоначального построения класса итератора, которую я
     * в основном взял из методички, потому что я представления не имел,
     * как должен проектироваться класс итератора (и сейчас, впрочем, не очень).
     */
    public class MyListIterator{
        private Link current;
        private Link previous;
        private MyLinkedList<T> list;

        MyListIterator(MyLinkedList<T> list){
            this.list = list;
            this.reset();
        }

        public void reset(){
            current = list.first;
            previous = null;
        }

        public boolean hasNext() {
            if (current == null && first != null) { reset(); } // если появился элемент в пустом списке - сбрасываем
            return (current != null && current.next != null);
        }


        public Link getCurrent(){
            hasNext();
            return current;
        }

        /**
         * При возникновении ситуации пустого списка
         * итератор самостоятельно не обрабатывал эту ситуацию.
         */
        public T getData(){
            hasNext(); // используем на случай появления элементов в пустом списке
            if (current != null){
                return current.data;
            } else {
                return null; // тут могло бы быть полезное неопасное исключение
            }
        }

        public void nextLink(){
            if (hasNext()){
                previous = current;
                current = current.next;
            }
        }

        /**
         * Пример промаха: удаляя предпоследний элемент, итератор
         * сбрасывался вместо указания на последний.
         * И доблестно падал при удалении из пустого списка.
         */
        public T deleteCurrent() {
            // если удалять нечего - падать не обязательно, а можно оставить
            // какое-нибудь исключение, но исключение я прямо сегодня не буду уметь
            if (current == null) return null;

            T data = current.data;

            if (previous == null){
                first = current.next;
                reset();
            } else if (!hasNext()){
                previous.next = null;
                reset();
            } else {
                current = previous.next = current.next;
            }

            return data;
        }

        public void insertAfter(T data){
            if (list.isEmpty()){
                first = new Link(data, first);
                current = first;
            } else {
                hasNext(); // иначе мы вызовем NPException в случае, если список был пустой и перестал
                current.next = new Link(data,current.next);
                current = current.next;
            }
        }

        public void insertBefore(T data){
            if (previous == null) { // мы в начале списка или никуда и не ходили
                first = new Link(data, first); //изящно обходим проблему потери элементов, присваивая first вместо current == null
                current = first;
            } else {
                previous.next = new Link(data, previous.next);
                current = previous.next;
            }
        }
    }
}
