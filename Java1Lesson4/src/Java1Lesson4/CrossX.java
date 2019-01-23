// This is a personal academic project. Dear PVS-Studio, please check it.

// PVS-Studio Static Code Analyzer for C, C++ and C#: http://www.viva64.com


/*
Нашел ошибку в расчете выигрыша, потому что неаккуратно скопировал циклы суммирования.
    Хорошая новость: я уже достаточно взрослый, чтобы пользоваться
    статическими анализаторами кода вместо поиска вручную!
Плохая новость: статические анализаторы кода возможно и не находят
все эти ошибки копирования, как рекламировалось.
*/

package Java1Lesson4;

import java.util.Scanner;

class CrossX {

    //размер и переменная поля
    //оставляем 3х3, потому что под общий случай писать получается долго
    private static final int mapSize = 3;
    private static final int toWin = 3;
    private static char[][] map;

    //значения полей
    private static final char player = 'X';
    private static final char computer = 'O';
    private static final char empty = '•';

    //сканер хода игрока
    private static final Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        initMap();

        //случайно выбираем, кто ходит первым
        if(Math.random() > 0.5){
            computer();
        }

        //цикл игры
        while(true){
            if (isMapFull()){
                System.out.println("Карта закончилась.");
                break;
            }
            printMap();
            player();
            if (checkWin(player)){
                System.out.println("Вы выиграли.");
                break;
            }
            if (isMapFull()){
                System.out.println("Карта закончилась.");
                break;
            }
            computer();
            if (checkWin(computer)){
                System.out.println("Компьютер выиграл.");
                break;
            }

        }

        printMap();

    }

    //инициализация карты NxN
    private static void initMap() {
        map = new char[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = empty;
            }
        }
    }

    //печать карты NxN
    private static void printMap() {
        for (int i = 0; i <= mapSize; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < mapSize ; i++) {
            System.out.print(i+1 +  " ");
            for (int j = 0; j < mapSize ; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    //ход игрока NxN
    private static void player() {
        int x,y;
        do {
            System.out.println("Введите ход в координатах клетки X Y:");
            x = intInput() - 1;
            y = intInput() - 1;
        } while (!isCellValid(x,y));
        map[x][y] = player;
    }

    //проверка на ввод числа игроком
    private static int intInput() {
        while (!sc.hasNextInt()){
            sc.nextLine();
            System.out.println("Вы ввели не число. Попробуйте еще раз: ");
        }
        return sc.nextInt();
    }

    //проверка возможности хода NxN
    private static boolean isCellValid(int x, int y) {
        return  x >= 0 &&
                y >= 0 &&
                x < mapSize &&
                y < mapSize &&
                map[x][y] == empty;
    }

    //проверка заполненности карты NxN
    private static boolean isMapFull() {
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (map[i][j] == empty) return false;
            }
        }
        return true;
    }

    //проверяем выигрыш 3х3
    private static boolean checkWin(char dot) {
        int hor = 0,
            vert = 0,
            diag1 = 0,
            diag2 = 0;

        //проверяем прямые
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize ; j++) {
                if (map[i][j] == dot){
                    hor++;
                }
                if (map[j][i] == dot){
                    vert++;
                }
            }
            if (hor == toWin || vert == toWin){
                return true;
            }
            hor = 0;
            vert = 0;
        }

        //проверяем диагонали
        for (int i = 0; i < mapSize; i++) {
            if (map[i][i] == dot){
                diag1++;
            }
            if (map[i][mapSize - i - 1] == dot){
                diag2++;
            }
        }
        return (diag1 == toWin || diag2 == toWin);

    }

    //ходим компьютером рядом со своим предыдущим ходом 3х3
    private static void computer(){
        boolean iDidIt = false; //мы походили!
        int x,y;

        //противостоим игроку
        iDidIt = opposition(player);


        //если центральная клетка свободна - ходим для самого первого хода
        if (isCellValid(1,1)){
            map[1][1] = computer;
            iDidIt = true;
        }

        //ищем свой предыдущий ход и ходим рядом
        X: if (!iDidIt) {
            for (int i = 0; i < mapSize; i++) {
                for (int j = 0; j < mapSize; j++) {
                    //если находим
                    if ((map[i][j]) == computer) {
                        //ищем куда бы походить в окрестностях
                        for (int k = -1; k <= 1; k++) {
                            for (int l = -1; l <= 1; l++) {
                                //если нашли - то оживляемся!
                                if (isCellValid(i + k, j + l)) {

                                    //делаем пробный ход и проверяем, выиграем ли мы
                                    map[i + k][j + l] = computer; //-V6025
                                    if (checkWin(computer)) {
                                        iDidIt = true;
                                        break X;
                                    } else {
                                        map[i + k][j + l] = empty; //-V6025
                                    }

                                    //проверяем, что мы так вообще построим линию
                                    if (isCellValid(i + 2 * k, j + 2 * l) || isCellValid(i - k, j - l)) {
                                        map[i + k][j + l] = computer; //-V6025
                                        iDidIt = true;
                                        break X;
                                    }
                                } //больше скобочек богу скобочек
                            }
                        }
                    }
                }
            }
        }

        //если рядом с предыдущим ходом пойти не получилось - ходим случайно
        while(!iDidIt) {
            x = (int) (Math.random() * 3);
            y = (int) (Math.random() * 3);
            if (isCellValid(x,y)) {
                map[x][y] = computer;
                iDidIt = true;
            }
        }
    }

    //противостоим игроку: присматриваем и делаем гадости 3х3
    private static boolean opposition(char dot){
        boolean found = false; //ход игрока найден
        int x,y,i,j;

        int hor = 0,
            vert = 0,
            diag1 = 0,
            diag2 = 0;

        //проверяем прямые
        XY: {
            for (i = 0; i < mapSize; i++) {
                for (j = 0; j < mapSize; j++) {
                    if (map[i][j] == dot) {
                        hor++;
                    }
                    if (map[j][i] == dot) {
                        vert++;
                    }
                }
                //если находим 2 точки по горизонтали
                if (hor == toWin - 1) {
                    for (y = 0; y < mapSize; y++) {
                        if (isCellValid(i, y)) {
                            map[i][y] = computer;
                            found = true;
                            break XY;
                        }
                    }
                }
                //если находим 2 точки  по вертикали
                if (vert == toWin - 1) {
                    for (x = 0; x < mapSize; x++) {
                        if (isCellValid(x, i)) {
                            map[x][i] = computer;
                            found = true;
                            break XY;
                        }
                    }
                }
                hor = 0;
                vert = 0;
            }
        }

        //проверяем диагонали
        DD: if (!found) {
            for (i = 0; i < mapSize; i++) {
                if (map[i][i] == dot) {
                    diag1++;
                }
                if (map[i][mapSize - i - 1] == dot) {
                    diag2++;
                }
            }
            //если находим 2 точки на одной диагонали
            if (diag1 == toWin - 1) {
                for (i = 0; i < mapSize; i++) {
                    if (isCellValid(i, i)) {
                        map[i][i] = computer;
                        found = true;
                        break DD;
                    }
                }
            }
            if (diag2 == toWin - 1) {
                for (i = 0; i < mapSize; i++) {
                    if (isCellValid(i, mapSize - i - 1)) {
                        map[i][mapSize - i - 1] = computer;
                        found = true;
                        break DD;
                    }
                }
            }
        }
        return found;
    }

}

