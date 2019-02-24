package JavaАlg1Lesson2;

public class ArrayOperator {

    public void generateRand(int[] m){
        int length = m.length;
        for (int i = 0; i < length; i++) {
            m[i] = (int) (Math.random() * length);
        }
    }

    public void generateReverse(int[] m){
        int length = m.length;
        for (int i = 0; i < length; i++) {
            m[i] = (int)(length - i + Math.random()*2);
        }
    }

    public boolean fullCopy(int[] m1, int[] m2){
        int length1 = m1.length;
        int length2 = m2.length;
        if (length1 != length2) return false;
        for (int i = 0; i < length2; i++) {
            m2[i] = m1[i];
        }
        return true;
    }

    public void print(int[] m){
        int length = m.length;
        for (int i = 0; i < length; i++) {
            System.out.printf("%6d",m[i]);
            if ((i+1)%20 == 0) System.out.println(); // массив с 0, для форматирования надо считать с 1
        }
        System.out.println();
    }

    public int[] insert(int [] m, int position, int value){
        int length = m.length;
        if (length < position) return m;
        int [] tmp = new int[length+1];
        for (int i = 0; i < position; i++) {
            tmp[i] = m[i];
        }
        tmp[position] = value;
        for (int i = position; i < length ; i++) {
            tmp[i+1] = m[i];
        }
        return tmp;
    }

    public int[] deleteByPosition(int [] m, int position){
        int length = m.length;
        if (length < position) return m;
        int [] tmp = new int[length-1];
        for (int i = 0; i < position; i++) {
            tmp[i] = m[i];
        }
        for (int i = position+1; i < length ; i++) {
            tmp[i-1] = m[i];
        }
        return tmp;
    }

    public int[] deleteByValue(int [] m, int value){
        int length = m.length;
        int position;

        ArrayFind find = new ArrayFind();
        position = find.linearFind(m, value);
        if (position == length) return m;

        int [] tmp = new int[length-1];
        for (int i = 0; i < position; i++) {
            tmp[i] = m[i];
        }
        for (int i = position+1; i < length ; i++) {
            tmp[i-1] = m[i];
        }
        return tmp;
    }


}
