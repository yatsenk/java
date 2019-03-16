package JavaАlg1Lesson6;


/**
 * Очень большой класс с кучей методов. Сдается мне, это надо проектировать через интерфейсы
 * и давать нормальные названия, чтобы не запутаться. Больше геттеров и приватности!
 */
public class MyTree<T> {
    public Node<T> root; // храним корень
    public int depth = 0; // глубина дерева
    public int nodes = 0; // количество узлов
    private int leftNodes = 0, rightNodes = 0; // ноды в половинах дерева для расчета балансировки

    public class Node<T>{ // добавим Generics в подкласс, чтобы посмотреть на изменение в Warnings относительно ДЗ4.
        public int distance; // дистанция от корня, чтобы что-нибудь считать и моделировать рандомизированные деревья поиска
        final int id; // id который мы можем копировать из T data, (а лучше делать хэши),
                // что должно немного увеличить расход памяти в пользу ускорения обращения к элементам
                // (я, полагаю, только что изобрел индексы SQL :)
        T data; // полезная нагрузка ноды
        Node<T> leftChild; // указатели на узлы
        Node<T> rightChild;
        //Node<T> parent; // я не видел обратных ссылок к родителю - возможно, пригодятся только при удалении

        Node(int id, T data) {
            this.id = id;
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }


    // выводим полезную нагрузку
    public T findData(int id){
        Node<T> current = root;
        while (current.id != id){
            if (current.id > id){
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
            if (current == null) return null;
        }
        return current.data;
    }

    /**
     * @return Node[2], где 0 - найденный узел, 1 - родитель найденного узла.
     */
    // ищем ноду по индексы
    public Node<T>[] findNode(int id){
        if (root == null) { return null; }// дерево пустое

        Node<T>[] n = new Node[2];
        n[0] = root;
        while (n[0].id != id){
            n[1] = n[0];
            if (n[0].id > id){
                n[0] = n[0].leftChild;
            } else {
                n[0] = n[0].rightChild;
            }
            if (n[0] == null) return null;
        }
        return n;
    }

    // вставляем элемент, записывая значение дистанции от корня
    // самый несбалансированный метод вставки
    public void insert(int id, T data){
        Node<T> current = root;
        Node<T> parent = current;
        int distance = 0; // расстояние от корня
        if (root == null){ // проверяем на пустое дерево
            root = new Node<>(id,data);
            nodes++;
            return;
        }
        while (current != null){ // ищем куда вставлять
            distance++;
            parent = current;
            if (current.id < id){
                current = current.rightChild;
            } else if (current.id > id) {
                current = current.leftChild;
            } else { // обновляем данные, если индексы совпадают
                current.data = data;
                return;
            }
        }
        if (parent.id < id){ // вставляем
            parent.rightChild = new Node<>(id,data);
            parent.rightChild.distance = distance;
            if (depth < distance) depth = distance;
        } else {
            parent.leftChild = new Node<>(id,data);
            parent.leftChild.distance = distance;
            if (depth < distance) depth = distance;
        }
        nodes++;
    }

    public boolean delete(int id){
        Node<T>[] n = findNode(id); // работать с массивом оказалось неудобно,
                                    // проще присвоить значения current parent
        if (n == null || n[0] == null) { return false; }

        if (n[0].leftChild == n[0].rightChild){ // если потомки равны друг другу, то они - null
            if (n[0] == root){ root = null; }
                else if (n[0] == n[1].leftChild) { n[1].leftChild = null; }
                    else { n[1].rightChild = null; }
            nodes--;
            return true;
        }

        if (n[0].leftChild == null ^ n[0].rightChild == null){ // только один из двух потомков null
            if (n[0] == root){ // если это был корень
                if (n[0].leftChild == null){ root = n[0].rightChild; }
                    else { root = n[0].leftChild; }
            } else if (n[0] == n[1].leftChild){ // если удаляем левый узел
                if (n[0].leftChild == null){ n[1].leftChild = n[0].rightChild; }
                    else { n[1].leftChild = n[0].leftChild; }
            } else { // иначе удаляем правый узел
                if (n[0].leftChild == null){ n[1].rightChild = n[0].rightChild; }
                    else { n[1].rightChild = n[0].leftChild; }
            }
            nodes--;
            return true;
        }

        { // в наличии оба потомка
            Node<T> replace = findMin(n[0].rightChild); // находим узел на замену: min в правом поддереве
            if (n[0] == n[1].leftChild){ n[1].leftChild = replace; }
                else { n[1].rightChild = replace; }
            replace.leftChild = n[0].leftChild;
            replace.rightChild = n[0].rightChild;
            nodes--;
            return true;
        }
    }

    public void display(){
        // красиво рисовать деревья мы сегодня не будем
    }

    public Node<T> findMin(Node<T> n){
        Node<T> current = n;
        Node<T> parent = current;
        while (current != null){
            parent = current;
            current = current.leftChild;
        }
        return parent;
    }

    public Node<T> findMax(Node<T> n){
        Node<T> current = n;
        Node<T> parent = current;
        while (current != null){
            parent = current;
            current = current.rightChild;
        }
        return parent;
    }

    public void inOrder(Node<T> rootNode){
        if (rootNode != null){
            inOrder(rootNode.leftChild);
            System.out.print(rootNode.id + " ");
            inOrder(rootNode.rightChild);
        }
    }

    // интересно, а как вообще метод отдельно тестировать, если метод мне нужен приватным для внутренних нужд?
    public int fillDepth(Node<T> rootNode, int distance){
        int maxDepth = distance;
        int k;
        if (rootNode != null){
            rootNode.distance = distance;
            distance++;
            k = fillDepth(rootNode.leftChild,distance);
            if (k > maxDepth) maxDepth = k;
            k = fillDepth(rootNode.rightChild,distance);
            if (k > maxDepth) maxDepth = k;
        }
        return maxDepth;
    }


    /**
     * Балансируем дерево методом пересоздания вместо балансировки при вставке,
     * по пути нарушая пару кастов (Java 1 явно недостаточно, чтобы это решить).
     * Путь решения производительности: делать Insert не по данным, а по объектам.
     * Впрочем, у меня нет обусловленного моим количеством знаний требуемого времени,
     * чтобы решить в классе все проблемы производительности.
     */
    public void balance(){
        Node[] treeArr = new Node[nodes]; // принимаем ноды любого типа
        toArray(root,treeArr);
        resetTree(); // сбрасываем настройки дерева
        toTree(treeArr, 0, treeArr.length); // так как мы заново создаем дерево, пересчет не нужен
    }

    private void toArray(Node<T> rootNode, Node[] arr){ // тут мог бы быть итератор вместо массива
        if (rootNode != null){
            toArray(rootNode.leftChild,arr);
            int i = 0; // итератор на коленке
            while (arr[i] != null){ i++; }
            arr[i] = rootNode;
            toArray(rootNode.rightChild,arr);
        }
    }

    private void resetTree(){
        root = null;
        nodes = depth = 0;
        leftNodes = rightNodes = 0;
    }

    private void toTree(Node[] arr, int left, int right) {
        if (left == right) return;
        int mid = left + (right - left)/2;
        insert(arr[mid].id, (T)arr[mid].data);
        toTree(arr,left,mid);
        toTree(arr,mid+1,right);
    }

    public boolean checkBalance(){
        fillDepth(root,0); // на случай, если мы чего-нибудь наудаляли - пересчитываем глубину и дистанцию
        return calcBalance(root);
    }

    private boolean calcBalance(Node<T> rootNode){
        if (rootNode == null) { return true;}
        if (rootNode.leftChild != null || rootNode.rightChild != null) {
            return calcBalance(rootNode.leftChild) && calcBalance(rootNode.rightChild);
        } else {
            return rootNode.distance >= (depth-1);
        }
    }

    public boolean checkIdealBalance(){
        leftNodes = rightNodes = 0;
        calcNodes(root.leftChild,false);
        calcNodes(root.rightChild,true);
        if (leftNodes == rightNodes) return true;
        return false;
    }

    private void calcNodes(Node<T> rootNode, boolean side){
        if (rootNode == null) return;
        if (rootNode.leftChild != null || rootNode.rightChild != null) {
            if (side) {leftNodes++;}
                else {rightNodes++;}
            calcNodes(rootNode.leftChild, side);
            calcNodes(rootNode.rightChild, side);
        }
    }
}
