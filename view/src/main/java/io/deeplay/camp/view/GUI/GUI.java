package io.deeplay.camp.view.GUI;

import com.formdev.flatlaf.FlatIntelliJLaf;
import io.deeplay.camp.game.domain.GameTypes;
import io.deeplay.camp.game.entites.Field;
import io.deeplay.camp.game.entites.Move;
import io.deeplay.camp.game.entites.Ship;
import io.deeplay.camp.view.GameUI;

import javax.swing.*;
import java.util.List;

public class GUI implements GameUI {
    private MainFrame mainFrame;

    public GUI() {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }

    @Override
    public void startGameSession(String gameId, GameTypes gameType) {

    }

    @Override
    public void connectingPlayer(String waitingPlayerName) {

    }

    @Override
    public void gameStarted(Field field) {
        mainFrame.setField(field);
        mainFrame.getGamePanel().repaint();
        mainFrame.repaint();
    }

    @Override
    public void getPlayerAction(Move move, String playerName) {

    }

    @Override
    public void createShips(List<Ship.ShipType> ships, String playerName) {

    }

    @Override
    public void gameEnded(String winner) {

    }

    @Override
    public void endGameSession() {

    }

    @Override
    public void createGameSession(String username) {

    }

    @Override
    public void selectShipColor(String color) {

    }

    @Override
    public void renderGameField(Field field) {

    }

    @Override
    public void suggestMoveOptions(List<Move> moves) {

    }

    @Override
    public void gameStateUpdate() {

    }
}
