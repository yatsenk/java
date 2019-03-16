package JavaАlg1Lesson6;

import java.util.LinkedList;

/**
 * Реализовано обычное дерево, примечания в конце кода
 */

public class RBTree {

    public class Node { // узел хранения данных
        boolean isRed;
        int id; // упростим себе сравнение данных - по этому индексу
        String data;
        Node leftChild,rightChild,parent; // добавим родителя

        public Node(int id, String data, Node parent, Node leftChild, Node rightChild) {
            isRed = true; // при создании узел всегла красный
            this.id = id;
            this.data = data;
            this.parent = parent;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        public int getId() {
            return id;
        }
    }


    private Node root; // корень дерева
    private Node iter; // указатели для итераторов

//     сначала напишем обслуживающие пользователя функции

    /**
     * @param id принимает id элемента хранения
     * @return возвращает хранимые данные
     */
    public String find(int id){
        if (root == null) return null; // если дерева нет - можно не продолжать
        iter = root; // начинаем проход по дереву
        while (true){ // цикл поиска вместо рекурсий (вот рекурсии и не используются)
            if (iter.id == id) return iter.data; // нашли что требуется
            else if (id < iter.id && iter.leftChild != null)  iter = iter.leftChild; // уходим влево, проверяя такую возможность
            else if (id > iter.id && iter.rightChild != null) iter = iter.rightChild; // симметрично
            else return null; // ничего не нашли, потомки закончились
        }
    }

    /**
     * Находит Node по id
     * @return возвращает null, если не найдено
     */
    public Node findNode(int id){
        if (root == null) return null; // если дерева нет - можно не продолжать
        iter = root; // начинаем проход по дереву
        while (true){ // цикл поиска вместо рекурсий (вот рекурсии и не используются)
            if (iter.id == id) return iter; // нашли что требуется
            else if (id < iter.id && iter.leftChild != null)  iter = iter.leftChild; // уходим влево, проверяя такую возможность
            else if (id > iter.id && iter.rightChild != null) iter = iter.rightChild; // симметрично
            else return null; // ничего не нашли, потомки закончились
        }
    }

    /**
     * Ищет минимальный id в дереве
     * @return возвращает -1, если дерева не существует, или id
     */
    public int findMin(){
        if (root == null) return -1; // если дерева нет - можно не продолжать
        iter = root; // начинаем проход по дереву
        while (iter.leftChild != null) iter = iter.leftChild; // двигаемся все время влево
        return iter.id;
    }

    /**
     * Ищет максимальный id в дереве
     * @return возвращает -1, если дерева не существует, или id
     */
    public int findMax(){
        if (root == null) return -1; // если дерева нет - можно не продолжать
        iter = root; // начинаем проход по дереву
        while (iter.rightChild != null) iter = iter.rightChild; // двигаемся все время вправо
        return iter.id;
    }


    /**
     * Рекурсивная функция вывода id по возрастанию
     */
    public void printInOrder(){
        _printInOrder(root);
    }

    private void _printInOrder(Node rootNode){
        if (rootNode != null){
            _printInOrder(rootNode.leftChild);
            System.out.print(rootNode.id + " ");
            _printInOrder(rootNode.rightChild);
        }
    }

    /**
     * Формирование списка нод по возрастанию
     * @return список нод
     */
    public LinkedList toList(){
        LinkedList<Node> tmp = new LinkedList<>();
        _toList(root, tmp);
        return tmp;
    }

    private void _toList(Node root, LinkedList<Node> tmp){
        if (root != null) {
            _toList(root.leftChild, tmp);
            tmp.add(root);
            _toList(root.rightChild, tmp);
        }
    }

    /**
     * Проверяем дерево на сбалансированность. Критерий: высота листьев отличается не более чем на два узла (+-1)
     * @return true, если сбалансированно
     */
    private int maxdepth;
    public boolean checkBalance(){
        maxdepth = 0;
        return calcBalance(root,0);
    }

    private boolean calcBalance(Node root, int distance){
        if (root == null) return true;
        if (root.leftChild != null || root.rightChild != null){
            distance++;
            return calcBalance(root.leftChild,distance) && calcBalance(root.rightChild,distance);
        } else {
            if (maxdepth > 0) return (distance == maxdepth || distance == maxdepth - 1 || distance == maxdepth + 1); // пусть так вместо -1<n>1
            else { maxdepth = distance;
            return true; }
        }
    }


//     теперь напишем сложную вставку

    public void insert(int id, String data){
        // 1. В дереве есть дубликат - обновляем данные
        Node doublet = findNode(id);
        if (doublet != null) {
            doublet.data = data;
            return;
        }

        // 2. Корень был пустой
        if (root == null) { 
            root = new Node(id, data,null,null,null);
            root.isRed = false;
            return;
        }
        
        // 3. Вставляем узел до балансировки
        // ищем родителя вставки
        iter = root;
        while (true){
            if (id < iter.id && iter.leftChild != null) iter = iter.leftChild;
            else if (id > iter.id && iter.rightChild != null) iter = iter.rightChild;
            else break;
        }
        // вставляем
        if (id < iter.id) iter.leftChild = new Node(id,data,iter,null,null);
        else iter.rightChild = new Node(id,data,iter,null,null);
    }


    /**
     * Тут мог бы быть код четырех поворотов и остальная логика, но столько времени на чтение и понимание
     * 30 страниц Лафоре у меня до операции нет (хотя пишет классно). Лучше отложить до прочтения алгоритмов.
     * В StudyExample лежит рабочий код из интернетов, но без логики не стоит.
     */


}
