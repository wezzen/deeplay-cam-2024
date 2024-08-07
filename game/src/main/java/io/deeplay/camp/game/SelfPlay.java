package io.deeplay.camp.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.deeplay.camp.game.bots.Bot;
import io.deeplay.camp.game.domain.GalaxyListener;
import io.deeplay.camp.game.domain.GameTypes;
import io.deeplay.camp.game.entites.*;
import io.deeplay.camp.game.interfaces.PlayerInterface;
import io.deeplay.camp.game.utils.GameLogger;

public class SelfPlay implements GalaxyListener {

    private int sizeField;
    private String[] playerNames;
    private Bot.BotFactory[] factories;
    private PlayerInterface[] players = new PlayerInterface[2];
    private Map<String, PlayerInterface> playerNamesMap;
    private List<GalaxyListener> listeners;

    public SelfPlay(final int sizeField, final String[] playerNames, final Bot.BotFactory[] factories) {
        this.factories = factories;
        this.sizeField = sizeField;
        this.playerNames = playerNames;
        listeners = new ArrayList<>();
        playerNamesMap = new HashMap<>();
    }

    public void playGame() {
        listeners = new ArrayList<>();
        playerNamesMap = new HashMap<>();
        final Field field = new Field(sizeField);
        final Game game = new Game(field);
        final GameLogger logger = new GameLogger();
        long skipCounter = 0;
        long moveCounter = 0;


        List<Ship.ShipType> startShips = new ArrayList<>();
        startShips.add(Ship.ShipType.BASIC);

        players[0] = factories[0].createBot(playerNames[0], field);
        players[1] = factories[1].createBot(playerNames[1], field);
        playerNamesMap.put(playerNames[0], players[0]);
        playerNamesMap.put(playerNames[1], players[1]);

        listeners.add(game);
        listeners.add(logger);
        listeners.add(players[0]);
        listeners.add(players[1]);

        startGameSession("0000", GameTypes.BotVsBot);
        connectingPlayer(playerNames[0]);
        connectingPlayer(playerNames[1]);
        gameStarted(field);

        createShips(startShips, playerNames[0]);
        createShips(startShips, playerNames[1]);

        while (!game.isGameOver() && skipCounter < 4) {
            final String nextPlayerToAct = game.getNextPlayerToAct();
            currentInitiator = playerNamesMap.get(nextPlayerToAct);
            final PlayerInterface player = playerNamesMap.computeIfAbsent(nextPlayerToAct, (key) -> {
                throw new IllegalStateException("There is no player with name " + key);
            });

            final Answer answer = player.getAnswer(game.getField());

            if (answer.getMove().moveType() == Move.MoveType.SKIP) {
                skipCounter++;
            }

            if (answer.getShipList() != null) {
                createShips(answer.getShipList(), game.getNextPlayerToAct());
            }

            getPlayerAction(answer.getMove(), nextPlayerToAct);
            moveCounter++;
            if (moveCounter % 6 == 0) addCredits();
        }
        String winner = game.isWinner();
        gameEnded(winner);
        endGameSession();
    }

    @Override
    public void startGameSession(final String gameId, final GameTypes gameType) {
        for (final GalaxyListener listener : listeners) {
            listener.startGameSession(gameId, gameType);
        }
    }

    @Override
    public void connectingPlayer(final String waitingPlayerName) {
        for (final GalaxyListener listener : listeners) {
            listener.connectingPlayer(waitingPlayerName);
        }
    }

    @Override
    public void gameStarted(final Field field) {

        for (final GalaxyListener listener : listeners) {
            //if (listener != currentInitiator) {
                listener.gameStarted(new Field(field));
            }
        //}
        //currentInitiator = null; // Сбрасываем инициатора после вызова
    }

    @Override
    public void getPlayerAction(final Move move, final String playerName) {
        for (final GalaxyListener listener : listeners) {
            listener.getPlayerAction(move, playerName);
        }
    }

    @Override
    public void addCredits() {
        for (final GalaxyListener listener : listeners) {
            listener.addCredits();
        }
    }

    @Override
    public void createShips(List<Ship.ShipType> ships, String playerName) {
        for (final GalaxyListener listener : listeners) {
            listener.createShips(ships, playerName);
        }
    }

    @Override
    public void gameEnded(final String winner) {
        for (final GalaxyListener listener : listeners) {
            listener.gameEnded(winner);
        }
    }

    @Override
    public void endGameSession() {
        for (final GalaxyListener listener : listeners) {
            listener.endGameSession();
        }
    }
}
