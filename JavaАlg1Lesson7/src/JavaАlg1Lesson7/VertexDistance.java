package JavaАlg1Lesson7;

import java.util.LinkedList;
import java.util.Random;

public class VertexDistance {
    private int vertexCount = 0; // счетчик вершин
    private int maxVertex; // максимальное количество вершин
    private Vertex [] vertexList; // список вершин
    private int[][] adjMatrix; // список связей вершин
    private LinkedList<Integer> ll; // стек, очередь и прочие вспомогательные конструкции в одном лице

    public VertexDistance(int maxVertex){
        this.maxVertex = maxVertex;
        vertexList = new Vertex[maxVertex];
        adjMatrix = new int [maxVertex][maxVertex];
        ll = new LinkedList<>();
        fillVertex(); // заполняем вершины
        randomAdjDirect(); // заполняем направленную матрицу
//        randomAdjSymm(); // заполняем симметричную матрицу
        printAdjMatrix(); // выводим матрицу
    }

    // добавление вершины
    private boolean addVertex(char c){
        if (vertexCount < maxVertex) {
            vertexList[vertexCount++] = new Vertex(c);
            return true;
        } else return false;
    }

    // заполнение вершин последовательно
    private void fillVertex(){
        for (int i = 0; i < maxVertex; i++) {
            addVertex((char)(65+i));
        }
    }

    // генерация матрицы ненаправленного графа
    private void randomAdjSymm(){
        Random rnd = new Random();
        for (int i = 0; i < maxVertex; i++) {
            for (int j = 0; j < i; j++) {
                if (rnd.nextInt(2) == 0) adjMatrix[i][j] = adjMatrix[j][i] = 0; // разряжаем
                else adjMatrix[i][j] = adjMatrix[j][i] = rnd.nextInt(10);
            }
        }
        // инициализируем диагональ
        for (int i = 0; i < maxVertex; i++) {
            adjMatrix[i][i] = 0;
        }
    }

    // генерация матрицы направленного графа
    private void randomAdjDirect(){
        Random rnd = new Random();
        // заполняем всю матрицу случайными числами
        for (int i = 0; i < maxVertex; i++) {
            for (int j = 0; j < maxVertex; j++) {
                if (rnd.nextInt(2) == 0) adjMatrix[j][i] = 0; // разряжаем
                else adjMatrix[i][j] = rnd.nextInt(10);
            }
        }
        // обнуляем диагональ
        for (int i = 0; i < maxVertex; i++) {
            adjMatrix[i][i] = 0;
        }
    }

    // печать матрицы
    private void printAdjMatrix(){
        System.out.println("Матрица смежности для " + vertexCount + " вершин.");
        for (int i = 0; i < maxVertex; i++) {
            System.out.print(vertexList[i].label + " ");
        }
        System.out.println();
        for (int i = 0; i < maxVertex; i++) {
            for (int j = 0; j < maxVertex; j++) {
                System.out.print(adjMatrix[j][i] + " ");
            }
            System.out.println(vertexList[i].label);
        }
    }

    // номер вершины по ее названию
    private int getVertexID(char c){
        for (int i = 0; i < vertexCount; i++) {
            if (vertexList[i].label == c) return i;
        }
        return -1;
    }

    // сброс посещенных вершин
    private void resetWasVisited(){
        for (Vertex v : vertexList) {
            v.wasVisited = false;
            v.distance = 0;
        }
    }

    /**
     * Поиск пути в ширину. Недостаток решения: если участок пути пересекается с ранее пройденным участком -
     * невозможно использовать этот отрезок.
     * Пример: A - B - (D - E) закрывает путь для A - C - (D - E), даже если A - B длинее A - C.
     * Решение: искать путь не поиском в ширину, а рекурсивным поиском в глубину. Заодно и список пройденных
     * вершин наиболее короткого маршрута можно вывести.
     *
     * @param c1,c2: начальная и конечная точки маршрута
     */
    public int bfs(char c1, char c2){
        resetWasVisited();
        int v1 = getVertexID(c1), v2 = 0, target = getVertexID(c2); // координаты вершин
        int way = -1; // количество шагов до нужной вершины
        // задаем отправную вершину
        vertexList[v1].wasVisited = true;
        ll.addFirst(v1);
        while (!ll.isEmpty()){
            v1 = ll.removeLast();
            if (v1 == target && (way > vertexList[v1].distance || way == -1))
                way = vertexList[v1].distance;
            while ((v2 = nextUnvisitedVertex(v1)) != - 1){
                vertexList[v2].wasVisited = true;
                vertexList[v2].distance = vertexList[v1].distance + adjMatrix[v1][v2];
                ll.addFirst(v2);
            }
        }
        return way;
    }

    // следующая непосещенная вершина - используется исключительно в ширину
    private int nextUnvisitedVertex(int ver){
        for (int i = 0; i < vertexCount; i++) {
            if (adjMatrix[ver][i] > 0 && !vertexList[i].wasVisited) return i;
        }
        return -1;
    }

    /**
     * Поиск пути в глубину, рекурсивный. С выводом вершин маршрута.
     * @param c1,c2: начальная и конечная точки маршрута
     */
    private int target; // целевая вершина
    private LinkedList<Integer> visited; // лист посещенных вершин
    private Integer[] out; // результирующий массив
    private int way; // длина пути

    public int dfs(char c1, char c2){
        int v1 = getVertexID(c1); // мы находимся здесь
        target = getVertexID(c2); // попасть надо сюда
        visited = new LinkedList<>();
        way = -1;

        dfs_step(v1,0); // запускаем рекурсивный обход
        dfs_printOut(); // печатаем узлы
        return way;
    }

    // рекурсивный обход
    private void dfs_step(int v1, int distance){
        visited.addLast(v1); // мы пришли

        // заканчиваем обход в эту сторону, если дошли
        if (v1 == target)
            dfs_end(distance);
        // если еще ходим
        else for (int i = 0; i < vertexCount; i++)
            if (adjMatrix[v1][i] != 0 && !visited.contains(i)) dfs_step(i, distance + adjMatrix[v1][i]);

        visited.removeLast(); // мы ушли, теперь следующие маршруты могут включать этот узел
    }

    // рекурсивный обход: пришли к цели
    private void dfs_end(int distance){
        if (distance < way || way == -1){
            way = distance;
            out = visited.toArray(new Integer[0]);
        }
    }

    // печать узлов из массива обхода
    private void dfs_printOut(){
        if (out == null) System.out.print("Маршрут отсутствует.");
        else for (int i:out)
            System.out.print(" -> " + vertexList[i].label);
        System.out.println();
    }

    /**
     * Задача коммивояжера: побывать везде по одному разу, и желательно побыстрее
     * @param c1: начальная точка обхода
     */

    public int commy(char c1){
        //устанавливаем переменные, разработанные для dfs
        visited = new LinkedList<>(); // список посещенных узлов, который должен был быть пустым к этому моменту
        way = -1; // пройденный путь
        out = null; // маршрут пути

        int v1 = getVertexID(c1); // начальная точка маршрута
        commy_step(v1, 0,0);
        dfs_printOut(); // печатем узлы, этот метод вполне подходит
        return way;
    }

    private void commy_step(int v1, int distance, int step){
        visited.addLast(v1); // добавляем вершину в список на правах стека

        // если мы все обошли в эту сторону и вершины закончились
        if (step == vertexCount - 1)
            dfs_end(distance); // тоже у dfs позаимствовали
        // самый интересный цикл
        else for (int i = 0; i < vertexCount; i++)
            if (adjMatrix[v1][i] != 0 && !visited.contains(i)) commy_step(i, distance + adjMatrix[v1][i],step+1);

        visited.removeLast();
    }

    /**
     * Обход коммивояжера с различными начальными точками
     */

    public void commy_everywhere(){
        for (int i = 0; i < vertexCount; i++) {
            char label = vertexList[i].label;
            int distance = commy(label);
            System.out.println("Обход коммиявожера из точки " + label + ": " + ((distance == -1)?"не найден":distance));
        }
    }
}

// вершина, которая нам не очень нужна в обходе графа (можно хранить посещение в массиве boolean)
class Vertex {
    char label; // название
    boolean wasVisited; // факт посещения
    int distance; // дистанция от начальной точки, чтобы было интереснее.

    public Vertex(char label) {
        this.label = label;
        this.wasVisited = false;
        this.distance = 0;
    }
}