package Java2L1;

public class Course {
    double [] snags; // массив препятствий по условию задачи

    public Course(double ... snags) { // конструируем полосу препятствий
        this.snags = snags;
    }
    
    public void doIt(Team t){ // отправляем команду выигрывать чемпионат района
        for (double d:snags) t.pass(d);
    }
}
