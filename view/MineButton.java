package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.JButton;

public class MineButton extends JButton {
    private int number = -1;
    private PanelPlayer p;

    public MineButton(PanelPlayer p) {
        this.p = p;
        this.setPreferredSize(new Dimension(30, 30));
    }

    public void paint(Graphics g) {
        switch(this.number) {
            case -1:
                g.drawImage((Image)this.p.getGame().getGFrame().getLoadData().getListImage().get("noUse"), 0, 0, this.getPreferredSize().width, this.getPreferredSize().height, (ImageObserver)null);
                break;
            case 0:
                g.drawImage((Image)this.p.getGame().getGFrame().getLoadData().getListImage().get("b0"), 0, 0, this.getPreferredSize().width, this.getPreferredSize().height, (ImageObserver)null);
                break;
            case 1:
                g.drawImage((Image)this.p.getGame().getGFrame().getLoadData().getListImage().get("b1"), 0, 0, this.getPreferredSize().width, this.getPreferredSize().height, (ImageObserver)null);
                break;
            case 2:
                g.drawImage((Image)this.p.getGame().getGFrame().getLoadData().getListImage().get("b2"), 0, 0, this.getPreferredSize().width, this.getPreferredSize().height, (ImageObserver)null);
                break;
            case 3:
                g.drawImage((Image)this.p.getGame().getGFrame().getLoadData().getListImage().get("b3"), 0, 0, this.getPreferredSize().width, this.getPreferredSize().height, (ImageObserver)null);
                break;
            case 4:
                g.drawImage((Image)this.p.getGame().getGFrame().getLoadData().getListImage().get("b4"), 0, 0, this.getPreferredSize().width, this.getPreferredSize().height, (ImageObserver)null);
                break;
            case 5:
                g.drawImage((Image)this.p.getGame().getGFrame().getLoadData().getListImage().get("b5"), 0, 0, this.getPreferredSize().width, this.getPreferredSize().height, (ImageObserver)null);
                break;
            case 6:
                g.drawImage((Image)this.p.getGame().getGFrame().getLoadData().getListImage().get("b6"), 0, 0, this.getPreferredSize().width, this.getPreferredSize().height, (ImageObserver)null);
                break;
            case 7:
                g.drawImage((Image)this.p.getGame().getGFrame().getLoadData().getListImage().get("b7"), 0, 0, this.getPreferredSize().width, this.getPreferredSize().height, (ImageObserver)null);
                break;
            case 8:
                g.drawImage((Image)this.p.getGame().getGFrame().getLoadData().getListImage().get("b8"), 0, 0, this.getPreferredSize().width, this.getPreferredSize().height, (ImageObserver)null);
                break;
            case 9:
                g.drawImage((Image)this.p.getGame().getGFrame().getLoadData().getListImage().get("flag"), 0, 0, this.getPreferredSize().width, this.getPreferredSize().height, (ImageObserver)null);
                break;
            case 10:
                g.drawImage((Image)this.p.getGame().getGFrame().getLoadData().getListImage().get("bomb"), 0, 0, this.getPreferredSize().width, this.getPreferredSize().height, (ImageObserver)null);
                break;
            case 11:
                g.drawImage((Image)this.p.getGame().getGFrame().getLoadData().getListImage().get("bombRed"), 0, 0, this.getPreferredSize().width, this.getPreferredSize().height, (ImageObserver)null);
                break;
            case 12:
                g.drawImage((Image)this.p.getGame().getGFrame().getLoadData().getListImage().get("bombX"), 0, 0, this.getPreferredSize().width, this.getPreferredSize().height, (ImageObserver)null);
        }

    }

    public void setNumber(int number) {
        this.number = number;
    }
}
