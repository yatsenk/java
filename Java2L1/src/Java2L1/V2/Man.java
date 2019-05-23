package Java2L1.V2;

import Java2L1.V2.Interfaces.TeamMembers;

public class Man implements TeamMembers {
    String name;
    int run;
    int jump;
    boolean failed;

    public Man(String name) {
        this.name = name;
        run = 1000;
        jump = 2;
        failed = false;
    }

    public boolean isFailed() {
        return failed;
    }

    public Man(String name, int run, int jump) {
        this.name = name;
        this.run = run;
        this.jump = jump;
        failed = false;
    }


    @Override
    public void run(int r) {
        System.out.println("Человек бежит.");
        if (run < r) failed = true;
    }

    @Override
    public void jump(int j) {
        System.out.println("Человек прыгает.");
        if (jump < j) failed = true;
    }
}
