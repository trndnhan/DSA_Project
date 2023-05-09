package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PanelNotification extends JPanel {
    private JPanel p11;
    private JPanel p12;
    private JPanel p13;
    private LabelNumber lbTime;
    private LabelNumber lbBomb;
    private GPanel game;
    private ButtonSmile bt;
    private Timer time;
    private int nowTime;

    public PanelNotification(final GPanel game) {
        this.game = game;
        this.lbTime = game.getCoreGame().getLbTime();
        this.lbBomb = game.getCoreGame().getLbBomb();
        this.bt = game.getCoreGame().getButtonSmile();
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLoweredBevelBorder());
        this.add(this.p11 = new JPanel(), "West");
        this.add(this.p12 = new JPanel(), "East");
        this.add(this.p13 = new JPanel(), "Center");
        this.p11.add(this.lbBomb = new LabelNumber(this, "000"));
        this.updateLbBomb();
        this.p12.add(this.lbTime = new LabelNumber(this, "000"));
        this.time = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ++PanelNotification.this.nowTime;
                PanelNotification.this.updateLbTime();
            }
        });
        this.p13.add(this.bt = new ButtonSmile(this));
        this.bt.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {
                PanelNotification.this.bt.setStage(4);
                PanelNotification.this.bt.repaint();
                int option = JOptionPane.showConfirmDialog((Component)null, "Do you want to play a new game?", "NEW GAME", 0);
                if (option == 0) {
                    PanelNotification.this.getGame().getGFrame().setVisible(false);
                    new GFrame(game.getW(), game.getH(), game.getBoom());
                }

            }

            public void mousePressed(MouseEvent e) {
                if (!PanelNotification.this.getGame().getCoreGame().isWin() && !PanelNotification.this.getGame().getCoreGame().isLost()) {
                    PanelNotification.this.bt.setStage(2);
                    PanelNotification.this.bt.repaint();
                } else {
                    PanelNotification.this.getGame().getGFrame().setVisible(false);
                    new GFrame(game.getW(), game.getH(), game.getBoom());
                }

            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent e) {
            }
        });
    }

    public void updateLbTime() {
        if (this.nowTime > 999) {
            this.lbTime.setNumber("inf");
        } else {
            String cTime = String.valueOf(this.nowTime);
            if (cTime.length() == 1) {
                this.lbTime.setNumber("00" + cTime);
            } else if (cTime.length() == 2) {
                this.lbTime.setNumber("0" + cTime);
            } else {
                this.lbTime.setNumber(cTime);
            }

            this.lbTime.repaint();
        }

    }

    public void updateLbBomb() {
        String boom = String.valueOf(this.game.getBoom() - this.game.getCoreGame().getFlag());
        if (boom.length() == 1) {
            this.lbBomb.setNumber("00" + boom);
        } else if (boom.length() == 2) {
            this.lbBomb.setNumber("0" + boom);
        } else {
            this.lbBomb.setNumber("0" + boom);
        }

        this.lbBomb.repaint();
    }

    public GPanel getGame() {
        return this.game;
    }

    public void setGame(GPanel game) {
        this.game = game;
    }

    public Timer getTime() {
        return this.time;
    }

    public void setTime(Timer time) {
        this.time = time;
    }

    public ButtonSmile getBt() {
        return this.bt;
    }

    public void setBt(ButtonSmile bt) {
        this.bt = bt;
    }
}
