package io.deeplay.camp.engine.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;

/**
 * Класс-представление сущности Игрок. Класс абстрактный для опредлеение разных игроков:
 * 1) человек
 * 2) бот
 */
public abstract class Player {
    /**
     * Хранит в себе переменные:
     * legalMoves - коллекция ходов для всех флотов в распоряжении
     * isInCheck - флажок состояния для игрока
     * fleetList - список флотов в распоряжении
     * controlledPlanet - список захваченых планет
     */
    protected final Collection<Move> legalMoves;
    protected final boolean isInCheck;
    protected ArrayList<Fleet> fleetList;
    protected ArrayList<Planet> controlledPlanet;

    public Player(Collection<Move> legalMoves, boolean isInCheck, ArrayList<Fleet> fleetList, ArrayList<Planet> controlledPlanet) {
        this.legalMoves = legalMoves;
        this.isInCheck = isInCheck;
        this.fleetList = fleetList;
        this.controlledPlanet = controlledPlanet;
    }

    public boolean isInCheck() {
     return this.isInCheck;
    }

    public Collection<Move> getLegalMoves() {
        return legalMoves;
    }

    public ArrayList<Fleet> getFleetList() {
        return fleetList;
    }

    /**
     * Метод актуализирует количество очков игрока, пересчитывая их за захваченные планеты
     * @return
     */
    public int getCurrentGamePoints() {
        int totalPoints = 0;
        for (Planet planet : controlledPlanet) {
            totalPoints += planet.getPoints();
        }
        return totalPoints;
    }

    /**
     * Метод фильтрует коллекцию ходов moves с помощью стрима stream(), оставляя только те ходы,
     * у которых координаты назначения совпадают с заданной клеткой.
     * Использует метод collect для сбора отфильтрованных ходов в неизменяемый список
     * @param tile
     * @param moves
     * @return
     */
    static Collection<Move> calculateAttacksOnTile(final int[] tile,
                                                   final Collection<Move> moves) {
        return moves.stream()
                .filter(move -> Arrays.equals(move.endPosition(), tile)) //Либо move.getDestinationCoordinate() == tile
                .collect(collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }
}
