package Java2L1.V2;

import Java2L1.V2.Interfaces.TeamMembers;

public class Robot implements TeamMembers {
    String name;
    int run;
    int jump;
    boolean failed;

    public Robot(String name) {
        this.name = name;
        this.run = 10;
        this.jump = 0;
        failed = false;
    }

    public boolean isFailed() {
        return failed;
    }

    public Robot(String name, int run, int jump) {
        this.name = name;
        this.run = run;
        this.jump = jump;
        failed = false;
    }

    @Override
    public void run(int r) {
        System.out.println("Робот бежит.");
        if (run < r) failed = true;
    }

    @Override
    public void jump(int j) {
        System.out.println("Робот прыгает.");
        if (jump < j) failed = true;
    }

}
