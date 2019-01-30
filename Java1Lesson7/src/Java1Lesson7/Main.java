package Java1Lesson7;

class Plate{
    private int food;
    public Plate(int food){ // создаем тарелку с едой
        this.food = food;
    }
    public void addFood(int food){ // положить еще еды
        this.food +=food;
        System.out.println();
        System.out.println("В тарелку положили " + food + " еды: теперь " + this.food + " осталось.");
    }
    public boolean eatFood(int food){ // кот ест еду
        if ((this.food - food) >= 0){
            this.food-=food;
            return true;
        } else {
            System.out.println();
            System.out.println("В тарелке нет " + food + " еды: " + this.food + " осталось.");
            return false;
        }
    }
    public int leftFood(){
        System.out.println(food + " еды осталось в тарелке.");
        return food;
    }

}

class Cat{
    private String name;
    private int appetite;
    private boolean hungry = true;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }
    public boolean eat(Plate p){
        if (hungry){
            if (p.eatFood(appetite)){
                hungry = false;
                System.out.println(name + " покушал " + appetite + " еды.");
                return true;
            } else {
                System.out.println(name + " так и остался голодным.");
                return false;
            }
        } else {
            System.out.println(name + " был не голоден.");
            return false;
        }
    }

    public String getName(){
        return name;
    }

    public boolean isHungry(){
        return hungry;
    }
}

public class Main {

    public static void main(String[] args) {

        Plate plate = new Plate(110);
        Cat[] cat = new Cat[6];
        cat[1] = new Cat("Barsik", 10);
        cat[2] = new Cat("Murzik", 20);
        cat[3] = new Cat("Meow", 30);
        cat[4] = new Cat("Gav", 40);
        cat[5] = new Cat("Fifth", 50);


        //проверяем голодность котов
        for (int i = 1; i < 6; i++) {
            System.out.print(cat[i].getName());
            if (cat[i].isHungry()){
                System.out.println(" голоден.");
            } else {
                System.out.println(" сыт.");
            }
        }

        System.out.println();

        //кормим котов
        for (int i = 1; i < 6; i++) {
            cat[i].eat(plate);
        }

        plate.addFood(40);

        //еще кормим котов
        for (int i = 1; i < 6; i++) {
            cat[i].eat(plate);
        }

        System.out.println();
        plate.leftFood();
        System.out.println();

        //проверяем голодность котов
        for (int i = 1; i < 6; i++) {
            System.out.print(cat[i].getName());
            if (cat[i].isHungry()){
                System.out.println(" голоден.");
            } else {
                System.out.println(" сыт.");
            }
        }

    }
}
