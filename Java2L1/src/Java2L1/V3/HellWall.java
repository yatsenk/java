package Java2L1.V3;

public class HellWall extends Hell {
    int height = 100;

    private void jump(StarTeam s){
        if (!s.isFailed()){
            s.jump(height);
            if (!s.isFailed())
                System.out.println("Перепрыгнул!");
            else System.out.println("Недолет!");
        }
    }

    @Override
    public void solve(StarTeam s) {
        jump(s);
    }
}
