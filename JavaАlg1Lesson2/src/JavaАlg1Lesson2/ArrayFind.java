package JavaАlg1Lesson2;

public class ArrayFind {
    private int step;

    public int getStep(){
        return step+1;
    }

    public int linearFind(int[] m, int value){
        int length = m.length;
        int position = 0;
        step = 0;
        while (position < length && m[position] != value){
            position++;
            step++;
        }
        return position;
    }

    public int binaryFind(int [] m, int value){
        int low = 0;
        int high = m.length - 1;
        int mid;
        step = 0;

        while (high >= low){
            mid = (low + high) / 2;
            if (m[mid] > value){
                high = mid - 1;
                step++;
            } else if (m[mid] < value) {
                low = mid + 1;
                step++;
            } else {
                return mid;
            }
        }
        return m.length;
    }
}
