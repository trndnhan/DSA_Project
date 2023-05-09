package view;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class PanelPlayer extends JPanel {
    private GPanel game;
    private MineButton[][] arrayButton;

    public PanelPlayer(GPanel game) {
        this.game = game;
        this.setLayout(new GridLayout(game.getW(), game.getH()));
        this.arrayButton = game.getCoreGame().getArrayButton();
        this.setBorder(BorderFactory.createLoweredBevelBorder());

        for(int i = 0; i < this.arrayButton.length; ++i) {
            for(int j = 0; j < this.arrayButton[i].length; ++j) {
                this.add(this.arrayButton[i][j] = new MineButton(this));
                this.arrayButton[i][j].addMouseListener(game);
            }
        }

    }

    public MineButton[][] getArrayButton() {
        return this.arrayButton;
    }

    public void setArrayButton(MineButton[][] arrayButton) {
        this.arrayButton = arrayButton;
    }

    public GPanel getGame() {
        return this.game;
    }

    public void setGame(GPanel game) {
        this.game = game;
    }
}
