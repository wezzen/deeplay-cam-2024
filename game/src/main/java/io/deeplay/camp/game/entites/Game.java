package io.deeplay.camp.game.entites;

import io.deeplay.camp.game.domain.GalaxyListener;
import io.deeplay.camp.game.domain.GameTypes;
import io.deeplay.camp.game.utils.ValidationMove;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Контроллер
 */
public class Game implements GalaxyListener {
    private final static int NUM_PLAYERS = 2;

    private final Field field;
    private GameTypes gameType;
    private List<Move> allGameMoves;
    public final Player[] players = new Player[NUM_PLAYERS];
    private final Map<String, Player> playerNames;
    private final Map<String, Cell> playerStartPosition;
    private int nextPlayerToAct;
    private String id;

    public Game(final Field field) {
        this.field = field;
        this.allGameMoves = new ArrayList<>();
        this.playerNames = new HashMap<>();
        this.playerStartPosition = new HashMap<>();
    }


    public GameTypes getGameType() {
        return gameType;
    }


    public String getId() {
        return id;
    }

    public String getNextPlayerToAct() {
        return players[nextPlayerToAct].getName();
    }

    public int getNextPlayerToActIndex() {
        return nextPlayerToAct;
    }


    public boolean isGameOver() {
        return field.isGameOver();
    }

    public String isWinner() {
        return field.isWinner();
    }


    @Override
    public void startGameSession(String gameId, GameTypes gameType) {
        this.gameType = gameType;
        id = gameId;
    }

    @Override
    public void connectingPlayer(final String waitingPlayerName) {
        if (players[0] == null) {
            players[0] = new Player(0, waitingPlayerName);
            playerNames.put(waitingPlayerName, players[0]);
        } else if (players[1] == null) {
            players[1] = new Player(1, waitingPlayerName);
            playerNames.put(waitingPlayerName, players[1]);
        } else throw new IllegalArgumentException("Игроки уже существуют");
    }

    @Override
    public void gameStarted(Field field) {
        this.field.setBoard(field.getBoard());
        playerStartPosition.put(players[0].getName(), field.getBoard()[0][field.getSize() - 1]);
        playerStartPosition.put(players[1].getName(), field.getBoard()[field.getSize() - 1][0]);
    }

    /**
     * Обрабатывает действие игрока в игре.
     *
     * @param move       объект {@link Move}, представляющий ход игрока.
     * @param playerName имя игрока, совершающего ход.
     * @throws IllegalArgumentException если игрок с указанным именем не существует.
     * @throws IllegalStateException    если ход не валиден или тип хода не поддерживается.
     *                                  <p>
     *                                  Метод проверяет валидность игрока, переключает ход на следующего игрока и обрабатывает ход в зависимости от его типа (ORDINARY, CAPTURE, SKIP).
     *                                  Если ход валиден, он добавляется в список всех ходов игры и применяется к текущему игроку.
     *                                  В конце, из очков текущего игрока вычитается стоимость хода.
     */
    @Override
    public void getPlayerAction(Move move, String playerName) {
        if (!playerNames.containsKey(playerName)) {
            throw new IllegalArgumentException("Отсутствует игрок:" + playerName);
        }
        switchPlayerToAct();

        if (move.moveType() == Move.MoveType.ORDINARY) {
            if (ValidationMove.isValidOrdinaryMove(move, field, players[nextPlayerToAct])) {
                allGameMoves.add(move);
                move.makeMove(players[nextPlayerToAct]);
            } else throw new IllegalStateException("Такой ORDINARY move не валиден!");
        } else if (move.moveType() == Move.MoveType.CAPTURE) {
            if (ValidationMove.isValidCaptureMove(move, players[nextPlayerToAct])) {
                allGameMoves.add(move);
                move.makeAttack(players[nextPlayerToAct]);
            } else throw new IllegalStateException("Такой CAPTURE move не валиден!");
        } else if (move.moveType() == Move.MoveType.SKIP) {
            getAllGameMoves().add(move);
        } else throw new IllegalStateException("Не существует такого типа хода!");

        players[nextPlayerToAct].decreaseTotalGamePoints(move.cost());
    }

    @Override
    public void createShips(List<Ship.ShipType> ships, String playerName) {
        Fleet fleet = new Fleet(playerStartPosition.get(playerName), playerNames.get(playerName));
        for (Ship.ShipType shipType : ships) {
            Ship ship = new Ship(shipType, fleet);
        }
    }

    // todo сделать начисление очков раз в несколько ходов, но пока у нас нет этого
    @Override
    public void gameEnded(String winner) {

    }

    @Override
    public void endGameSession() {

    }

    public Field getField() {
        return field;
    }

    public List<Move> getAllGameMoves() {
        return allGameMoves;
    }

    public Player getPlayerByName(final String name) {
        return playerNames.computeIfAbsent(name, (key) -> {
            throw new IllegalStateException("There is no player with name " + key);
        });
    }

    public Map<String, Player> getPlayerNames() {
        return playerNames;
    }

    public void switchPlayerToAct() {
        nextPlayerToAct = (nextPlayerToAct + 1) % NUM_PLAYERS;
    }

}
