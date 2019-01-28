package Java1Lesson6;

class Dog extends Animal{
    public Dog() {
        this.animal = "Dog";
        this.run_distance = 500;
        this.jump_height = 0.5;
        this.swim_ability = true;
        this.swim_distance = 10;
    }
    public Dog(String name){
        this();
        this.name = name;
    }
    public Dog(String name, int run_distance){
        this(name);
        this.run_distance = run_distance;
    }

    public void setName(String name){
        this.name = name;
    }
    public boolean setRun_distance(int run_distance){
        int min = 400;
        int max = 600;
        if (run_distance <= max && run_distance >= min){
            this.run_distance = run_distance;
            return true;
        } else {
            System.out.println("Наши собаки максимум бегают 400-600 метров.");
            return false;
        }
    }

    @Override
    public boolean run(int x){
        if (x <= run_distance){
            System.out.println("Собака " + name +  " убежала на " + x + " метров.");
            return true;
        } else {
            System.out.println("Собака " + name + " так далеко не бегает.");
            return false;
        }
    }
}
