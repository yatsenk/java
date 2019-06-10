package Java2L5;

/**
 Если другой метод будет использовать s без синхронизации?
 Будет блокировка или будет ошибка в коде?

 Суть вопроса: один метод работает с объектом в синхронизированном коде,
 а другой - в обычном. Будет ли блокировка этого объекта для обычного кода.

 Вывод: объект не блокируется с использованием синхронизации для других не-синхронизированных потоков.
 Внимательная синхронизация (и копирование объектов) спасет мир и длительные расчеты.
 */

class Increment { // чтобы уж точно объект, прости, Integer
    int i;
    public Increment() {this.i = 0;}
    public int getI() {return i;}
    public void setI(int i) {this.i = i;}
}

public class SyncQuestions {

    static Increment increment = new Increment();
    public static void main(String[] args) {
        increment.setI(0);
        new Thread(() -> inc1()).start();
        new Thread(() -> inc2()).start();
    }

    static void inc1(){
        synchronized (increment)
        {
            System.out.println("start sync t1");
            for (int i = 0; i < 10; i++) {
                increment.setI(increment.getI() + 1);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            System.out.println("t1 " + increment.getI() + " - ожидается _10_ хотя бы раз, если есть блокировка объекта для другого метода");
            System.out.println("end sync t1 \n");
        }
    }

    static void inc2(){
        //synchronized (increment)
        {
            for (int i = 0; i < 10; i++) {
                increment.setI(increment.getI() + 1);
                try {
                    Thread.sleep(30); // разное значение задержек для наглядности ошибок в расчетах, можно получить "t2 19"
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("t2 " + increment.getI());
        }
    }
}