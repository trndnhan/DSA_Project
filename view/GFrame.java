package view;

import javax.swing.JFrame;

import model.LoadData;

public class GFrame extends JFrame {

    private LoadData loadData;

    private GPanel gPanel;

    public GFrame(int w, int h, int bomb) {

        loadData = new LoadData();

        add(gPanel = new GPanel(w, h, bomb, this));

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
