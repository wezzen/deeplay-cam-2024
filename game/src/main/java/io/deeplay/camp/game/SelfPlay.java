package io.deeplay.camp.game;

import io.deeplay.camp.game.domain.GalaxyListener;
import io.deeplay.camp.game.entites.Answer;
import io.deeplay.camp.game.entites.Field;
import io.deeplay.camp.game.entites.Game;
import io.deeplay.camp.game.entites.Player;
import io.deeplay.camp.game.interfaces.PlayerInterface;

public class SelfPlay {

    private final int sizeField;
    private String[] playerNames;
    PlayerInterface[] players;
    GalaxyListener[] listeners;

    public SelfPlay(int sizeField, String[] playerNames) {
        this.sizeField = sizeField;
    }

    private void initializePlayers(Field field) {

        for (PlayerInterface player : players) {
            player.gameStarted(field);
        }
    }

    private void initializeListeners(Field field) {

    }

    private PlayerInterface getPlayer(String name) {

    }

    public void playGame() {
        Field field = new Field(sizeField);
        final Game game = new Game(field);
        String currentPlayer;
        Answer answer;
        initializePlayers(field);
        initializeListeners(field);


        while (!game.isGameOver()) {
            currentPlayer = game.getNextPlayerToAct();
            answer = getPlayer(currentPlayer).getAnswer();
            //todo ваолидировать
            game.getPlayerAction(answer.getMove(), currentPlayer);
            //todo ваолидировать опять(?)
            for (PlayerInterface player : players) {
                player.getPlayerAction(answer.getMove(), currentPlayer);
            }
            for (GalaxyListener listener : listeners) {
                listener.getPlayerAction(answer.getMove(), currentPlayer);
            }
        }

        game.isWinner();

        //gameEnded
        //endGameSession
    }

    public static void main(String[] args) {


    }
}
