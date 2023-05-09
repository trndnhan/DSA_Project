package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.JButton;

public class ButtonSmile extends JButton {
    public static final int win = 0;
    public static final int lose = 1;
    public static final int press = 2;
    public static final int wow = 3;
    public static final int now = 4;
    private PanelNotification p;
    private int stage;

    public ButtonSmile(PanelNotification p) {
        this.p = p;
        this.setPreferredSize(new Dimension(50, 50));
        this.stage = 4;
    }

    public void paint(Graphics g) {
        switch(this.stage) {
            case 0:
                g.drawImage((Image)this.p.getGame().getGFrame().getLoadData().getListImage().get("smileWin"), 0, 0, this.getPreferredSize().width, this.getPreferredSize().height, (ImageObserver)null);
                break;
            case 1:
                g.drawImage((Image)this.p.getGame().getGFrame().getLoadData().getListImage().get("smileLose"), 0, 0, this.getPreferredSize().width, this.getPreferredSize().height, (ImageObserver)null);
                break;
            case 2:
                g.drawImage((Image)this.p.getGame().getGFrame().getLoadData().getListImage().get("smilePress"), 0, 0, this.getPreferredSize().width, this.getPreferredSize().height, (ImageObserver)null);
                break;
            case 3:
                g.drawImage((Image)this.p.getGame().getGFrame().getLoadData().getListImage().get("smilePressPlay"), 0, 0, this.getPreferredSize().width, this.getPreferredSize().height, (ImageObserver)null);
                break;
            case 4:
                g.drawImage((Image)this.p.getGame().getGFrame().getLoadData().getListImage().get("smile"), 0, 0, this.getPreferredSize().width, this.getPreferredSize().height, (ImageObserver)null);
        }

    }

    public int getStage() {
        return this.stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }
}
