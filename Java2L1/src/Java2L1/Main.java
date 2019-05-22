package Java2L1;

public class Main {

    public static void main(String[] args) {
        Team team = new Team("Ivan", "Stas", "Boris", "Vlad");
        team.displayAll();

        Course c = new Course(0.01, 0.02, 0.5);
        c.doIt(team);
        team.showResulta();
    }
}
