package Java2L1;

import java.util.ArrayList;

public class Team{
    int quantity; // количество игроков, никто не знает зачем
    Gamer [] gamers; // список игроков

    public Team(String ... newgamer){
        this.quantity = newgamer.length;

        this.gamers = new Gamer[quantity]; // создаем массив объектов и добавляем имена... ArrayList??? Ладно, это скучно,
                                            // зато я теперь вспомнил, как делать правильно.
        for (int i = 0; i < quantity; i++) {
            this.gamers[i] = new Gamer(newgamer[i]);
        }
    }

    public void displayAll(){
        System.out.println("Состав команды: ");
        for (int i = 0; i < quantity; i++) {
            System.out.println(gamers[i].name);
        }
    }

    public void showResulta(){
        System.out.println("Прошли все испытания: ");
        for (Gamer g: gamers) if (g.passed) System.out.println(g.name); // играемся с циклами
    }

    public void pass(double threshold){
        for (Gamer g: gamers) {
            if (Math.random() < threshold) g.passed = false; // кто самое слабое звено?
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
