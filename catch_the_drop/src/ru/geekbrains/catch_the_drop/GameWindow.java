package ru.geekbrains.catch_the_drop;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class GameWindow extends JFrame {

    private static Image background;
    private static Image game_over;
    private static Image drop;

    private static float drop_top = -200;
    private static float drop_left = 200;
    private static float drop_v = 200;

    private static long last_frame_time = 0;

    private static int score = 0;

    private static GameWindow game_window;
    private static int lives = 2;


    public static void main(String[] args) throws IOException {
        background = ImageIO.read(GameWindow.class.getResourceAsStream("background.jpg"));
        drop = ImageIO.read(GameWindow.class.getResourceAsStream("drop.png"));
        game_window = new GameWindow();
        game_window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game_window.setLocation(50, 50);
        game_window.setSize(1024, 768);
        game_window.setResizable(false);
        last_frame_time = System.nanoTime();
        GameField game_field = new GameField();
        game_field.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                float drop_right = drop_left + drop.getWidth(null);
                float drop_bottom = drop_top + drop.getHeight(null);
                boolean is_drop = x >= drop_left && x <= drop_right && y>= drop_top && y <=drop_bottom;
                if (is_drop){
                    drop_top = -200;
                    drop_left = (float) Math.random() * (game_field.getWidth() - drop.getWidth(null));
                    drop_v *= 1.05;
                    score++;
                    game_window.setTitle("Lives: " + lives + " Score: " + score);
                }
            }
        });
        game_window.add(game_field);
        game_window.setVisible(true);
    }

    private static void onRepaint(Graphics g) {
        long current_time = System.nanoTime();
        float delta_time = (current_time - last_frame_time) * 10e-10f;
        last_frame_time = current_time;

        drop_top = drop_top + drop_v * delta_time;


        g.drawImage(background, 0,0,null);
        g.drawImage(drop, (int) drop_left, (int) drop_top,null);
        if (drop_top > game_window.getHeight()) {
            if (lives == 0) {
                for (int a = -30; a <= 1024; a += 100) {
                    for (int b = -30; b <= 768; b += 122)
                        g.drawImage(drop, a, b, null);
                }
                game_window.setTitle("Game over. " + " Score: " + score);

            }
            else {
                drop_top = -200;
                lives--;
                game_window.setTitle("Lives: " + lives + " Score: " + score);
            }
            }
        }

    private static class GameField extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            onRepaint(g);
                try {
                    sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            repaint();
        }
    }
}