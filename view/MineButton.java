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
public class MineButton extends JButton {

    private int number;

    private PanelPlayer p;

    public MineButton(PanelPlayer p) {
        number = -1;
        this.p = p;
        setPreferredSize(new Dimension(30, 30));
    }



    @Override
    public void paint(Graphics g) {
        switch (number) {

            case -1:
                g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get("noUse"), 0, 0,
                        getPreferredSize().width, getPreferredSize().height, null);
                break;

            case 0:
                g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get("b0"), 0, 0,
                        getPreferredSize().width, getPreferredSize().height, null);
                break;

            case 1:
                g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get("b1"), 0, 0,
                        getPreferredSize().width, getPreferredSize().height, null);
                break;

            case 2:
                g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get("b2"), 0, 0,
                        getPreferredSize().width, getPreferredSize().height, null);
                break;

            case 3:
                g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get("b3"), 0, 0,
                        getPreferredSize().width, getPreferredSize().height, null);
                break;

            case 4:
                g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get("b4"), 0, 0,
                        getPreferredSize().width, getPreferredSize().height, null);
                break;

            case 5:
                g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get("b5"), 0, 0,
                        getPreferredSize().width, getPreferredSize().height, null);
                break;

            case 6:
                g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get("b6"), 0, 0,
                        getPreferredSize().width, getPreferredSize().height, null);
                break;

            case 7:
                g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get("b7"), 0, 0,
                        getPreferredSize().width, getPreferredSize().height, null);
                break;

            case 8:
                g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get("b8"), 0, 0,
                        getPreferredSize().width, getPreferredSize().height, null);
                break;
            case 9:
                g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get("flag"), 0, 0,
                        getPreferredSize().width, getPreferredSize().height, null);
                break;
            case 10:
                g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get("bomb"), 0, 0,
                        getPreferredSize().width, getPreferredSize().height, null);
                break;
            case 11:
                g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get("bombRed"), 0, 0,
                        getPreferredSize().width, getPreferredSize().height, null);
                break;
            case 12:
                g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get("bombX"), 0, 0,
                        getPreferredSize().width, getPreferredSize().height, null);
                break;
            default:
                break;
        }
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
