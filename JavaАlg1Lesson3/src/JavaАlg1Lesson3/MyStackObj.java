package JavaАlg1Lesson3;


/**
 * Пишем свой односвязный LinkedList
 */
public class MyStackObj {
    private int size = 0;
    private Stack top;

    public MyStackObj(){}
    public MyStackObj(char ch){
        top = new Stack(null,ch);
    }

    public int getSize() {
        return size;
    }

    void push(char ch){
        if (size == 0){ top = new Stack(null,ch); } //когда у нас пустой или стертый стек - создаем первый объект
        else { top = new Stack(top, ch); } //иначе передаем в новый объект ссылку на предыдущий и полезную нагрузку
        size++;
    }

    char pop(){
        if (size > 0){
            size--;
            char chr = top.ch; //временное хранение, иначе потом не сможем считать
            top = top.link;
            return chr;
        } else return 0;
    }

    char peer(){
        return top.ch;
    }

    /**
     * в этом классе мы хранием значение и ссылку на предыдущий объект
     * я избавился от конструктора с передачей только char, чтобы улучшить читаемость
     */
    class Stack {
        Stack link;
        char ch;

        Stack(Stack link, char ch) {
            this.link = link;
            this.ch = ch;
        }
    }
}
