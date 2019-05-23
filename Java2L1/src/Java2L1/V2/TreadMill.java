package Java2L1.V2;

import Java2L1.V2.Interfaces.Problems;
import Java2L1.V2.Interfaces.TeamMembers;

public class TreadMill implements Problems {
    int distance;

    public TreadMill() {
        this.distance = 100;
    }

    public TreadMill(int distance) {
        this.distance = distance;
    }

    public void run(TeamMembers t){
        if (!t.isFailed()){
            t.run(distance);
            if (!t.isFailed())
                System.out.println("Убежал!");
            else System.out.println("Выдохся!");
        }
    }

    @Override
    public void solve(TeamMembers t) {
        run(t);
    }
}
