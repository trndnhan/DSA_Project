package view;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class PanelPlayer extends JPanel {

    private GPanel game;

    private MineButton[][] arrayButton;

    public PanelPlayer(GPanel game) {
        this.game = game;

        setLayout(new GridLayout(game.getW(), game.getH()));

        arrayButton = game.getCoreGame().getArrayButton();

        setBorder(BorderFactory.createLoweredBevelBorder());
        for (int i = 0; i < arrayButton.length; i++) {
            for (int j = 0; j < arrayButton[i].length; j++) {
                add(arrayButton[i][j] = new MineButton(this));
                arrayButton[i][j].addMouseListener(game);
            }
        }
    }

    public MineButton[][] getArrayButton() {
        return arrayButton;
    }

    public void setArrayButton(MineButton[][] arrayButton) {
        this.arrayButton = arrayButton;
    }

    public GPanel getGame() {
        return game;
    }

    public void setGame(GPanel game) {
        this.game = game;
    }

}
