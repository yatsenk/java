package JavaАlg1Lesson1;

//public class Main { // n! games
//    public static void main(String[] args) {
//        System.out.println(mul(20));
//    }
//    static double mul(double x){
//        return x==1 ? x : Math.floor(x / mul(x - 1));
//    }
//}


public class Main{
    public static void main(String[] args) {
        Mass mass = new Mass(300);
        Binary b = new Binary();
        System.out.println(b.getPosition(mass, 15));
    }
}


class Mass {
    private int length;
    private int [] m;

    Mass(int length){
        this.length = length;
        m = new int[length];
        m[0] = 0;
        for (int i = 1; i < length; i++) {
            m[i] = m[i-1] + (int)(Math.random()*3);
        }
    }

    public int getMassLength(){
        return length-1;
    }

    public int getMassI(int i){
        return m[i];
    }
}

class Binary{
    private int low;
    private int high;

    public Binary(){
        low = 0;
        high = 0;
    }

    public int getPosition(Mass m, int target){
        int gross;
        int step = 0;
        high = m.getMassLength();
        while (high >= low) {
            step++;
            gross = (low+high) / 2;
            if (m.getMassI(gross) > target){
                high = --gross;
            } else if (m.getMassI(gross) < target){
                low = ++gross;
            } else {
                System.out.println("Шагов поиска: " + step);
                return gross;
            }
        }
        return -1;
    }
}