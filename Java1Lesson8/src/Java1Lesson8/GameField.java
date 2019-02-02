// This is a personal academic project. Dear PVS-Studio, please check it.

// PVS-Studio Static Code Analyzer for C, C++ and C#: http://www.viva64.com

package Java1Lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameField extends JFrame{
    private JButton[] jb = new JButton[9];
    private int ux,uy;

    public GameField(CrossX cx) throws HeadlessException{
        setTitle("CrossX");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100,100,300,300);
        setResizable(false);

        setLayout(new GridLayout(3,3));

        initButtons(cx);

        setVisible(true);
    }

    private void initButtons(CrossX cx){
        for (int i = 0; i < 9; i++) {
            final int b_i = i; // потому что actionListener и Google на час в 7 утра
            jb[b_i] = new JButton();
            add(jb[b_i]);
            jb[b_i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ux=b_i/3;
                    uy=b_i%3;
                    jb[b_i].setText("X");
                    jb[b_i].removeActionListener(this); //и вот тут тоже гугл на полчаса, хотя это же очевидно
                    playARound(cx);
                    }
            });
        }
    }

    private void win(String s){
        JFrame w = new JFrame();
        w.setBounds(100,200,300,100);
        w.setLayout(new GridLayout(1,1));
        JButton close = new JButton(s + " выиграл!");
        w.add(close);
        w.setVisible(true);
        close.addActionListener(e -> System.exit(0)); // в 10-30 утра я все еще не знаю, что такое лямюда, но обязательно узнаю!

    }

    private boolean playARound(CrossX cx){ // 9 утра, спагетти в силе, зачем я в этот класс все пишу?
        cx.player(ux,uy);
        if (cx.checkWin(cx.player)) {win("Игрок");}
        if (cx.isMapFull()) {win("Никто не"); return false;}
        o_button(cx.computer());
        if (cx.checkWin(cx.computer)) {win("Компьютер");}
        if (cx.isMapFull()) {win("Никто не");}
        return false;
    }

    private void o_button(int xy){ //ход компа
        jb[xy].setText("O");
        for (ActionListener listener : jb[xy].getActionListeners()) { // последствия гугла выше на полчаса (с)
            jb[xy].removeActionListener(listener);
        }

    }

}

