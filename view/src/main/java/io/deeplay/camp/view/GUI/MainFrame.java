package io.deeplay.camp.view.GUI;

import io.deeplay.camp.game.entites.Field;
import io.deeplay.camp.view.GUI.panels.GamePanel;
import io.deeplay.camp.view.GUI.panels.InfoPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private GamePanel gamePanel;
    private InfoPanel infoPanel;

    public MainFrame() {
        setTitle("Galaxy");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel = new GamePanel();
        infoPanel = new InfoPanel();


        setLayout(new BorderLayout());
        add(gamePanel, BorderLayout.WEST);
        add(infoPanel, BorderLayout.EAST);
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

    public void setField(Field field) {
        gamePanel.setField(field);
    }
}
