package Java2L1;

public class Main {

    public static void main(String[] args) {
        Team team = new Team("Ivan", "Stas", "Boris", "Vlad");
        Course c = new Course(10, 20, 30); // сложность каждого препятствия в процентах

        c.doIt(team);
        team.showResulta();
    }
}
