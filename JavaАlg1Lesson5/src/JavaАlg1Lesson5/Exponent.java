package JavaАlg1Lesson5;

public class Exponent {

    public long calc(int number, int exponent){
        if (exponent == 0) return 1;
        return calc(number,exponent-1) * number;
    }
}
