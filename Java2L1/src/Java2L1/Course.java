package Java2L1;

public class Course {
    int quantity; // штуки препятствий
    double [] snags; // сами препятствия

    public Course(double ... snags) { // конструируем полосу препятствий
        this.quantity = snags.length;
        this.snags = snags;
    }
    
    public void doIt(Team t){ // отправляем команду выигрывать чемпионат района
        for (double d:snags) {
            t.pass(d);
        }
    }
}
