package Java2L1.V3;

public class StarTeamCat extends StarTeam {
    public StarTeamCat(String name) {
        super(name);
        super.type = "Кот";
    }

    public StarTeamCat(String name, int run, int jump) {
        super(name, run, jump);
        super.type = "Кот";
    }
}
