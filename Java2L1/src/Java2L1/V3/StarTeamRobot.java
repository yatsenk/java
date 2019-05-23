package Java2L1.V3;

public class StarTeamRobot extends StarTeam{
    public StarTeamRobot(String name) {
        super(name);
        super.type = "Робот";
    }

    public StarTeamRobot(String name, int run, int jump) {
        super(name, run, jump);
        super.type = "Робот";
    }
}
