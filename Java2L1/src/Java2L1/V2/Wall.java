package Java2L1.V2;

import Java2L1.V2.Interfaces.Problems;
import Java2L1.V2.Interfaces.TeamMembers;

public class Wall implements Problems {
    int height;

    public Wall() {
        this.height = 4;
    }

    public Wall(int height) {
        this.height = height;
    }

    public void jump(TeamMembers t){
        if (!t.isFailed()){
            t.jump(height);
            if (!t.isFailed())
                System.out.println("Перепрыгнул!");
            else System.out.println("Недолет!");
        }
    }

    @Override
    public void solve(TeamMembers t) {
        jump(t);
    }
}
