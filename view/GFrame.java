package view;

import javax.swing.*;

import model.LoadData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GFrame extends JFrame {

    private LoadData loadData;
    private JMenuBar mnb;
    private GPanel gPanel;
    private JMenu menu;
    private JMenuItem undo;

    public GFrame(int w, int h, int bomb) {

        this.setJMenuBar(this.mnb = new JMenuBar());
        this.mnb.add(this.menu = new JMenu("Game"));
        this.menu.add(this.undo = new JMenuItem("Undo"));

        loadData = new LoadData();

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
