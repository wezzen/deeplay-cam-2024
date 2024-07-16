package io.deeplay.camp.engine.entities;

import io.deeplay.camp.engine.entities.domain.GameStates;
import io.deeplay.camp.engine.entities.domain.GameTypes;

import java.util.ArrayList;
import java.util.Collection;

public class Game {
    private final Field field;
    private final ArrayList<Player> players;
    private GameStates currentState;
    private GameTypes gameType;
    private ArrayList<Move> allGameMoves;

    //Пока что не придумал куда и как пихать цикл обновление счетчика

    public Game(Field field, ArrayList<Player> players, GameTypes gameType) {
        this.field = field;
        this.players = players;
        this.gameType = gameType;
        this.currentState = GameStates.DEFAULT;
        this.allGameMoves = null;
    }

    public void startGame() {
        this.currentState = GameStates.PRECEDENCE;
        while (!isGameOver()) {
            for (Player player : players) {
                executeTurn(player);
                if (isGameOver()) {
                    break;
                }
            }
        }
        this.currentState = GameStates.COMPLETE;
    }

    private void executeTurn(Player player) {
        Collection<Move> legalMoves = player.getLegalMoves();
        if (legalMoves.isEmpty()) {
            return;
        }
        // Для простоты давайте просто выполним первый законный ход
        Move move = legalMoves.iterator().next();
        executeMove(player, move);
    }

    private void executeMove(Player player, Move move) {
        // Перемещение флота
        // Реализую логику для выполнения хода
        // Это может быть перемещение флота, атака на планету и т.д.
        if (move.moveType() == Move.MoveType.ORDINARY) {
            for (Fleet fleet : player.getFleetList()) {
                if (fleet.getFleetPosition().equals(move.startPosition())) {
                    fleet.setFleetPosition(move.endPosition());
                    System.out.println("Fleet moved to new position: " + move.endPosition());
                    break;
                }
            }
        } else if (move.moveType() == Move.MoveType.CAPTURE) {
            // Атака на планету
            Cell targetCell = move.endPosition();
            if (targetCell.planet != null) {
                player.getControlledPlanet().add(targetCell.planet);
                System.out.println("Planet captured at: " + move.endPosition());
            } else {
                System.out.println("No planet to capture at: " + move.endPosition());
            }
        }

        // Обновляем состояние игры на основе перемещения
        updateGameState();
        System.out.println(player.getName() + " executes move: " + move);
        allGameMoves.add(move);
        // Обновляем состояние игры, на основе перемещения
    }

    private void updateGameState(){
        boolean allPlanetsCaptured = true;
        Player winningPlayer = null;

        for (Player player : this.getPlayers()) { // предполагаем, что есть список игроков
            if (player.getControlledPlanet().size() == field.getAllPlanets().size()) {
                winningPlayer = player;
                break;
            }
        }

        if (winningPlayer != null) {
            currentState = GameStates.COMPLETE;
            System.out.println("Player " + winningPlayer.getName() + " has won the game!");
            return;
        }

        // Проверка ничьи
        boolean isDraw = true;
        int controlledPlanets = players.get(0).getControlledPlanet().size();
        for (Player player : players) {
            if (player.getControlledPlanet().size() != controlledPlanets) {
                isDraw = false;
                break;
            }
        }

        if (isDraw) {
            currentState = GameStates.DRAW;
            System.out.println("The game is a draw!");
            return;
        }

        // Состояние атаки
        if (allGameMoves.getLast().moveType() == Move.MoveType.CAPTURE) { // lastMove предполагается, что содержит последний ход
            currentState = GameStates.PRECEDENCE;
            System.out.println("A planet was captured!");
            return;
        }

        // Обычное состояние
        currentState = GameStates.DEFAULT;
        System.out.println("Game continues in DEFAULT state.");
    }

    public boolean isGameOver() {
        // Реализую логику для определения окончания игры
        // Пока что только на захват всех планет
        return false;
    }

    public GameStates getCurrentState() {
        return currentState;
    }

    public GameTypes getGameType() {
        return gameType;
    }

    public void setGameType(GameTypes gameType) {
        this.gameType = gameType;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
