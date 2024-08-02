package io.deeplay.camp.game.entites;

import io.deeplay.camp.game.utils.ValidationMove;

import java.util.Objects;

/**
 * Класс - ход, как record,
 * чтоб не закладывать, еще не продуманную, логику.
 * Record обеспечивает из коробки все требуемые методы (и даже больше)
 */
public record Move(Cell startPosition, Cell endPosition, MoveType moveType, int cost) {
    // точка входа для хода
    public void makeMove(final Player player) {
        Fleet fleet = startPosition.getFleet();
        Fleet enemyFleet = endPosition.getFleet();
        if (enemyFleet != null) {
            handleEnemyFleetEncounter(player, fleet, enemyFleet);
        } else {
            moveFleetToPosition(fleet, endPosition);
        }
        checkAndCapturePlanet(player);
    }

    // на конечной позиции есть флот
    private void handleEnemyFleetEncounter(final Player player,final Fleet fleet,final Fleet enemyFleet) {
        if (!enemyFleet.getOwner().equals(player)) { // флот оппонента
            handleOpponentFleet(player, fleet, enemyFleet);
        } else { // флот игрока
            mergeFleets(player, fleet, enemyFleet);
        }
    }

    private void handleOpponentFleet(final Player player,final Fleet fleet,final Fleet enemyFleet) {
        if (endPosition.planet != null) {
            if (endPosition.planet.isCaptured()) {// планета есть и она принадлежит сопернику
                fleet.fleetsClashWithPlanet(enemyFleet, player, enemyFleet.getOwner(), endPosition.planet.points);
            } else { // планета есть и она свободна
                fleet.fleetsClash(enemyFleet, player, enemyFleet.getOwner());
            }
        } else fleet.fleetsClash(enemyFleet, player, enemyFleet.getOwner()); //планеты нет

        if (player.getFleetList().contains(fleet)) {
            moveFleetToPosition(fleet, endPosition);
        } else {
            clearFleetFromPosition(startPosition);
        }
    }

    private void mergeFleets(final Player player,final Fleet fleet1,final Fleet fleet2) {
        fleet2.addShipsIntoFleet(fleet1.getShipList());
        player.removeFleet(fleet1);
        clearFleetFromPosition(startPosition);
    }

    private void checkAndCapturePlanet(final Player player) {
        if (endPosition.planet != null && endPosition.getFleet().getOwner().equals(player)) {
            if (ValidationMove.isCapturePlanet(endPosition.getFleet().getFleetPower(), endPosition.planet.points)) {
                capturePlanet(player, endPosition.planet);
            } else if (endPosition.planet.isCaptured() && !endPosition.planet.getOwner().equals(player)) {
                clearFleetFromPosition(endPosition);
            }
        }
    }

    public void capturePlanet(final Player player,final Planet planet) {
        player.controlledPlanet.add(planet);
        planet.setOwner(player);
        planet.isCaptured();
    }

    // вход для атаки
    public void makeAttack(final Player player) {
        Fleet fleet = startPosition.getFleet();
        Fleet enemyFleet = endPosition.getFleet();

        if (endPosition.planet != null) {
            handlePlanetCaptureAttempt(player, fleet, enemyFleet);
        }

        if (enemyFleet != null && !enemyFleet.getOwner().equals(player)) {
            engageInCombat(player, fleet, enemyFleet);
        }
    }

    private void handlePlanetCaptureAttempt(final Player player,final Fleet fleet,final Fleet enemyFleet) {
            int enemyFleetPower;
        if (enemyFleet != null && endPosition.planet.isCaptured()) { // выше проверяется на null
            enemyFleetPower = enemyFleet.getFleetPower();
        } else {
            enemyFleetPower = 0;
        }
        if (ValidationMove.isCapturePlanet(fleet.getFleetPower(), endPosition.planet.points + enemyFleetPower)) {
                capturePlanet(player, endPosition.planet);

        }
    }

    private void engageInCombat(final Player player,final Fleet fleet,final Fleet enemyFleet) {
        fleet.fleetsClash(enemyFleet, player, enemyFleet.getOwner());
        if (player.getFleetList().contains(fleet)) {
            clearFleetFromPosition(endPosition);
        } else {
            clearFleetFromPosition(startPosition);
        }
    }

    private void moveFleetToPosition(final Fleet fleet,final Cell targetPosition) {
        setFleetOnPosition(targetPosition, fleet);
        clearFleetFromPosition(startPosition);
    }

    private void setFleetOnPosition(final Cell position, final Fleet fleet) {
        fleet.setFleetPosition(position);
        position.setFleet(fleet);
    }

    private void clearFleetFromPosition(final Cell position) {
        position.setFleet(null);
    }

    /**
     * Два типа хода:
     * ORDINARY - как перемещение по игровому полю
     * CAPTURE - атака на планету, захваченную противником
     */
    public enum MoveType {
        ORDINARY,
        CAPTURE,
        SKIP
    }

    /**
     * Статус-флажок для контроля выполнения игроком хода:
     * DONE - выполнен
     * ILLEGAL_MOVE - ход не валидный, не выполнен
     * LEAVES_PLAYER_IN_CHECK - состояние проверки хода игрока
     */
    public enum MoveStatus {
        DONE,
        ILLEGAL_MOVE,
        LEAVES_PLAYER_IN_CHECK
    }

    @Override
    public String toString() {
        if (startPosition != null && endPosition != null) {
            return "Start position = " + startPosition.toString() + " end position = " + endPosition.toString();
        } else {
            return "Empty Move";
        }
    }
}

