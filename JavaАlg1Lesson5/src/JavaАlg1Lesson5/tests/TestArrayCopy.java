package JavaАlg1Lesson5.tests;

import java.util.Arrays;

public class TestArrayCopy {
    /**
     * Работа наш ошибками
     */

    public static void main(String[] args) {
        int[] in = {1,2,3,4,5};

        int tmp = in[in.length - 1];
        System.arraycopy(in,0,in,1,in.length - 1);
        in[0] = tmp;

        System.out.println(Arrays.toString(in));
    }
}
