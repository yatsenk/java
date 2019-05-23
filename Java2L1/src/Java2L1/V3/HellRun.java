package Java2L1.V3;

public class HellRun extends Hell {
    int distance = 100000;

    public void run(StarTeam s){
        if (!s.isFailed()){
            s.run(distance);
            if (!s.isFailed())
                System.out.println("Убежал!");
            else System.out.println("Выдохся!");
        }
    }

    @Override
    public void solve(StarTeam s) {
        run(s);
    }
}
