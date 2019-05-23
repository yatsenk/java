package Java2L1.V2;

import Java2L1.V2.Interfaces.TeamMembers;

public class Cat implements TeamMembers {
    String name;
    int run;
    int jump;
    boolean failed;

    public Cat(String name) {
        this.name = name;
        this.run = 100;
        this.jump = 5;
        failed = false;
    }

    public boolean isFailed() {
        return failed;
    }

    public Cat(String name, int run, int jump) {
        this.name = name;
        this.run = run;
        this.jump = jump;
        failed = false;
    }

    @Override
    public void run(int r) {
        System.out.println("Кот бежит.");
        if (run < r) failed = true;
    }

    @Override
    public void jump(int j) {
        System.out.println("Кот прыгает.");
        if (jump < j) failed = true;
    }
}
