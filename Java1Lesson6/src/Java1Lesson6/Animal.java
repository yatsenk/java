package Java1Lesson6;

abstract class Animal{
    protected String name = "Noname";
    protected String animal;
    protected int run_distance;
    protected double jump_height;
    protected boolean swim_ability;
    protected int swim_distance;

    public boolean run(int x){
        if (x <= run_distance){
            System.out.println( animal +  " " + name + " убежал на " + x + " метров.");
            return true;
        } else {
            System.out.println(animal +  " " + name + " так далеко не бегает.");
            return false;
        }
    }

    public boolean jump(double x){
        if (x <= jump_height){
            System.out.println(animal +  " " + name + " подпрыгнул на " + x + " метров.");
            return true;
        } else {
            System.out.println(animal +  " " + name + " так высоко не прыгает.");
            return false;
        }
    }

    public boolean swim(int x){
        if (x <= swim_distance && swim_ability){
            System.out.println(animal +  " " + name + " пропылал " + x + " метров.");
            return true;
        } else {
            System.out.println(animal +  " " + name + " так далеко не плавает.");
            return false;
        }
    }
}