package view;

import control.CoreGame;
import control.CoreGame;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GPanel extends JPanel implements MouseListener {
    private PanelNotification p1;
    private PanelPlayer p2;
    private GFrame gameFrame;
    private CoreGame coreGame;
    private int w;
    private int h;
    private int boom;

    public GPanel(int w, int h, int boom, GFrame gameFrame) {
        this.gameFrame = gameFrame;
        this.boom = boom;
        this.w = w;
        this.h = h;
        this.coreGame = new CoreGame(w, h, boom, this);
        this.setLayout(new BorderLayout(20, 20));
        this.add(this.p1 = new PanelNotification(this), "North");
        this.add(this.p2 = new PanelPlayer(this), "Center");
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        this.getP1().getBt().setStage(3);
        this.getP1().getBt().repaint();
        MineButton[][] arrayButton = this.p2.getArrayButton();

        for(int i = 0; i < arrayButton.length; ++i) {
            for(int j = 0; j < arrayButton[i].length; ++j) {
                int option;
                if (e.getButton() == 1 && e.getSource() == arrayButton[i][j] && !this.coreGame.getArrayFlag()[i][j]) {
                    if (!this.getP1().getTime().isRunning()) {
                        this.getP1().getTime().start();
                    }

                    if (!this.coreGame.open(i, j)) {
                        if (this.coreGame.isLost()) {
                            this.getP1().getTime().stop();
                            this.getP1().getBt().setStage(1);
                            this.getP1().getBt().repaint();
                            option = JOptionPane.showConfirmDialog(this, "Wanna replay?", "YOU LOSE!", 0);
                            if (option == 0) {
                                this.gameFrame.setVisible(false);
                                new GFrame(this.w, this.h, this.boom);
                            } else {
                                this.coreGame.fullTrue();
                            }
                        } else if (this.coreGame.isWin()) {
                            this.getP1().getTime().stop();
                            this.getP1().getBt().setStage(0);
                            this.getP1().getBt().repaint();
                            option = JOptionPane.showConfirmDialog(this, "Wanna replay??", "YOU WIN!", 0);
                            if (option == 0) {
                                this.gameFrame.setVisible(false);
                                new GFrame(this.w, this.h, this.boom);
                            }
                        }
                    }
                } else if (e.getButton() == 3 && e.getSource() == arrayButton[i][j]) {
                    this.coreGame.flag(i, j);
                }

                if (e.getClickCount() == 2 && e.getSource() == arrayButton[i][j] && this.coreGame.getArrayBoolean()[i][j] && !this.coreGame.clickDouble(i, j)) {
                    option = JOptionPane.showConfirmDialog(this, "Wanna replay?", "YOU LOSE!", 0);
                    if (option == 0) {
                        this.gameFrame.setVisible(false);
                        new GFrame(this.w, this.h, this.boom);
                    } else {
                        this.coreGame.fullTrue();
                    }
                }
            }
        }

    }

    public void mouseReleased(MouseEvent e) {
        this.getP1().getBt().setStage(4);
        this.getP1().getBt().repaint();
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public int getW() {
        return this.w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return this.h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public CoreGame getCoreGame() {
        return this.coreGame;
    }

    public void setCoreGame(CoreGame coreGame) {
        this.coreGame = coreGame;
    }

    public GFrame getGFrame() {
        return this.gameFrame;
    }

    public void setGFrame(GFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public int getBoom() {
        return this.boom;
    }

    public void setBoom(int boom) {
        this.boom = boom;
    }

    public PanelNotification getP1() {
        return this.p1;
    }

    public void setP1(PanelNotification p1) {
        this.p1 = p1;
    }

    public PanelPlayer getP2() {
        return this.p2;
    }

    public void setP2(PanelPlayer p2) {
        this.p2 = p2;
    }
}
