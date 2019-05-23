package Java2L1.V3;

public abstract class StarTeam {
    String name;
    String type;
    int run;
    int jump;
    boolean failed;

    public StarTeam(String name) {
        this.name = name;
        this.run = 20000;
        this.jump = 200;
        failed = false;
    }

    public StarTeam(String name, int run, int jump) {
        this.name = name;
        this.run = run;
        this.jump = jump;
        failed = false;
    }


    public boolean isFailed() {
        return failed;
    }

    public void run(int r) {
        System.out.println(type + " " + name + " бежит.");
        if (run < r) failed = true;
    }

    public void jump(int j) {
        System.out.println(type + " " + name + " прыгает.");
        if (jump < j) failed = true;
    }
}
