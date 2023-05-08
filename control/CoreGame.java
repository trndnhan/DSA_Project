package control;

import java.util.Random;
import java.util.Stack;

import view.MineButton;
import view.GPanel;

public class CoreGame {

    private Random rd;
    private MineButton[][] arrayButton;
    private int[][] arrayBomb;
    private boolean[][] arrayBoolean;
    private boolean isLost; // Change from isComplete to isLost
    private boolean isWon; // Change from isEnd to isWon
    private int bomb;
    private boolean[][] arrayPflat;

    private Stack<int[]> stateStack;

    private GPanel game;

    public CoreGame(int w, int h, int bomb, GPanel game) {

        this.game = game;
        this.bomb = bomb;

        arrayButton = new MineButton[w][h];
        arrayBomb = new int[w][h];
        arrayBoolean = new boolean[w][h];
        stateStack = new Stack<int[]>();

        rd = new Random();

        createArrayBomb(bomb, w, h);
        point();

    }

    public boolean clickDouble(int i, int j) {

        boolean isBombH = false;

        for (int l = i - 1; l <= i + 1; l++) {
            for (int k = j - 1; k <= j + 1; k++) {
                if (l >= 0 && l <= arrayBomb.length - 1 && k >= 0 && k <= arrayBomb[i].length - 1) {
                    if (!arrayPflat[l][k]) {
                        if (arrayBomb[l][k] == -1) 
                            isBombH = true;
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

        if (isBombH) {
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

    public void Pflat(int i, int j) {
		if (!arrayBoolean[i][j]) {
			if (arrayPflat[i][j]) {
				flat--;
				arrayPflat[i][j] = false;
				arrayButton[i][j].setNumber(-1);
				arrayButton[i][j].repaint();
				game.getP1().updateLbBoom();
			} else if (flat < bomb) {
				co++;
				arrayPflat[i][j] = true;
				arrayButton[i][j].setNumber(9);
				arrayButton[i][j].repaint();
				game.getP1().updateLbBoom();
			}
		}

    public boolean open(int i, int j) {

        if (!isLost && !isWon) {

            if (!arrayBoolean[i][j]) {

                int[] array = { i, j };
                stateStack.push(array);

                if (arrayBomb[i][j] == 0) {

                    arrayBoolean[i][j] = true;
                    arrayButton[i][j].setNumber(0);
                    arrayButton[i][j].repaint();

                    if (checkWin()) {
                        isWon = true;

                        return false;
                    }

                } else {

                    int number = arrayBomb[i][j];

                    if (number != -1) {

                        arrayBoolean[i][j] = true;

                        arrayButton[i][j].setNumber(number);
                        arrayButton[i][j].repaint();

                        if (checkWin()) {
                            isWon = true;

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
                    isWon = true;

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

    public void createArrayBomb(int boom, int w, int h) {
        // Simplify the code
        int count = 0;
        while (count != boom) {
            int locationX = rd.nextInt(w);
            int locationY = rd.nextInt(h);
            if (arrayBomb[locationX][locationY] != -1) {
                arrayBomb[locationX][locationY] = -1;
                count += 1;
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

    public void undo() {
        if (!stateStack.empty() && !isLost && !isWon) {
            int[] array = stateStack.pop();
            arrayButton[array[0]][array[1]].setNumber(-1);
            arrayButton[array[0]][array[1]].repaint();
            arrayBoolean[array[0]][array[1]] = false;
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

    public boolean isLost() {
        return isLost;
    }

    public void setLost(boolean isLost) {
        this.isLost = isLost;
    }

    public boolean isWon() {
        return isWon;
    }

    public void setWon(boolean isEnd) {
        this.isWon = isEnd;
    }
}
