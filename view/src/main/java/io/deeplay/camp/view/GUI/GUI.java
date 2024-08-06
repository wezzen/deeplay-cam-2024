package io.deeplay.camp.view.GUI;

import com.formdev.flatlaf.FlatIntelliJLaf;
import io.deeplay.camp.game.domain.GameTypes;
import io.deeplay.camp.game.entites.Field;
import io.deeplay.camp.game.entites.Game;
import io.deeplay.camp.game.entites.Move;
import io.deeplay.camp.game.entites.Ship;
import io.deeplay.camp.view.GameUI;

import javax.swing.*;
import java.util.List;

public class GUI implements GameUI {
    private MainFrame mainFrame;

    Game game;

    public GUI(Field field) {
        game = new Game(field);
        mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }

    @Override
    public void startGameSession(String gameId, GameTypes gameType) {
        game.startGameSession(gameId, gameType);
    }

    @Override
    public void connectingPlayer(String waitingPlayerName) {
        game.connectingPlayer(waitingPlayerName);
    }

    @Override
    public void gameStarted(Field field) {
//        game = new Game(field);
        game.gameStarted(field);
        mainFrame.setField(field);
        mainFrame.getGamePanel().repaint();
        mainFrame.repaint();
    }

    @Override
    public void getPlayerAction(Move move, String playerName) {
        game.getPlayerAction(move, playerName);
        mainFrame.getGamePanel().setField(game.getField());
        mainFrame.repaint();
        
    }

    @Override
    public void addCredits() {
        game.addCredits();
    }

    @Override
    public void createShips(List<Ship.ShipType> ships, String playerName) {
        game.createShips(ships, playerName);
    }

    @Override
    public void gameEnded(String winner) {
        game.gameEnded(winner);
    }

    @Override
    public void endGameSession() {
        game.endGameSession();
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
