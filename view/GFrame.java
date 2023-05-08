package view;

 import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javafx.event.ActionEvent;
import model.LoadData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GFrame extends JFrame {

    private LoadData loadData;
    
    private JMenuBar mnb;
    private GPanel gPanel;
    private JMenu menu;
    private JMenuItem undo;

    private JMenuBar mnb;
    private JMenu menu;
    private JMenuItem Beginner,Intermediate,Expert,NewGame,Exit;

    public GFrame(int w, int h, int bomb) {

        this.setJMenuBar(this.mnb = new JMenuBar());
        this.mnb.add(this.menu = new JMenu("Game"));
        this.menu.add(this.undo = new JMenuItem("Undo"));

        loadData = new LoadData();

        setJMenuBar(mnb = new JMenuBar());
        mnb.add(menu = new JMenu("Game"));

        menu.add(NewGame = new JMenuItem("New Game"));
        menu.addSeperator();

        menu.add(Beginner = new JMenuItem("Beginner")); 
        menu.add(Intermediate = new JMenuItem("Intermediate"));
        menu.add(Expert = new JMenuItem("Expert"));

        menu.addSeparator();
        menu.add(Exit = new JMenuItem("Exit"));
        

        //
        if (w==8) {
            Beginner.setIcon(new ImageIcon(loadData.getListImage().get("tick")));
         } else if (w==16) {
            Intermediate.setIcon (new ImageIcon(loadData.getListImage().get("tick"))):
         } else {
            Expert.setIcon(new ImageIcon(loadData.getListImage().get("tick")));
         }
        

         Beginner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    gameFrame.setVisible(false);
                    new GFrame(8, 8, 10);
         }
        });
        Intermediate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    gameFrame.setVisible(false);
                    new GFrame(16,16, 40);
         }
        });
        Expert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    gameFrame.setVisible(false);
                    new GFrame(30, 16, 99);
         }
        });
        NewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    gameFrame.setVisible(false);
                    new GFrame(w, h, bomb);
         }
        });
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    System.exit(0);
         }
        });

        add(gPanel = new GPanel(w, h, bomb, this));

        this.undo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gPanel.getCoreGame().undo();
            }
        });

        setIconImage(loadData.getListImage().get("title"));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GFrame(8, 8, 10);
    }

    public LoadData getLoadData() {
        return loadData;
    }

    public void setLoadData(LoadData loadData) {
        this.loadData = loadData;
    }

    public GPanel getGamePanel() {
        return gPanel;
    }

    public void setGamePanel(GPanel gPanel) {
        this.gPanel = gPanel;
    }

}
