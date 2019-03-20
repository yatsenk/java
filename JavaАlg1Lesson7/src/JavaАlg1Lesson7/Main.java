package JavaАlg1Lesson7;

public class Main {

    public static void main(String[] args) {
        VertexDistance vd = new VertexDistance(15);
        int distance; // вычисленное кратчайшее расстояние

        System.out.println();
        distance = vd.bfs('A','C');
        System.out.println("1. Кратчайший путь обходом в ширину: " + ((distance == -1)?"не найден":distance));

        System.out.println();
        distance = vd.dfs('A','C');
        System.out.println("2. Кратчайший путь перебором в глубину: " + ((distance == -1)?"не найден":distance));

//        System.out.println();
//        distance = vd.commy('A');
//        System.out.println("3. Обход коммиявожера из точки A: " + ((distance == -1)?"не найден":distance));

        System.out.println("\n\n3. Решение задачи коммивояжера рекурсивным NP-перебором.\n");
        vd.commy_everywhere();

    }


}


