package view;

import control.CoreGame;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GPanel extends JPanel implements MouseListener {
    private PanelPlayer p;

    private GFrame gameFrame;

    private CoreGame coreGame;

    private int w;
    private int h;
    private int bomb;

    public GPanel(int w, int h, int bomb, GFrame gameFrame) {

        this.gameFrame = gameFrame;

        this.bomb = bomb;
        this.w = w;
        this.h = h;

        coreGame = new CoreGame(w, h, bomb, this);

        setLayout(new BorderLayout(20, 20));

        add(p = new PanelPlayer(this), BorderLayout.CENTER);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        MineButton[][] arrayButton = p.getArrayButton();
        for (int i = 0; i < arrayButton.length; i++) {
            for (int j = 0; j < arrayButton[i].length; j++) {
                if (e.getButton() == 1 && e.getSource() == arrayButton[i][j]) {

                    if (!coreGame.open(i, j)) {

                        if (coreGame.isLost()) {

                            int option = JOptionPane.showConfirmDialog(this, "Wanna play again ?", "You lost!",
                                    JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.YES_OPTION) {
                                gameFrame.setVisible(false);
                                new GFrame(w, h, bomb);
                            } else {
                                coreGame.fullTrue();
                            }
                        } else if (coreGame.isWon()) {

                            int option = JOptionPane.showConfirmDialog(this, "Wanna play again ?", "You win!",
                                    JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.YES_OPTION) {
                                gameFrame.setVisible(false);
                                new GFrame(w, h, bomb);
                            }
                        }
                    }
                }
            }

        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public CoreGame getCoreGame() {
        return coreGame;
    }

    public void setWorld(CoreGame coreGame) {
        this.coreGame = coreGame;
    }

    public GFrame getGameFrame() {
        return gameFrame;
    }

    public void setGameFrame(GFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public int getBomb() {
        return bomb;
    }

    public void setBomb(int bomb) {
        this.bomb = bomb;
    }


    public PanelPlayer getP2() {
        return p;
    }

    public void setP2(PanelPlayer p) {
        this.p = p;
    }
}
