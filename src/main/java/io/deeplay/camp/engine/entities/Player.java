package io.deeplay.camp.engine.entities;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;

/**
 * Класс-представление сущности Игрок
 */
public class Player {
    /**
     * Хранит в себе переменные:
     * name - имя игрока
     * id - иентификатор
     * fleetList - список флотов в распоряжении
     * controlledPlanet - список захваченых планет
     * legalMoves - коллекция ходов для всех флотов в распоряжении
     */
    private final long id;
    private final String name;
    private int totalGamePoints;
    protected ArrayList<Fleet> fleetList;
    protected ArrayList<Planet> controlledPlanet;
    protected Collection<Move> legalMoves;

    public Player(long id, String name) {
        this.id = id;
        this.name = name;
        this.totalGamePoints = 0;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Fleet> getFleetList() {
        return fleetList;
    }

    public ArrayList<Planet> getControlledPlanet() {
        return controlledPlanet;
    }

    public Collection<Move> getLegalMoves() {
        return legalMoves;
    }

    public int getTotalGamePoints() {
        return totalGamePoints;
    }

    /**
     * Метод актуализирует количество очков игрока, пересчитывая их за захваченные планеты
     * @return totalPoints
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
     * @param cell
     * @param moves
     * @return
     */
    Collection<Move> calculateAttacksOnTile(final Cell cell,
                                            final Collection<Move> moves) {
        return moves.stream()
                .filter(move -> equals(move.endPosition(), cell)) //Либо move.getDestinationCoordinate() == tile
                .collect(collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    private boolean equals(Object o, Object a) {
        return o.equals(a);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id && totalGamePoints == player.totalGamePoints && Objects.equals(name, player.name) && Objects.equals(fleetList, player.fleetList) && Objects.equals(controlledPlanet, player.controlledPlanet) && Objects.equals(legalMoves, player.legalMoves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, totalGamePoints, fleetList, controlledPlanet, legalMoves);
    }
}
