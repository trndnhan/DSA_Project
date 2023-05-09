package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import model.LoadData;



public class GameFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private LoadData loadData = new LoadData();
    private GPanel gamePanel;
    private JMenuBar mnb;
    private JMenu menu;
    private JMenuItem undo, Beginner, Intermediate, Expert, NewGame, Exit;


    public void GFrame(int w, int h, int bomb) {

        this.setJMenuBar(this.mnb = new JMenuBar());
        this.mnb.add(this.menu = new JMenu("Game"));
//        this.menu.add(this.undo = new JMenuItem("Undo"));

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
                    GFrame.setVisible(false);
                    new GFrame(8, 8, 10);
         }
        });
        Intermediate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    GFrame.setVisible(false);
                    new GFrame(16,16, 40);
         }
        });
        Expert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    GFrame.setVisible(false);
                    new GFrame(30, 16, 99);
         }
        });
        NewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    GFrame.setVisible(false);
                    new GFrame(w, h, bomb);
         }
        });
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    System.exit(0);
         }
        });

        add(gamePanel = new GPanel(w, h, bomb, this));

        this.undo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gamePanel.getCoreGame().undo();
            }
        });
        this.add(this.gamePanel = new GPanel(w, h, bomb, this));
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
