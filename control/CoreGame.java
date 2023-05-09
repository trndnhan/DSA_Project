package control;

import java.util.Random;
import java.util.Stack;

import view.MineButton;
import view.ButtonSmile;
import view.GPanel;
import view.LabelNumber;

public class CoreGame {

    private Random rd;

    private MineButton[][] arrayButton;
    private int[][] arrayBomb;

    private boolean[][] arrayBoolean;

    private boolean[][] arrayFlag;
    private int flag;

    private boolean isLost;
    private boolean isWin;

    private ButtonSmile buttonSmile;
    private LabelNumber lbTime, lbBomb;

    private int bomb;

    private GPanel game;
    private Stack<int[]> stateStack;

    public CoreGame(int w, int h, int bomb, GPanel game) {

        stateStack = new Stack<int[]>();
        this.game = game;
        this.bomb = bomb;

        arrayButton = new MineButton[w][h];
        arrayBomb = new int[w][h];
        arrayBoolean = new boolean[w][h];
        arrayFlag = new boolean[w][h];

        rd = new Random();

        createBombArray(bomb, w, h);
        tileNumber();

        for (int i = 0; i < arrayButton.length; i++) {
            for (int j = 0; j < arrayButton[i].length; j++)
                System.out.print(arrayBomb[i][j] + " ");
            System.out.println();
        }
    }

    public boolean clickDouble(int i, int j) {

        boolean isBomb = false;

        for (int l = i - 1; l <= i + 1; l++) {
            for (int k = j - 1; k <= j + 1; k++) {
                if (l >= 0 && l <= arrayBomb.length - 1 && k >= 0 && k <= arrayBomb[i].length - 1) {
                    if (!arrayFlag[l][k]) {
                        if (arrayBomb[l][k] == -1) {
                            isBomb = true;
                            arrayButton[l][k].setNumber(12);
                            arrayButton[l][k].repaint();
                            arrayBoolean[l][k] = true;
                        } else if (!arrayBoolean[l][k]) {
                            if (arrayBomb[l][k] == 0) {
                                open(l, k);
                            } else {
                                arrayButton[l][k].setNumber(arrayBomb[l][k]);
                                arrayButton[l][k].repaint();
                                arrayBoolean[l][k] = true;
                            }
                        }
                    }
                }
            }
        }

        if (isBomb) {
            for (int j2 = 0; j2 < arrayBoolean.length; j2++) {
                for (int k = 0; k < arrayBoolean[i].length; k++) {
                    if (arrayBomb[j2][k] == -1 && !arrayBoolean[j2][k]) {
                        arrayButton[j2][k].setNumber(10);
                        arrayButton[j2][k].repaint();
                    }
                }
            }
            return false;
        }

        return true;
    }

    public void flag(int i, int j) {
        if (!arrayBoolean[i][j]) {
            if (arrayFlag[i][j]) {
                flag--;
                arrayFlag[i][j] = false;
                arrayButton[i][j].setNumber(-1);
                arrayButton[i][j].repaint();
                game.getP1().updateLbBomb();
            } else if (flag < bomb) {
                flag++;
                arrayFlag[i][j] = true;
                arrayButton[i][j].setNumber(9);
                arrayButton[i][j].repaint();
                game.getP1().updateLbBomb();
            }
        }

    }

    public boolean open(int i, int j) {

        if (!isLost && !isWin) {
            if (!arrayBoolean[i][j]) {
                int[] array = { i, j };
                stateStack.push(array);
                if (arrayBomb[i][j] == 0) {

                    arrayBoolean[i][j] = true;
                    arrayButton[i][j].setNumber(0);
                    arrayButton[i][j].repaint();

                    if (checkWin()) {
                        isWin = true;

                        return false;
                    }

                    for (int l = i - 1; l <= i + 1; l++) {
                        for (int k = j - 1; k <= j + 1; k++) {
                            if (l >= 0 && l <= arrayBomb.length - 1 && k >= 0 && k <= arrayBomb[i].length - 1) {
                                if (!arrayBoolean[l][k]) {
                                    open(l, k);
                                }
                            }
                        }
                    }

                    if (checkWin()) {
                        isWin = true;

                        return false;
                    }

                } else {

                    int number = arrayBomb[i][j];

                    if (number != -1) {

                        arrayBoolean[i][j] = true;

                        arrayButton[i][j].setNumber(number);
                        arrayButton[i][j].repaint();

                        if (checkWin()) {
                            isWin = true;

                            return false;
                        }

                        return true;
                    }
                }
            }

            if (arrayBomb[i][j] == -1) {
                arrayButton[i][j].setNumber(11);
                arrayButton[i][j].repaint();
                isLost = true;

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
                    isWin = true;

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

    public void tileNumber() {
        for (int i = 0; i < arrayBomb.length; i++)
            for (int j = 0; j < arrayBomb[i].length; j++)
                if (arrayBomb[i][j] == 0) {
                    int count = 0;
                    for (int l = i - 1; l <= i + 1; l++)
                        for (int k = j - 1; k <= j + 1; k++)
                            if (l >= 0 && l <= arrayBomb.length - 1 && k >= 0 && k <= arrayBomb[i].length - 1)
                                if (arrayBomb[l][k] == -1)
                                    count++;
                    arrayBomb[i][j] = count;
                }
    }

    public void createBombArray(int bomb, int w, int h) {
        int count = 0;
        while (count != bomb) {
            int locationX = rd.nextInt(w);
            int locationY = rd.nextInt(h);
            if (arrayBomb[locationX][locationY] != -1) {
                arrayBomb[locationX][locationY] = -1;
                count += 1;
            }
        }

    }

    public void undo() {
        if (!stateStack.empty() && !isLost && !isWin) {
            int[] array = stateStack.pop();
            arrayButton[array[0]][array[1]].setNumber(-1);
            arrayButton[array[0]][array[1]].repaint();
            arrayBoolean[array[0]][array[1]] = false;
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

    public void setMineButton(MineButton[][] arrayButton) {
        this.arrayButton = arrayButton;
    }

    public ButtonSmile getButtonSmile() {
        return buttonSmile;
    }

    public void setButtonSmile(ButtonSmile buttonSmile) {
        this.buttonSmile = buttonSmile;
    }

    public LabelNumber getLbTime() {
        return lbTime;
    }

    public void setLbTime(LabelNumber lbTime) {
        this.lbTime = lbTime;
    }

    public LabelNumber getLbBomb() {
        return lbBomb;
    }

    public void setLbBomb(LabelNumber lbBomb) {
        this.lbBomb = lbBomb;
    }

    public boolean[][] getArrayBoolean() {
        return arrayBoolean;
    }

    public void setArrayBoolean(boolean[][] arrayBoolean) {
        this.arrayBoolean = arrayBoolean;
    }

    public boolean isLost() {
        return isLost;
    }

    public void setLost(boolean isLost) {
        this.isLost = isLost;
    }

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean isWin) {
        this.isWin = isWin;
    }

    public boolean[][] getArrayFlag() {
        return arrayFlag;
    }

    public void setArrayFlag(boolean[][] arrayFlag) {
        this.arrayFlag = arrayFlag;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

}
