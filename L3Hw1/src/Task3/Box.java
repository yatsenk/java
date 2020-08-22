package Task3;

import java.util.ArrayList;

//Special box only for fruits. Maybe isotherm?
public class Box<T extends Fruit> {
    //Keep something in a Task3.Box
    private ArrayList<T> aBoxKeeper = new ArrayList<>();

    //Put something in a Task3.Box with type T or it's childs.
    public void add(T something){ //Someone like put, someone like add, Java core arrays like both.
        aBoxKeeper.add(something);
    }

    //Calculate netto weight of the box by every item. Another way we can write Map for class-weight and multiply items quantity to weight.
    public float getWeight(){
        float sumWeight = 0;
        for (T something: aBoxKeeper) sumWeight += something.getWeight();
        return sumWeight;
    }

    //Compareing box with box for logistics reason.
    public boolean compare(Box<? extends Fruit> anotherBox){ //Task3.Box may be different, and anyway "boolean compare (Task3.Box<?> anotherBox)" not from this lesson.
        return this.getWeight() == anotherBox.getWeight();
    }

    //Moving fruit to friutbox by type.
    public void move(Box<T> anotherBox){ //Task3.Box may be differ size or some other child reasons.
        //It's ultralazy, but OK, no add extra addAll method or public aBoxKeeper
        for (T item : aBoxKeeper){
            anotherBox.add(item);
        }
        aBoxKeeper = new ArrayList<>(); //Is it a best practice?
    }
}
