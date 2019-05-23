package Java2L1.V1;

import java.util.List;
import java.util.LinkedList;

public class Team{
    List<Gamer> gamers = new LinkedList<>(); // так должно быть быстрее на миллионах записей,
                                             // потому что у нас строго последовательный доступ в задаче

    public Team(String ... newgamer){
        for (String s: newgamer) gamers.add(new Gamer(s));
    }

    public void displayAll(){
        System.out.println("Состав команды: ");
        for (Gamer g: gamers) System.out.println(g.name);
    }

    public void showResulta(){
        System.out.println("Прошли все испытания: ");
        for (Gamer g: gamers) if (g.passed) System.out.println(g.name);
    }

    public void pass(double threshold){
        for (Gamer g: gamers) {
            if (Math.random()*100 < threshold) g.passed = false; // кто самое слабое звено?
        }
    }

    //тут храним игроков
    private class Gamer{
        String name;
        boolean passed; // прошел испытание

        public Gamer(String name) {
            this.name = name;
            passed = true;
        }
    }


}
