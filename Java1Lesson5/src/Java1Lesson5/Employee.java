package Java1Lesson5;

public class Employee {
    private String name;
    private String position;
    private String email;
    private int tel;
    private int salary;
    private byte age;


    //Сотрудник по умолчанию, вот пусть ему все пишут и звонят
    public Employee(){
        this.name = "John Silver";
        this.position = "Pirate";
        this.email = "John.Silver@TheBlackPearl.so";
        this.tel = 166;
        this.salary = 30;
        this.age = 50;
    }

    //Полный конструктор
    public Employee(String name, String position, String email, int tel, int salary, int age) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.tel = tel;
        this.salary = salary;
        this.age = (byte)age;
        if (age < 18) {
            System.out.println(name + " слишком юн для нас, примем его на работу через " + (18-age) + " лет.");
            System.out.println();
        }
    }


    public void setName (String name){
        System.out.println(this.name + " поменял имя на " + name + ".");
        System.out.println();
        this.name = name;
    }

    public void setAge (int age) {
        if (this.age > age){
            System.out.println("У вас что, " + name + " помолодел?");
            System.out.println();
        }
        this.age = (byte) age;
    }

    public byte getAge(){
        return age;
    }

    public void printAll() {
        System.out.println("Имя: " + name);
        System.out.println("Должность: " + position);
        System.out.println("Email: " + email);
        System.out.println("Телефон: " + tel);
        System.out.println("Оклад: " + salary);
        System.out.println("Возраст: " + age);
        System.out.println();
    }


    public static void main(String[] args) {

        //массив объектов
        Employee[] Em = new Employee[5];

        //создаем объекты в массиве, используя оба конструктора
        Em[0] = new Employee(); //JSilver
        Em[1] = new Employee("Alien","bottom","a",111,20,40);
        Em[2] = new Employee("Daddy","top","b",333,40,15);
        Em[3] = new Employee("Zeli","right","c",4444,50,26);
        Em[4] = new Employee(); //son of JSilver
        //используем сеттеры для изменения объекта
        Em[4].setAge(18);
        Em[4].setName("Jack John Silver");

        for (int i = 0; i < 5 ; i++) {
            if (Em[i].getAge() < 40){
                Em[i].printAll();
                System.out.println();
            }
        }
    }
}
