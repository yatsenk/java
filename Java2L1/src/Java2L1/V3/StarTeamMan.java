package Java2L1.V3;

public class StarTeamMan extends StarTeam {
    public StarTeamMan(String name) {
        super(name);
        super.type = "Человек";
    }

    public StarTeamMan(String name, int run, int jump) {
        super(name, run, jump);
        super.type = "Человек";
    }
}
