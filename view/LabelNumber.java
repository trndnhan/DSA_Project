package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.JLabel;

public class LabelNumber extends JLabel {
    private PanelNotification p;
    private String number;

    public LabelNumber(PanelNotification p, String number) {
        this.p = p;
        this.number = number;
        this.setPreferredSize(new Dimension(78, 46));
    }

    public void paint(Graphics g) {
        if (this.number.equals("inf")) {
            g.drawImage((Image)this.p.getGame().getGFrame().getLoadData().getListImage().get("inf"), 0, 0, 26, 46, (ImageObserver)null);
            g.drawImage((Image)this.p.getGame().getGFrame().getLoadData().getListImage().get("inf"), 26, 0, 26, 46, (ImageObserver)null);
            g.drawImage((Image)this.p.getGame().getGFrame().getLoadData().getListImage().get("inf"), 52, 0, 26, 46, (ImageObserver)null);
        } else {
            g.drawImage((Image)this.p.getGame().getGFrame().getLoadData().getListImage().get(String.valueOf(this.number.charAt(0))), 0, 0, 26, 46, (ImageObserver)null);
            g.drawImage((Image)this.p.getGame().getGFrame().getLoadData().getListImage().get(String.valueOf(this.number.charAt(1))), 26, 0, 26, 46, (ImageObserver)null);
            g.drawImage((Image)this.p.getGame().getGFrame().getLoadData().getListImage().get(String.valueOf(this.number.charAt(2))), 52, 0, 26, 46, (ImageObserver)null);
        }

    }

    public PanelNotification getP() {
        return this.p;
    }

    public void setP(PanelNotification p) {
        this.p = p;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
