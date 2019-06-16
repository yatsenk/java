package Java2L6;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private final static int PORT = 9999; // куда подключаемся
    private final static String ADDRESS = "localhost"; // сервер локально, а вообще можно скан сети написать, и автоопределение ролей
    private static Socket socket;

    private static void connect(){
        try {
            socket = new Socket(ADDRESS,PORT);
        } catch (IOException e) {
            System.out.println("Check server and network.");
        }
    }

    public static void main(String[] args){
        connect();
        new Terminal(socket);
    }
}
