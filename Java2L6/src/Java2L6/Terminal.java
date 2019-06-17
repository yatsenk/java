package Java2L6;

/**
 * Участок кода, отвечающий за коннекты и общение в терминале.
 * Первый шаг на пути к децентрализации и свободным выборам в сети! (Сервера, не президента.)
 */

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Terminal {
    private static String EOS = "bye"; // заканчиваем работу;
    private DataInputStream dis;
    private DataOutputStream dos;
    private Socket socket;

    public Terminal(Socket socket){
        this.socket = socket;
        openConnection();
        readMSG();
        writeMSG();
    }

    private void openConnection(){
        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            System.out.println("Напишите " + EOS + ", чтобы завершить работу с сервером."); // потому что GUI по заданию нет.
        } catch (IOException | NullPointerException e ) {
            System.out.println("Socket not opened.");
            System.exit(1);
        }
    }

    private void closeConnection() {
        try {
            dis.close();
            dos.close();
            socket.close();
            System.exit(0); // раз мы пишем прикладной консольный клиент, приложение явно можно и нужно завершать
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMSG(){
        new Thread(() -> {
           while (socket.isConnected()) { // явно лишняя проверка, но пусть будет - на время отладки была полезной и вселяля надежду
               try {
                   String s = dis.readUTF();
                   System.out.println(s);
                   if (s.equalsIgnoreCase(EOS)) closeConnection();
               } catch (IOException e) {
                   break;
               }
           }
        }).start();
    }

    /**
     * Я полагаю, использовать _здесь_ Scanner или BufferedReader - скорее вопрос религии и потоков.
     * Но Scanner выглядел бы компактнее и логичнее для меня, даже если не умеет синхронизацию.
     */
    private void writeMSG(){
        new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            try {
                while (socket.isConnected()) {
                    String s = sc.nextLine();
                    dos.writeUTF(s);
                    if (s.equalsIgnoreCase(EOS)) closeConnection();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            sc.close();
        }).start();
    }

//    private void writeMSG(){
//        new Thread(() -> {
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//            try {
//                while (socket.isConnected()) {
//                    String s = br.readLine();
//                    dos.writeUTF(s);
//                    if (s.equalsIgnoreCase(EOS)) closeConnection();
//                }
//                br.close(); // и тут эксепшены (логично при блокировке объекта)
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();
//    }

}
