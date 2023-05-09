package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;


public class PanelNotification extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private JPanel p11, p12, p13;

    private LabelNumber lbTime, lbBomb;

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

        p12.add(lbTime = new LabelNumber(this, "000"));

        time = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                nowTime++;
                updateLbTime();
            }
        });

        this.p13.add(this.bt = new ButtonSmile(this));
        this.bt.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                PanelNotification.this.bt.setStage(4);
                PanelNotification.this.bt.repaint();
                int option = JOptionPane.showConfirmDialog((Component)null, "Are you play new game?", "Notification", 0);
                if (option == 0) {
                    PanelNotification.this.getGame().getGameFrame().setVisible(false);
                    new GFrame(game.getW(), game.getH(), game.getBomb());
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (!PanelNotification.this.getGame().getWorld().isEnd() && !PanelNotification.this.getGame().getWorld().isComplete()) {
                    PanelNotification.this.bt.setStage(2);
                    PanelNotification.this.bt.repaint();
                } else {
                    PanelNotification.this.getGame().getGameFrame().setVisible(false);
                    new GFrame(game.getW(), game.getH(), game.getBomb());
                }

            }

            @Override
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
            this.lbTime.setNumber("voCuc");
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
        String bomb = String.valueOf(this.game.getBomb() - this.game.getWorld().getCo());
        if (bomb.length() == 1) {
            this.lbBomb.setNumber("00" + bomb);
        } else if (bomb.length() == 2) {
            this.lbBomb.setNumber("0" + bomb);
        } else {
            this.lbBomb.setNumber("0" + bomb);
        }

        this.lbBomb.repaint();
    }

    public GPanel getGame() {
        return game;
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
        return bt;
    }

    public void setBt(ButtonSmile bt) {
        this.bt = bt;
    }

}
