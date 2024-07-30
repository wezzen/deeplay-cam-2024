package io.deeplay.camp.game;

import io.deeplay.camp.game.bots.RandomBot;
import io.deeplay.camp.game.domain.GalaxyListener;
import io.deeplay.camp.game.entites.*;
import io.deeplay.camp.game.interfaces.PlayerInterface;
import io.deeplay.camp.game.utils.GameLogger;

import java.util.*;

public class SelfPlay {

    private final int sizeField;
    private String[] playerNames;
    List<RandomBot> players;
    Map<String, RandomBot> stringPlayerInterfaceMap;
    List<GalaxyListener> listeners;

    public SelfPlay(int sizeField, String[] playerNames) {
        this.sizeField = sizeField;
        this.playerNames = playerNames;
        players = new ArrayList<>();
        listeners = new ArrayList<>();
        stringPlayerInterfaceMap = new HashMap<>();
    }

    private void initializePlayers(Field field, Game game) {
        Cell cellWithFleet = field.getBoard()[0][field.getSize() - 1];
        Cell cellWithFleet_ = field.getBoard()[field.getSize() - 1][0];


        Player player0 = new Player(0, playerNames[0]);
        Player player1 = new Player(1, playerNames[1]);
        game.connectingPlayer(playerNames[0]);
        game.connectingPlayer(playerNames[1]);


        Fleet fleet0 = new Fleet(cellWithFleet, player0);
        Fleet fleet1 = new Fleet(cellWithFleet_, player1);
        Ship ship = new Ship(Ship.ShipType.BASIC, fleet0);
        Ship ship_ = new Ship(Ship.ShipType.BASIC, fleet1);

        RandomBot randomBot0 = new RandomBot.Factory(player0).createBot(field);
        RandomBot randomBot1 = new RandomBot.Factory(player1).createBot(field);

        randomBot0.getGame().getPlayerNames().put(playerNames[1], player1);
        randomBot1.getGame().getPlayerNames().put(playerNames[0], player0);

        randomBot0.getGame().connectingPlayer(playerNames[0]);
        randomBot0.getGame().connectingPlayer(playerNames[1]);

        randomBot1.getGame().connectingPlayer(playerNames[0]);
        randomBot1.getGame().connectingPlayer(playerNames[1]);

        randomBot0.getGame().setNextPlayerToAct(0);
        randomBot1.getGame().setNextPlayerToAct(0);


        players.add(randomBot0);
        players.add(randomBot1);

        for (PlayerInterface player : players) {
            player.gameStarted(field);
        }
    }

    private void initializeListeners(Field field, Game game) {
        GameLogger logger = new GameLogger();
        listeners.add(logger);


        for (GalaxyListener listener : listeners) {
            listener.gameStarted(field);
        }
    }


    public void playGame() {
        Field field = new Field(sizeField);
        final Game game = new Game(field);

        initializePlayers(field, game);
        initializeListeners(field, game);

        for (int i = 0; i < playerNames.length; i++) {
            stringPlayerInterfaceMap.put(playerNames[i], players.get(i));
        }

        String currentPlayer;
        Answer answer;

        while (!game.isGameOver()) {
            currentPlayer = game.getNextPlayerToAct();
            answer = stringPlayerInterfaceMap.get(currentPlayer).getAnswer(game.getField());
            //todo валидировать
            game.getPlayerAction(answer.getMove(), currentPlayer);
            //todo валидировать опять(?)
            for (RandomBot player : players) {
//                if (!Objects.equals(player.player.getName(), currentPlayer)) {
                    player.getPlayerAction(answer.getMove(), currentPlayer);
//                }
            }

            for (GalaxyListener listener : listeners) {
                listener.getPlayerAction(answer.getMove(), currentPlayer);
            }
        }

        String winner = game.isWinner().getName();
        for (PlayerInterface player : players) {
            player.gameEnded(winner);
        }
        for (GalaxyListener listener : listeners) {
            listener.gameEnded(winner);
        }
    }

}
