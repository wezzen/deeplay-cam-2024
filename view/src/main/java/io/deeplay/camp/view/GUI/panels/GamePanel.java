package io.deeplay.camp.view.GUI.panels;

import io.deeplay.camp.game.entites.Cell;
import io.deeplay.camp.game.entites.Field;
import io.deeplay.camp.view.GUI.fabric.ImageFactory;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private static final ImageFactory imageFactory = new ImageFactory();
    private Field field;
    private int squareSize = 50; // Размер каждого квадрата
    private int arcSize = 15;

    public GamePanel() {
        setPreferredSize(new Dimension(800, 800));

    }

    public void setField(Field field) {
        this.field = field;
        squareSize = (int) (((double) getWidth() / field.getSize()) * 0.9);
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
                drawCell(g, cell, j * cellSize, i * cellSize, cellSize);
            }
        }
    }

    private void drawCell(Graphics g, Cell cell, int x, int y, int cellSize) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (cell.planet == null) {
            g2d.setColor(Color.LIGHT_GRAY);
        }
        //todo разные цвета для разных игроков
        else if (cell.planet.getOwner() != null) {
            g2d.setColor(Color.GREEN);
        } else {
            g2d.setColor(Color.DARK_GRAY);
        }
        g2d.fillRoundRect(x, y, squareSize, squareSize, arcSize, arcSize);
        if (cell.getFleet() != null) {
            int shipSize = cellSize / 2;
            int shipX = x + (cellSize - shipSize) / 2;
            int shipY = y + (cellSize - shipSize) / 2;

            g2d.setColor(Color.BLUE); // Color for the ship
            g2d.fillOval(shipX, shipY, shipSize, shipSize);
        }
    }


}
