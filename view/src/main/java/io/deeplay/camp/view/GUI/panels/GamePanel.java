package io.deeplay.camp.view.GUI.panels;

import io.deeplay.camp.game.entites.Cell;
import io.deeplay.camp.game.entites.Field;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Field field;
    private int squareSize = 50; // Размер каждого квадрата
    private int arcSize = 15;
    public GamePanel() {
        setPreferredSize(new Dimension(800, 800));
    }

    public void setField(Field field) {
        this.field = field;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (field != null) {
            drawField(g);
        }
    }

    private void drawField(Graphics g) {
        int size = field.getSize();
        int cellSize = getWidth() / size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell cell = field.getBoard()[i][j];
                if (cell.planet == null) {
                    drawCell(g, cell, i * cellSize, j * cellSize, cellSize);
                } else {
                    drawCellWithPlanet(g, cell, i * cellSize, j * cellSize, cellSize);
                }
            }
        }
    }

    private void drawCell(Graphics g, Cell cell, int x, int y, int cellSize) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.GRAY);
        g2d.fillRoundRect(x, y, squareSize, squareSize, arcSize, arcSize);
    }

    private void drawCellWithPlanet(Graphics g, Cell cell, int x, int y, int cellSize) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.GREEN);
        g2d.fillRoundRect(x, y, squareSize, squareSize, arcSize, arcSize);
    }
}
