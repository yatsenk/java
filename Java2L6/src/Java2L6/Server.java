package Java2L6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final static int PORT = 9999; // что слушаем
    private static Socket socket; // сюда открываем коннект

    private static void start(){
        try(ServerSocket ss = new ServerSocket(PORT)) {
            System.out.println("Server started.");
            socket = ss.accept(); // создаем сокет при контакте клиента
            System.out.println("Client accepted.");
        } catch (IOException e) {
            System.out.println("Something gone wrong. Check configuration.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        start(); // стартуем сокет и ждем первого клиента
        new Terminal(socket); // открываем терминал для общения
    }
}
