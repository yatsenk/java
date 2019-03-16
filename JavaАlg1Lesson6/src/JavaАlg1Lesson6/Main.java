package JavaАlg1Lesson6;

public class Main {

    public static void main(String[] args) {
	    /**
         * Строим 20 деревьев глубиной 6 уровней и проверяем, кто из них несбалансированный (все, я ожидаю).
         *
         * Критерий сбалансированности 1: различие дистанции листьев не более чем на 1 по всему дереву, что
         * жестче чем https://ru.wikipedia.org/wiki/АВЛ-дерево , но еще не идеально сбалансированное.
         * Так как максимальную высоту мы знаем из условий задачи и счетчика в классе дерева,
         * то напишем метод checkBalance, который обходит все листья и сравнивает их глубину
         * (которую я на всякий случай записал в ноды, но вычислить на лету тривиально).
         *
         * Критерий сбалансированности 2: в левой и правой половине дерева одинаковое количество узлов.
         * Пишем checkIdealBalance!
         * (Явно надо было Седжвика сначала почитать.)
         * */

	    final int LESS_THEN = 6; // 6 единиц глубины по условию задачи (с рутом 0 - это 7)
        final int TREES_QUANTITY = 2000; // 20 шт деревьев по условию задачи
	    MyTree<Integer>[] trees = new MyTree[TREES_QUANTITY]; // я уже понял, что так массивы создавать нельзя, но переписывать весь код не буду
        int balancedCount = 0;
        int idealBalancedCount = 0;

        for (int i = 0; i < TREES_QUANTITY; i++) {
            trees[i] = new MyTree<>(); // создаем

            while (trees[i].depth < LESS_THEN){ // заполняем
                trees[i].insert((int)(Math.random() * 200 - 100),12345);
            }

            boolean isBalanced = trees[i].checkBalance(); // проверяем балансировку
            boolean isIdealBalanced = trees[i].checkIdealBalance(); // проверяем идеальную балансировку (никому не везет, не выводим)
            if (isBalanced) {
                balancedCount++;
                System.out.println("Дерево № " + (i+1) + " имеет "
                        + trees[i].nodes + " нод и является " +
                        (isBalanced?"сбалансированным":"несбалансированным") + ".");
            }
        }

        System.out.println(balancedCount + " деревьев являются сбалансированными.");
        System.out.println(idealBalancedCount + " деревьев являются идеально сбалансированными.");
        System.out.println();
        System.out.println("Задача 2 ДЗ выполнена: " + (balancedCount * 100 / TREES_QUANTITY) + "% сбалансированных деревьев.");
        System.out.println();
        System.out.println("Выполняем балансировку...");
        balancedCount = idealBalancedCount = 0;

        for (int i = 0; i < TREES_QUANTITY; i++) {
            trees[i].balance();
            if (trees[i].checkBalance()) balancedCount++;
            if (trees[i].checkIdealBalance()) idealBalancedCount++;
        }
        System.out.println(balancedCount + " деревьев являются сбалансированными и их листья находятся на одной глубине.");
        System.out.println(idealBalancedCount + " деревьев являются идеально сбалансированными, левая и правая сторона обладают одинаковым количеством узлов.");
    }
}
