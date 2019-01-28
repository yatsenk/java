package Java1Lesson6;

class Cat extends Animal{
    public Cat(){
        this.animal = "Cat";
        this.run_distance = 200;
        this.jump_height = 2.0;
        this.swim_ability = false;
        this.swim_distance = 0;
    }

    public Cat(String name){
        this();
        this.name = name;
    }
}