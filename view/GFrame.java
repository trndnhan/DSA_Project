package view;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import model.LoadData;

public class GFrame extends JFrame {
    private LoadData loadData = new LoadData();
    private GPanel gamePanel;
    private JMenuBar mnb;
    private JMenu menu;
    private JMenuItem basic;
    private JMenuItem normal;
    private JMenuItem hard;
    private JMenuItem newGame;
    private JMenuItem exit;
    private JMenuItem undo;

    public GFrame(final int w, final int h, final int boom) {
        this.setJMenuBar(this.mnb = new JMenuBar());
        this.mnb.add(this.menu = new JMenu("Game"));
        this.menu.add(this.newGame = new JMenuItem("New game"));
        this.menu.addSeparator();
        this.menu.add(this.basic = new JMenuItem("Basic"));
        this.menu.add(this.normal = new JMenuItem("Normal"));
        this.menu.add(this.hard = new JMenuItem("Hard"));
        this.menu.addSeparator();
        this.menu.add(this.undo = new JMenuItem("Undo"));
        this.menu.addSeparator();
        this.menu.add(this.exit = new JMenuItem("Exit"));
        if (w == 8) {
            this.basic.setIcon(new ImageIcon((Image)this.loadData.getListImage().get("tick")));
        } else if (w == 16) {
            this.normal.setIcon(new ImageIcon((Image)this.loadData.getListImage().get("tick")));
        } else {
            this.hard.setIcon(new ImageIcon((Image)this.loadData.getListImage().get("tick")));
        }

        this.basic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GFrame.this.setVisible(false);
                new GFrame(8, 8, 10);
            }
        });
        this.normal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GFrame.this.setVisible(false);
                new GFrame(16, 16, 40);
            }
        });
        this.hard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GFrame.this.setVisible(false);
                new GFrame(16, 30, 99);
            }
        });
        this.newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GFrame.this.setVisible(false);
                new GFrame(w, h, boom);
            }
        });
        this.undo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GFrame.this.getGamePanel().getCoreGame().undo();
            }
        });
        this.exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.add(this.gamePanel = new GPanel(w, h, boom, this));
        this.setIconImage((Image)this.loadData.getListImage().get("title"));
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo((Component)null);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new GFrame(8, 8, 10);
    }

    public LoadData getLoadData() {
        return this.loadData;
    }

    public void setLoadData(LoadData loadData) {
        this.loadData = loadData;
    }

    public GPanel getGamePanel() {
        return this.gamePanel;
    }

    public void setGamePanel(GPanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
