// This is a personal academic project. Dear PVS-Studio, please check it.

// PVS-Studio Static Code Analyzer for C, C++ and C#: http://www.viva64.com


package Java1Lesson8;


class CrossX {

    //размер и переменная поля
    //оставляем 3х3, потому что под общий случай писать получается долго
    private final int mapSize = 3;
    private final int toWin = 3;
    private char[][] map;

    //значения полей
    final char player = 'X';
    final char computer = 'O';
    final char empty = '•';


    public CrossX(){
        map = new char[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = empty;
            }
        }
    }


    //ход игрока NxN
    public void player(int x,int y) {
        map[x][y] = player;
    }


    //проверка возможности хода NxN
    boolean isCellValid(int x, int y) {
        return  x >= 0 &&
                y >= 0 &&
                x < mapSize &&
                y < mapSize &&
                map[x][y] == empty;
    }

    //проверка заполненности карты NxN
    boolean isMapFull() {
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (map[i][j] == empty) return false;
            }
        }
        return true;
    }

    //проверяем выигрыш 3х3
    boolean checkWin(char dot) {
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
    int computer(){
        boolean iDidIt = false; //мы походили!
        int x,y;

        //противостоим игроку
        int opp = opposition(player);
        if ( opp > -1) {return opp;}


        //если центральная клетка свободна - ходим для самого первого хода
        if (isCellValid(1,1)){
            map[1][1] = computer;
            iDidIt = true;
            return 4;
        }

        //ищем свой предыдущий ход и ходим рядом
        if (!iDidIt) {
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
                                        return ((i + k) * 3 + (j + l));
                                    } else {
                                        map[i + k][j + l] = empty; //-V6025
                                    }

                                    //проверяем, что мы так вообще построим линию
                                    if (isCellValid(i + 2 * k, j + 2 * l) || isCellValid(i - k, j - l)) {
                                        map[i + k][j + l] = computer; //-V6025
                                        iDidIt = true;
                                        return ((i + k) * 3 + (j + l));
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
                return (3*x + y);
            }
        }
        return 0;
    }

    //противостоим игроку: присматриваем и делаем гадости 3х3
    private int opposition(char dot){
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
                            return (3*i + y);
                        }
                    }
                }
                //если находим 2 точки  по вертикали
                if (vert == toWin - 1) {
                    for (x = 0; x < mapSize; x++) {
                        if (isCellValid(x, i)) {
                            map[x][i] = computer;
                            found = true;
                            return (3*x + i);
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
                        return (3*i + i);
                    }
                }
            }
            if (diag2 == toWin - 1) {
                for (i = 0; i < mapSize; i++) {
                    if (isCellValid(i, mapSize - i - 1)) {
                        map[i][mapSize - i - 1] = computer;
                        found = true;
                        return (3*i + (mapSize - i - 1));
                    }
                }
            }
        }
        return -1;
    }

}

