package control;

import java.util.Random;

import view.MineButton;
import view.GPanel;

public class CoreGame {

    private Random rd;
    private MineButton[][] arrayButton;
    private int[][] arrayBomb;
    private boolean[][] arrayBoolean;
    private boolean isComplete;
    private boolean isEnd;
    private int bomb;

    private GPanel game;

    public CoreGame(int w, int h, int bomb, GPanel game) {

        this.game = game;
        this.bomb = bomb;

        arrayButton = new MineButton[w][h];
        arrayBomb = new int[w][h];
        arrayBoolean = new boolean[w][h];

        rd = new Random();

        createArrayBomb(bomb, w, h);
        point();

    }

    public boolean open(int i, int j) {

        if (!isComplete && !isEnd) {
            if (!arrayBoolean[i][j]) {
                if (arrayBomb[i][j] == 0) {

                    arrayBoolean[i][j] = true;
                    arrayButton[i][j].setNumber(0);
                    arrayButton[i][j].repaint();

                    if (checkWin()) {
                        isEnd = true;

                        return false;
                    }

                } else {

                    int number = arrayBomb[i][j];

                    if (number != -1) {

                        arrayBoolean[i][j] = true;

                        arrayButton[i][j].setNumber(number);
                        arrayButton[i][j].repaint();

                        if (checkWin()) {
                            isEnd = true;

                            return false;
                        }

                        return true;
                    }
                }
            }

            if (arrayBomb[i][j] == -1) {
                arrayButton[i][j].setNumber(11);
                arrayButton[i][j].repaint();
                isComplete = true;

                for (int j2 = 0; j2 < arrayBoolean.length; j2++) {
                    for (int k = 0; k < arrayBoolean[i].length; k++) {
                        if (arrayBomb[j2][k] == -1 && !arrayBoolean[j2][k]) {
                            if (j2 != i || k != j) {
                                arrayButton[j2][k].setNumber(10);
                                arrayButton[j2][k].repaint();
                            }
                        }
                    }
                }

                return false;
            } else {

                if (checkWin()) {
                    isEnd = true;

                    return false;
                }

                return true;
            }
        } else

            return false;

    }

    public boolean checkWin() {
        int count = 0;
        for (int i = 0; i < arrayBoolean.length; i++) {
            for (int j = 0; j < arrayBoolean[i].length; j++) {
                if (!arrayBoolean[i][j]) {
                    count++;
                }
            }
        }
        if (count == bomb)
            return true;
        else
            return false;
    }

    public void point() {
        for (int i = 0; i < arrayBomb.length; i++) {
            for (int j = 0; j < arrayBomb[i].length; j++) {
                if (arrayBomb[i][j] == 0) {
                    int count = 0;
                    for (int l = i - 1; l <= i + 1; l++) {
                        for (int k = j - 1; k <= j + 1; k++) {
                            if (l >= 0 && l <= arrayBomb.length - 1 && k >= 0 && k <= arrayBomb[i].length - 1)
                                if (arrayBomb[l][k] == -1) {
                                    count++;
                                }
                        }
                    }
                    arrayBomb[i][j] = count;
                }
            }
        }
    }

    public void createArrayBomb(int boom, int w, int h) {
        int locationX = rd.nextInt(w);
        int locationY = rd.nextInt(h);

        arrayBomb[locationX][locationY] = -1;
        int count = 1;
        while (count != boom) {
            locationX = rd.nextInt(w);
            locationY = rd.nextInt(h);
            if (arrayBomb[locationX][locationY] != -1) {

                arrayBomb[locationX][locationY] = -1;

                count = 0;
                for (int i = 0; i < arrayBomb.length; i++) {
                    for (int j = 0; j < arrayBomb[i].length; j++) {
                        if (arrayBomb[i][j] == -1)
                            count++;
                    }
                }
            }
        }

    }

    public void fullTrue() {
        for (int i = 0; i < arrayBoolean.length; i++) {
            for (int j = 0; j < arrayBoolean[i].length; j++) {
                if (!arrayBoolean[i][j]) {
                    arrayBoolean[i][j] = true;
                }
            }
        }
    }

    public MineButton[][] getArrayButton() {
        return arrayButton;
    }

    public void setArrayButton(MineButton[][] arrayButton) {
        this.arrayButton = arrayButton;
    }

    public boolean[][] getArrayBoolean() {
        return arrayBoolean;
    }

    public void setArrayBoolean(boolean[][] arrayBoolean) {
        this.arrayBoolean = arrayBoolean;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }
}
